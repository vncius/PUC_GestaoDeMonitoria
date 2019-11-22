package com.monitoria.puc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.monitoria.puc.service.ImplementacaoUserDetailsService;

/*CLASSE DE MAPEAMENTO DE URL/ENDERECOS/END-POINT BLOQUEIA OU LIBERA ACESSO*/
@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*ATIVANDO A PROTEÇÃO CONTRA USUÁRIO QUE NÃO ESTÃO AUTENTICADOS POR TOKEN*/
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		/*ATIVANDO A RESTRICAO A URL*/
		.disable().authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/index").permitAll()
		/*URL DE LOGOUT - REDIRECIONA O USUARIO APOS DESLOGAR*/
		.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
		/*MAPEIA URL DE LOGOUT E INVALIDA USUARIO*/
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		/*FILTRAR REQUISIÇÕES DE LOGIN PARA AUTENTICACAO*/
		.and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), 
				UsernamePasswordAuthenticationFilter.class)
		/*FILTRA DEMAIS REQUISICOES PARA VERIFICAR PRESENCA DO TOKEN JWT NO HEADER HTTP*/
		.addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* SERVICE QUE CONSULTA USUARIO NO BANCO */
		auth.userDetailsService(implementacaoUserDetailsService)
				/* GERA HASH DA SENHA */
				.passwordEncoder(new BCryptPasswordEncoder());

	}

}
