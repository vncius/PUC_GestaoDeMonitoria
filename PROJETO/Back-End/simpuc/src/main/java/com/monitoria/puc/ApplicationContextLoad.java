package com.monitoria.puc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@Component
public class ApplicationContextLoad implements ApplicationContextAware {

	@Autowired
	private static ApplicationContext applicationContext;
	
	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
