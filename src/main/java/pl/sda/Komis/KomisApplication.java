package pl.sda.Komis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.sda.Komis.config.MongoConfig;
import pl.sda.Komis.config.SecurityConfiguration;
import pl.sda.Komis.config.SwaggerConfiguration;

@SpringBootApplication
@Import(value = {SwaggerConfiguration.class, MongoConfig.class, SecurityConfiguration.class})
public class KomisApplication {

	public static void main(String[] args) {
		SpringApplication.run(KomisApplication.class, args);
	}
}
