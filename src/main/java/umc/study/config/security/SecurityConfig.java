package umc.study.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity //Spring Security 설정을 활성화시키는 역할 => 우리가 직접 작성한 보안 설정이 Spring Security의 기본 설정보다 우선 적용되게
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests // HTTP 요청에 대한 접근 제어를 설정
                        .requestMatchers("/", "/home", "/signup", "/users/signup", "/css/**").permitAll() // 특정 URL 패턴에 대한 접근 권한을 설정 //permitAll()은 인증 없이 접근 가능한 경로를 지정
                        .requestMatchers("/admin/**").hasRole("ADMIN") // 'ADMIN' 역할을 가진 사용자만 접근 가능하도록 제한
                        .anyRequest().authenticated() //그 외 모든 요청에 대해 인증을 요구
                )
                .formLogin((form) -> form // 폼 기반 로그인에 대한 설정
                        .loginPage("/login") //커스텀 로그인 페이지를 /login 경로로 지정
                        .defaultSuccessUrl("/home", true) //로그인 성공 시 /home으로 리다이렉트
                        .permitAll() //로그인 페이지는 모든 사용자가 접근 가능하도록 설정
                )
                .logout((logout) -> logout //로그아웃 처리에 대한 설정
                        .logoutUrl("/logout") ///logout 경로로 로그아웃을 처리
                        .logoutSuccessUrl("/login?logout") //로그아웃 성공 시 /login?logout으로 리다이렉트
                        .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //비밀번호를 암호화하여 저장
    }
}