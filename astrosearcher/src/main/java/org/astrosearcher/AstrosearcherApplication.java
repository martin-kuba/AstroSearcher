package org.astrosearcher;

import org.astrosearcher.classes.constants.AppConfig;
import org.astrosearcher.utilities.SearchEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class AstrosearcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(AstrosearcherApplication.class, args);
	}

	@Scheduled(fixedDelay = 250)
	void freeTimeQuantum() {

		synchronized (SearchEngine.class) {
			if (AppConfig.DEBUG && AppConfig.DEBUG_CORE) {
				System.out.println("[ Time Quantum ] ::: time quantum freed");
			}

			SearchEngine.timeQuantumUsed = false;
			SearchEngine.class.notify();
		}
	}
}
