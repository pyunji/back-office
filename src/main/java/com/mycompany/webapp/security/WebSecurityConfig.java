package com.mycompany.webapp.security;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("configure(HttpSecurity http) 실행");
		// 로그인 방식 설정
		http.formLogin()
		.loginPage("/login")	// default: /login(GET)
		.usernameParameter("mid")	// default: username
		.passwordParameter("mpassword") // default: password
		.loginProcessingUrl("/login") // default: /login(POST) .디폴트 로그인 요청 경로. 요청 매핑 메서드 만들 필요 없음. 시큐리티가 정해놓은 경로. POST 방식으로 login 경로를 요청해야한다
		.defaultSuccessUrl("/") // 로그인 성공 시 이동할 경로(리다이렉트)
		.failureUrl("/login") // default: /login?error . 로그인 실패 시 이동할 경로
		;
		
		// 로그아웃 설정
		http.logout()
			.logoutUrl("/logout")	// default: /logout
			.logoutSuccessUrl("/");
		
		// URL 권한 설정
		http.authorizeRequests()
//			.antMatchers("/cart/**").hasAuthority("ROLE_USER")
			.antMatchers("/login").permitAll()
			.antMatchers("/error/*").permitAll()
			.antMatchers("/**").hasAuthority("ROLE_ADMIN");
		
		// 권한 없음(403)일 경우 이동할 경로 설정
		http.exceptionHandling().accessDeniedPage("/error/403");
		
		// CSRF 비활성화
		http.csrf().disable();
		
		// CORS 설정 활성화
		http.cors();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("configure(AuthenticationManagerBuilder auth) 실행");
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		auth.authenticationProvider(provider);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		log.info("configure(WebSecurity web) 실행");
		// 권한 계층 설정
		DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
		handler.setRoleHierarchy(roleHierarchyImpl());
		web.expressionHandler(handler);
		// 스프링 시큐리티의 프로세스를 거치지 않는 정적 파일들 정의
		web.ignoring()
			.antMatchers("/bootstrap/**");
			
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		return passwordEncoder;
	}
	
	// 권한 계층을 참조하기 위해 HttpSecurity에서 사용하기 때문에 관리빈으로 반드시 등록해야함
	@Bean
	public RoleHierarchyImpl roleHierarchyImpl() {
		RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
		roleHierarchyImpl.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
		return roleHierarchyImpl;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	//springsecurity가 실행되면 이 객체를 찾을 것이다.
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration conf = new CorsConfiguration();
		//모든 요청 사이트 허용
		conf.addAllowedOrigin("*");
		//모든 요청 방식 허용
		conf.addAllowedMethod("*");
		//모든 요청 헤드 허용
		conf.addAllowedHeader("*");//"Authorization"라고 넣어도 된다. //"*"는 모든 header를 허용하겠다는 것이다.
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", conf);
		return source;
		
	}
}
