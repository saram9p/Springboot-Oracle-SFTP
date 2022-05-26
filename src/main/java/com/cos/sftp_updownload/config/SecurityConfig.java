package com.cos.sftp_updownload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean // SecurityConfig가 IoC에 등록될 때 @Bean 어노테이션을 읽어서 이 함수를 리턴해서 IoC가 들고있는다. 그러면 나는 DI해서 쓰기만 하면 된다.
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests()
			.antMatchers("/", "/main/**").authenticated() // 해당 주소로 시작하게 되면 인증이 필요하다.
			.anyRequest().permitAll()
			.and()
			//.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.csrf().disable()
			//.and()
			.formLogin()
			.loginPage("/auth/signin") // GET
			.loginProcessingUrl("/auth/signin") // POST -> 스프링 시큐리티가 로그인 프로세스 진행
			.defaultSuccessUrl("/"); // 로그인이 정상적으로 처리가 되면 / 로 가게 한다.
		
		return http.build();
	}
}
