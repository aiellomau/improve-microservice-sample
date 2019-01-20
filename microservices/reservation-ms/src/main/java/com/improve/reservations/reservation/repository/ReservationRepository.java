package com.improve.reservations.reservation.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.improve.reservations.reservation.model.Reservation;
import com.improve.reservations.reservation.model.ReservationStatus;

@RepositoryRestResource(collectionResourceRel = "reservation", path = "reservation")
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {

	List<Reservation> findByArrivalDateGreaterThanEqualAndDepartureDateLessThanEqual(Date from, Date to);

	List<Reservation> findByArrivalDateGreaterThanEqualAndDepartureDateLessThanEqualAndStatus(Date from, Date to,
			ReservationStatus status);

	List<Reservation> findByArrivalDateGreaterThanEqualAndDepartureDateLessThanEqualAndStatusAndCampsiteId(Date from,
			Date to, ReservationStatus status, Long campsiteId);

}
