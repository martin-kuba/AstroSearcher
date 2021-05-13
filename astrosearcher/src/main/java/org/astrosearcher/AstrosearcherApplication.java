package org.astrosearcher;

import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.utilities.MASTSearchEngine;
import org.astrosearcher.utilities.SearchEngine;
import org.astrosearcher.utilities.SimbadSearchEngine;
import org.astrosearcher.utilities.VizierSearchEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.LocalTime;
import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class AstrosearcherApplication {

	private static final Logger log = LoggerFactory.getLogger(AstrosearcherApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AstrosearcherApplication.class, args);
	}

	@Scheduled(fixedDelay = 1000 / (Limits.MAST_MAX_QPS - 1))
	void freeMastTimeQuantum() {

		synchronized (MASTSearchEngine.class) {
			MASTSearchEngine.setTimeQuantumUsed(false);
		}

		synchronized (SearchEngine.class) {
			if (AppConfig.DEBUG_SCHEDULE) {
				log.debug("Notifying SearchEngine class...");
			}
			SearchEngine.class.notify();
		}

	}

	@Scheduled(fixedDelay = 1000 / (Limits.SIMBAD_MAX_QPS - 1))
	void freeSimbadTimeQuantum() {

		synchronized (SimbadSearchEngine.class) {
			SimbadSearchEngine.setTimeQuantumUsed(false);
		}

		synchronized (SearchEngine.class) {
			if (AppConfig.DEBUG_SCHEDULE) {
				log.debug("Notifying SearchEngine class...");
			}
			SearchEngine.class.notify();
		}
	}

	@Scheduled(fixedDelay = 1000 / (Limits.VIZIER_MAX_QPS - 1))
	void freeVizierTimeQuantum() {

		synchronized (VizierSearchEngine.class) {
			VizierSearchEngine.setTimeQuantumUsed(false);
		}

		synchronized (SearchEngine.class) {
			if (AppConfig.DEBUG_SCHEDULE) {
				log.debug("Notifying SearchEngine class...");
			}
			SearchEngine.class.notify();
		}
	}

	@Bean(name = "threadPoolTaskExecutor")
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(8);
		executor.setMaxPoolSize(8);
		executor.setQueueCapacity(50);
		executor.setThreadNamePrefix("AsynchThread::");
		executor.initialize();
		return executor;
	}
}
