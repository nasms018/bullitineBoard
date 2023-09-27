package www.dream.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BullitineBoardApplication {
	public static void main(String[] args) {
		SpringApplication.run(BullitineBoardApplication.class, args);
		
	}
}
