package com.aurora.webgames;

import java.util.Locale; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebProperties.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import ch.qos.logback.core.encoder.Encoder;

@SpringBootApplication
public class WebgamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebgamesApplication.class, args);
	}
	
	@Bean
	public FixedLocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt","BR"));
	}

}
