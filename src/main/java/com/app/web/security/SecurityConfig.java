package com.app.web.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.app.web.entities.Usuario;
import com.app.web.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
public class SecurityConfig {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	@SuppressWarnings({ "deprecation", "removal" })
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()

				.requestMatchers("/registro**").permitAll()
				.requestMatchers("/login**").permitAll()
				.requestMatchers("/img/**").permitAll()
				.requestMatchers("/css/**").permitAll()
				.requestMatchers("/js/**").permitAll()
				.requestMatchers("/styles/**").permitAll()
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				.requestMatchers("/cliente/**").hasAuthority("CLIENTE")
				.requestMatchers("/entrenador/**").hasAuthority("ENTRENADOR")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/BodasyEventos")
				.successHandler((request, response, authentication) -> {
					redirectAfterLogin(authentication, response, request);
				})
				.permitAll()
				.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll();

		return http.build();
	}

	private void redirectAfterLogin(Authentication authentication, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		String username = authentication.getName();
		Usuario usuario = usuarioRepository.findByEmail(username);
		if (usuario != null) {
			HttpSession session = request.getSession();
			session.setAttribute("usuarioId", usuario.getId());
			response.sendRedirect("/admin");
			return;
		}
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}