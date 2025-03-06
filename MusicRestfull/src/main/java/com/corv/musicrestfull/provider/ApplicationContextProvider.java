package com.corv.musicrestfull.provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * Clase que facilita la obtención de ApplicationContext de forma programática
 * 
 */

public class ApplicationContextProvider implements ApplicationContextAware {
	private static ApplicationContext context;
	private static final Logger LOGGER = LogManager.getLogger(ApplicationContext.class);

	/**
	 * 
	 * Método que retorna ApplicationContext asociado a Spring MVC
	 * 
	 * @return
	 * 
	 */
	public static ApplicationContext getApplicationContext() {

		return context;

	}

	/**
	 * 
	 * Método que define ApplicationContext asociado a Spring MVC
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 * 
	 */
	public void setApplicationContext(ApplicationContext ctx) {
		LOGGER.info("se ha asignado valor al bean ApplicationContextProvider");

		context = ctx;

	}
}

