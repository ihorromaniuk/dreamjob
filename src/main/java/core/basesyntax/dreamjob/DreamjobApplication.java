package core.basesyntax.dreamjob;

import core.basesyntax.dreamjob.service.TechStarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DreamjobApplication {

    @Autowired
    private TechStarsService techStarsService;

	public static void main(String[] args) {
		SpringApplication.run(DreamjobApplication.class, args);
	}

    @Bean
    public CommandLineRunner getCommandLineRunner() {
        return args -> techStarsService.getAllJobsAndSaveToDb();
    }
}
