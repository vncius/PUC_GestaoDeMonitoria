package br.com.puc.monitoria;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Controller
public class IndexController {
	
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
	
	@RequestMapping("/")
	public String login() {
		return "login";
	}
	
	@RequestMapping("**/Principal")
	public String principal() {
		return "/base/layout";
	}
	
	@GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }
}
