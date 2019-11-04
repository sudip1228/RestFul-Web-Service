package rest;

import java.util.Locale;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;



@SpringBootApplication
//@EnableSwagger2
public class RestfulWebServicesApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplicationRunner.class, args);
	}
	//Spring @Bean Annotation is applied on a method to specify that it returns a bean to be managed by Spring context. 
	//Spring Bean annotation is usually declared in Configuration classes methods.
	//In this case, bean methods may reference other @Bean methods in the same class by calling them directly.
	
	@Bean
	public LocaleResolver localeResolver() {//using internationalization
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);//default locale is USA.
		return localeResolver;
	}
		
	
}