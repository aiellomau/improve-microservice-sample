package com.improve.reservations.reservation.service.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {

	/**
	 * Convert instance from LocalDate to Date instance
	 * 
	 * @param dateToConvert
	 * @return
	 */
	public static Date convert(final LocalDate dateToConvert) {
		return java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Convert instance from Date to LocalDate instance
	 * 
	 * @param dateToConvert
	 * @return
	 */
	public static LocalDate convert(final Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * Return a list of dates
	 * 
	 * @param startDate a LocalDate instance
	 * @param endDate   a LocalDate instance
	 * @return
	 */
	public static List<LocalDate> getDatesBetween(final LocalDate startDate, final LocalDate endDate) {

		final long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i))
				.collect(Collectors.toList());
	}

	/**
	 * Return a list of dates
	 * 
	 * @param startDate a Date instance
	 * @param endDate   a Date instance
	 * @return
	 */
	public static List<LocalDate> getDatesBetween(final Date startDate, final Date endDate) {

		return getDatesBetween(convert(startDate), convert(endDate));

	}

}
