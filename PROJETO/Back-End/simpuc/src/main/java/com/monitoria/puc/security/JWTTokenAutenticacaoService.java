package com.monitoria.puc.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.monitoria.puc.ApplicationContextLoad;
import com.monitoria.puc.model.ModelUsuario;
import com.monitoria.puc.repository.RepositoryUsuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JWTTokenAutenticacaoService {

	/*TEMPO DE VALIDADE DO TOKEN*/
	private static final long EXPIRATION_TIME = 172800000;
	
	/*SENHA UNICA PARA COMPOR AUTENTICACAO*/
	private static final String SECRET = "*--*/345345453/*44&$@!&()_}{`^:";
	
	/*PREFIXO PADRAO DE TOKEN*/
	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";
	
	/*GERA TOKEN E ADICIONA AO CABEÇALHO DA RESPOSTA HTTP*/
	public void addAuthentication(HttpServletResponse response, String username) throws IOException {
		String JWT = Jwts.builder() /*CHAMA O GERADOR DE TOKEN*/
				.setSubject(username) /*ADICIONA USUARIO*/
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /*TEMPO DE EXPIRACAO*/
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); /*COMPACTA E GERA TOKEN*/
		
		/*JUNTA PREFIXO AO TOKEN*/
		String token = TOKEN_PREFIX + "" + JWT;
		
		/*ADICIONA NO CABEÇALHO E NO CORPO HTTP*/
		response.addHeader(HEADER_STRING, token);
		response.getWriter().write("{\"Authorization\": \""+token+"\"}");; /*JSON*/
	}
	
	/*RETORNA O USUARIO VALIDADO COM TOKEN OU CASO CONTRARIO RETORNA NULL*/
	public Authentication getAuthentication(HttpServletRequest request) {
		/**/
		String token = request.getHeader(HEADER_STRING);
		
		if(token != null) {
			/*FAZ A VALIDACAO DO TOKEN DO USUARIO A CADA REQUISICAO*/
			String user = Jwts.parser().setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody().getSubject();
					
			if (user != null ) {
				ModelUsuario usuario = ApplicationContextLoad.getApplicationContext()
						.getBean(RepositoryUsuario.class).findUserByLogin(user);
				
				if (usuario != null) {
					return new UsernamePasswordAuthenticationToken(
							usuario.getMatricula(),
							usuario.getSenha(), 
							usuario.getAuthorities());
				}
			}
		} 
		
		return null; /*NAO AUTORIZADO*/
	}
}
