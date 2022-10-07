package com.jglive.www.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationProvider UserAuthProvider;
	

	// 비밀번호 BCrypt 암호화를 위한 Bean 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }   
    
	
    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web.ignoring().antMatchers("/h2-console/**");
    }
    
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests()
				// permitAll() 메소드는 어떠한 보안 요구 없이 요청을 허용해준다.
				.antMatchers("/","/userSign","userLogin").permitAll()
				.antMatchers("/userInfo").hasAuthority("ROLE_USER");
			http.formLogin()
				// 로그인 폼 사용
					// 로그인 URL 설정 이 설정이 없으면 스프링 시큐리티에서 제공해주는 로그인 페이지로 이동
					.loginPage("/userLogin")				//로그인 페이지
					.defaultSuccessUrl("/")					//로그인 성공시 이동									
					.failureUrl("/userLogin") 				//로그인 실패시 이동
					.usernameParameter("id")				//아이디 파라미터명 설정
		            .passwordParameter("pw")				//패스워드 파라미터명 설정
					.loginProcessingUrl("/login")       	//form url
					.permitAll();

					// 로그아웃 관련 설정
			http.logout()
				// 로그아웃을 요청할 URL
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.deleteCookies("JSESSIONID", "remember-me")
					.invalidateHttpSession(true)
					// 로그아웃 성공시 보낼 페이지 설정
					.permitAll();
	}
	
    /**
     * 로그인 인증 처리 메소드
     * @param auth
     * @throws Exception
     */
	
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(UserAuthProvider);
    }
    
    @Bean
    public AuthenticationSuccessHandler LoginSuccessHandler() {
    	return new LoginSuccessHandler();
    }
    

}
