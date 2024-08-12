package mindteck.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Mindteck1Application {

	public static void main(String[] args) {
		SpringApplication.run(Mindteck1Application.class, args);
	}

}
