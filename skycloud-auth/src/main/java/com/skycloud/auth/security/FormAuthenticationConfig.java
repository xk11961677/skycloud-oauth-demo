//package com.skycloud.auth.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
///**
// * 表单登录配置
// *
// */
//@Component
//public class FormAuthenticationConfig {
//
//	/**
//	 * The Sc authentication success handler.
//	 */
//	protected final AuthenticationSuccessHandler scAuthenticationSuccessHandler;
//
//	/**
//	 * The Pc authentication failure handler.
//	 */
//	protected final AuthenticationFailureHandler scAuthenticationFailureHandler;
//
//	/**
//	 * Instantiates a new Form authentication config.
//	 *
//	 */
//	@Autowired
//	public FormAuthenticationConfig(AuthenticationSuccessHandler scAuthenticationSuccessHandler, AuthenticationFailureHandler scAuthenticationFailureHandler) {
//		this.scAuthenticationSuccessHandler = scAuthenticationSuccessHandler;
//		this.scAuthenticationFailureHandler = scAuthenticationFailureHandler;
//	}
//
//	/**
//	 * Configure.
//	 *
//	 * @param http the http
//	 *
//	 * @throws Exception the exception
//	 */
//	public void configure(HttpSecurity http) throws Exception {
//		http.formLogin()
//				.loginPage("/authentication/require")
//				.loginProcessingUrl("/authentication/form")
//				.successHandler(scAuthenticationSuccessHandler)
//				.failureHandler(scAuthenticationFailureHandler);
//	}
//
//}
