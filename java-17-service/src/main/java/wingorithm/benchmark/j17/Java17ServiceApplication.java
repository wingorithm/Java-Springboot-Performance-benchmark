package wingorithm.benchmark.j17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Java17ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Java17ServiceApplication.class, args);
	}

}