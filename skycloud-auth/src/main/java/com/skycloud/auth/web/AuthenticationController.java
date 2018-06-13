package com.skycloud.auth.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.skycloud.auth.model.domain.UserDO;
import com.skycloud.auth.utils.RequestUtil;
import com.skycloud.common.base.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

/**
 * @date 2018年03月10日
 */
@RestController
@RequestMapping("/authentication")
@Slf4j
public class AuthenticationController {
    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private TokenEndpoint tokenEndpoint;

//    @Autowired
//    private TokenStore tokenStore;

    @Autowired
    private TokenStore jwtTokenStore;


    private String refreshTokenUrl = "http://localhost:8106/oauth/token";

    private static final String BEARER_TOKEN_TYPE = "Basic ";


    /**
     * 认证页面
     *
     * @return ModelAndView
     */
    @GetMapping("/require")
    public ModelAndView require() {
        return new ModelAndView("ftl/login");
    }


    @RequestMapping("/login")
    public ResponseVo<OAuth2AccessToken> login(HttpServletRequest request,@RequestParam String username, @RequestParam String password) {
        try {
            log.info("login  start ......");

            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (header == null || !header.startsWith("Custom")) {
                throw new UnapprovedClientAuthenticationException("请求头中无client信息");
            }
            String[] tokens = RequestUtil.extractAndDecodeHeader(header);
            assert tokens.length == 2;

            String clientId = tokens[0];
            String clientSecret = tokens[1];

            Map<String, String> parameters = new HashMap<>();
            parameters.put("username", username);
            parameters.put("password", password);
            parameters.put("grant_type", "password");//设置授权类型为密码模式
            Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
            Authentication authentication = new UsernamePasswordAuthenticationToken(clientId, clientSecret, grantedAuthorities);
            ResponseEntity<OAuth2AccessToken> responseEntity = tokenEndpoint.postAccessToken(authentication, parameters);
            log.info("login  end ......");
            return ResponseVo.getSuccessResult(responseEntity.getBody());
        } catch (InvalidGrantException e) {
            log.error("login error  用户名密码不正确 ....", e);
            return ResponseVo.getFailureResult("用户名密码不正确");
        } catch (Exception e) {
            log.error("login error ....", e);
            return ResponseVo.getFailureResult(e.getMessage());
        }
    }

    /**
     * 用户信息校验
     *
     * @return 用户信息
     */
    @RequestMapping("/user")
    public UserDO user() {
        HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDO userDO = null;
        if (authentication.isClientOnly()) {
            //TODO  根据client 与scope查询client是否存在,存在则返回信息
            userDO = new UserDO(1, Objects.toString(principal), Objects.toString(principal), authorities, true);
        } else {
            if (principal instanceof UserDetails) {
                userDO = new UserDO(1, "system", "123456", authorities, true);
                return userDO;
            }
            if (principal instanceof Principal) {
                userDO = new UserDO(1, ((Principal) principal).getName(), "123456", authorities, true);
                return userDO;
            }
        }
        return userDO;
//        return authentication.getPrincipal();
    }

    /**
     * @param accesstoken accesstoken
     * @return true/false
     */
    @PostMapping("/layout")
    public ResponseVo removeToken(String accesstoken) {
        OAuth2AccessToken accessToken = jwtTokenStore.readAccessToken(accesstoken);
        boolean flag = consumerTokenServices.revokeToken(accessToken.getValue());
        jwtTokenStore.removeAccessToken(accessToken);
//        tokenStore.removeRefreshToken(accessToken.getRefreshToken());
        return ResponseVo.getSuccessResult(flag);
    }

    /**
     * @param refreshToken
     * @param accessToken
     * @return
     */
    @RequestMapping(value = "/refreshToken")
    public ResponseVo<String> refreshToken(HttpServletRequest request, String refreshToken, String accessToken) {
        String token = null;

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            if (header == null || !header.startsWith(BEARER_TOKEN_TYPE)) {
                throw new UnapprovedClientAuthenticationException("请求头中无client信息");
            }
            String[] tokens = RequestUtil.extractAndDecodeHeader(header);
            assert tokens.length == 2;

            String clientId = tokens[0];
            String clientSecret = tokens[1];

            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

            if (clientDetails == null) {
                throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
            } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
                throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
            }

            Map<String, Object> map = new HashMap<>(2);
            map.put("grant_type", "refresh_token");
            map.put("refresh_token", refreshToken);

            //插件式配置请求参数（网址、请求参数、编码、client）
            Header[] headers = HttpHeader.custom().contentType(HttpHeader.Headers.APP_FORM_URLENCODED).authorization(request.getHeader(HttpHeaders.AUTHORIZATION)).build();
            HttpConfig config = HttpConfig.custom().headers(headers).url(refreshTokenUrl).map(map);
            token = HttpClientUtil.post(config);
            JSONObject jsonObj = JSON.parseObject(token);
            String accessTokenNew = (String) jsonObj.get("access_token");
            String refreshTokenNew = (String) jsonObj.get("refresh_token");
        } catch (Exception e) {
            ResponseVo.getFailureResult();
        }
        return ResponseVo.getSuccessResult(token);
    }
}