package com.skycloud.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skycloud.common.base.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 如果没配置，则返回json格式的响应。
 *
 */
@Slf4j
public class ScLogoutSuccessHandler implements LogoutSuccessHandler {

	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * On logout success.
	 *
	 * @param request        the request
	 * @param response       the response
	 * @param authentication the authentication
	 *
	 * @throws IOException the io exception
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		log.info("退出成功");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(ResponseVo.getFailureResult("退出成功")));
	}

}
