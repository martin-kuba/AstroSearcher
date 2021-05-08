package org.astrosearcher;

import org.astrosearcher.classes.constants.AppConfig;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.utilities.MASTSearchEngine;
import org.astrosearcher.utilities.SearchEngine;
import org.astrosearcher.utilities.SimbadSearchEngine;
import org.astrosearcher.utilities.VizierSearchEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;

@Configuration
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class AstrosearcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(AstrosearcherApplication.class, args);
	}

	@Scheduled(fixedDelay = 1000 / (Limits.MAST_MAX_QPS + 1))
	void freeMastTimeQuantum() {

		synchronized (MASTSearchEngine.class) {
			MASTSearchEngine.setTimeQuantum(false);
		}

		synchronized (SearchEngine.class) {
			if (AppConfig.DEBUG_SCHEDULE) {
				System.out.println("    " + LocalTime.now() + " ::: [ SearchEngine ]     : Notifying...");
			}
			SearchEngine.class.notify();
		}

	}

	@Scheduled(fixedDelay = 1000 / (Limits.SIMBAD_MAX_QPS + 1))
	void freeSimbadTimeQuantum() {

		synchronized (SimbadSearchEngine.class) {
			SimbadSearchEngine.setTimeQuantum(false);
		}

		synchronized (SearchEngine.class) {
			if (AppConfig.DEBUG_SCHEDULE) {
				System.out.println("    " + LocalTime.now() + " ::: [ SearchEngine ]     : Notifying...");
			}
			SearchEngine.class.notify();
		}
	}

	@Scheduled(fixedDelay = 1000 / (Limits.VIZIER_MAX_QPS + 1))
	void freeVizierTimeQuantum() {

		synchronized (VizierSearchEngine.class) {
			VizierSearchEngine.setTimeQuantum(false);
		}

		synchronized (SearchEngine.class) {
			if (AppConfig.DEBUG_SCHEDULE) {
				System.out.println("    " + LocalTime.now() + " ::: [ SearchEngine ]     : Notifying...");
			}
			SearchEngine.class.notify();
		}
	}
}
