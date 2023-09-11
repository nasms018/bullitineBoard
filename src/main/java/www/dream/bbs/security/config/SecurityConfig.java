package www.dream.bbs.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final JwtTokenProvider jwtTokenProvider;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.httpBasic().disable() // REST API는 UI를 사용하지 않으므로 기본설정을 비활성화
				.csrf().disable() // REST API는 csrf 보안이 필요 없으므로 비활성화
				// JWT Token 인증방식으로 세션은 필요없으므로 비활성화
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
				.and().authorizeRequests() // 리퀘스트에 대한 사용권한 체크
				/*
				 * .antMatchers("/sign-api/sign-in", "/sign-api/sign-up",
				 * "/sign-api/exception").permitAll() // 가입 및 로그인 주소는 허용
				 */
				 .antMatchers("/sign-api/sign-in").permitAll() // 가입 및 로그인 주소는 허용
				// 중간에 anonymous이 있는 모든 GET 요청은 허용. 되면 좋고 말면 고치고
				.antMatchers("/**/anonymous/**").permitAll() 
				.antMatchers("**exception**").permitAll()
				//.anyRequest().authenticated() // 나머지 요청은 모두 인증이 필요
				.anyRequest().hasRole("manager") // 나머지 요청은 인증된 직원이면 접근 가능
				.and().exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()).and()
				.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
				.and().addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
						UsernamePasswordAuthenticationFilter.class); // JWT Token 필터를 id/password 인증 필터 이전에 추가
	}

	/**
	 * Swagger 페이지 접근에 대한 예외 처리
	 *
	 * @param webSecurity
	 */
	@Override
	public void configure(WebSecurity webSecurity) {
		webSecurity.ignoring().antMatchers("/sign-api/exception");
	}
}