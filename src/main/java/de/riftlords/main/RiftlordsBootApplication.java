package de.riftlords.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
public class RiftlordsBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiftlordsBootApplication.class, args);
	}
//	<bean id="templateResolver"
//		      class="org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver">
//		  <property name="suffix" value=".html" />
//		  <property name="templateMode" value="HTML5" />
//		</bean>
//	@Bean
//	public TemplateResolver templateResolver(){
//	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//	    templateResolver.setPrefix("/templates/");
//	    templateResolver.setSuffix(".html");
//	    templateResolver.setTemplateMode("HTML5");
//	    return templateResolver;
//	}
//
//	@Bean
//	public SpringTemplateEngine templateEngine(){
//	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//	    templateEngine.setTemplateResolver(templateResolver());
//	    templateEngine.addDialect(new LayoutDialect());
//	    return templateEngine;
//	}
//
//	@Bean
//	public ViewResolver viewResolver(){
//	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver() ;
//	    viewResolver.setTemplateEngine(templateEngine());
//	    viewResolver.setOrder(1);
//	    return viewResolver;
//	}
}
