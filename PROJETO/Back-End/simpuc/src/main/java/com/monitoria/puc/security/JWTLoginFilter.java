package com.monitoria.puc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitoria.puc.model.ModelUsuario;

/*ESTABELECE O GERENCIADOR DO TOKEN*/
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	/*CONFIGURAÇÃO DO GERENCIADOR DE AUTENTICACAO*/
	protected JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
		
		super(new AntPathRequestMatcher(url));/*OBRIGA AUTENTICAR URL*/
		
		setAuthenticationManager(authenticationManager);/*GERENCIADOR DE AUTENTICACAO*/
	}

	/*RETORNA USUARIO AO PROCESSAR A AUTENTICACAO*/
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		ModelUsuario user = new ObjectMapper().readValue(request.getInputStream(), ModelUsuario.class);
		
		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(user.getMatricula(), user.getSenha()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		new JWTTokenAutenticacaoService().addAuthentication(response, authResult.getName());
	}
}
