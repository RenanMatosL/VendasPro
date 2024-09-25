package br.com.empresa.vendapro.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = { "br.com.empresa.vendapro" })
public class VendaproApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendaproApplication.class, args);
	}

}
