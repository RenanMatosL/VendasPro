package br.com.empresa.vendapro.conf;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan(basePackages = { "br.com.empresa.vendapro.*" })
public class MensagensConf {

	@Bean(name = "MessageSourcePadrao")
	public MessageSource gerarMessageSource() {
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setBasename("classpath:mensagens/mensagens");
		reloadableResourceBundleMessageSource.setDefaultEncoding("ISO-8859-1");
		reloadableResourceBundleMessageSource.setDefaultLocale(Locale.getDefault());
		return reloadableResourceBundleMessageSource;
	}

	@Bean(name = "localValidatorFactoryBeanPadrao")
	public LocalValidatorFactoryBean recuperarLocalValidatorFactoryBean() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(gerarMessageSource());
		return localValidatorFactoryBean;
	}
}