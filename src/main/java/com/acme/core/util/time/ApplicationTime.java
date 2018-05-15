package com.acme.core.util.time;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApplicationTime {

	private ApplicationTime() {
	}

	public static LocalDate dateNow() {
		return LocalDate.now(ApplicationClock.getCurrentClock());
	}

	public static LocalDateTime dateTimeNow() {
		return LocalDateTime.now(ApplicationClock.getCurrentClock());
	}
}
