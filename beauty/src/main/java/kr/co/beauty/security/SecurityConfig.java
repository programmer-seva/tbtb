/*
 * 날짜 : 2023-03-07
 * 이름 : 강중현
 * 내용 : security 보안설정
 */
package kr.co.beauty.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	AccessDeniedHandlerImpl accessDeniedHandler;
	@Autowired
	AuthenticationEntryPointImpl authenticationEntryPoint;
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 자원에 대해서 Security를 적용하지 않음으로 설정
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//사이트 위조 방지 설정 -> 배포시 제거
		http.cors().and().csrf().disable();
		
		//인가(접근권한) 설정
		http.authorizeHttpRequests()
			.requestMatchers("/**").permitAll()
			.requestMatchers("/member/**").permitAll();
				
		//로그인 alert
		http.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler)
			.authenticationEntryPoint(authenticationEntryPoint);
				
		//로그인 설정
		http.formLogin()
			.loginPage("/member/login")
			.defaultSuccessUrl("/")
			.failureUrl("/member/login?success=101")
			.usernameParameter("uid")
			.passwordParameter("pass");
				
		//로그아웃 설정
		http.logout()
			.invalidateHttpSession(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
			.logoutSuccessUrl("/member/login?success=200");
		
		// 자동로그인
		http.rememberMe() // rememberMe 기능 작동함
		  .rememberMeParameter("remember-me") // default: remember-me, checkbox 등의 이름과 맞춰야함
		  .tokenValiditySeconds(3600) // 쿠키의 만료시간 설정(초), default: 14일
		  .alwaysRemember(false) // 사용자가 체크박스를 활성화하지 않아도 항상 실행, default: false
		  .userDetailsService(userDetailsService); // 기능을 사용할 때 사용자 정보가 필요함. 반드시 이 설정 필요함.
		
		
		return http.build();
	}
	
	@Bean
    public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
    
	}
}
