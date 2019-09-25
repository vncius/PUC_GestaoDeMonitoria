package br.com.puc.monitoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// ATIVA RECURSOS PARA USAR JPA
@SpringBootApplication
@EntityScan(basePackages="br.com.puc.monitoria.Entity")
@ComponentScan(basePackages = {"br.com.*"})
@EnableJpaRepositories(basePackages = "br.com.puc.monitoria.repository")
@EnableTransactionManagement
@EnableWebMvc // WEB MVC ATIVA TODOS OS RECURSOS DO MVC PARA DIRECIONAR TELA LOGIN
public class MonitoriaApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(MonitoriaApplication.class, args);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// REDIRECIONA A TELA LOGIN PADRÃO DO SPRING PARA UMA PRÓPRIA
		registry.addViewController("/login").setViewName("/login");
		registry.setOrder(Ordered.LOWEST_PRECEDENCE);
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/static/**",
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }

}
