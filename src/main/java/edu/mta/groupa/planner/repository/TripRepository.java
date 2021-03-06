package edu.mta.groupa.planner.repository;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.data.repository.CrudRepository;

import edu.mta.groupa.planner.model.Trip;

/**
 * The Trip Repository extends the spring Crud Repository object,
 * supplying create, update, and delete functionality for the Trip
 * object.
 * 
 * @author Maryse
 *
 */
@Entity
public interface TripRepository extends CrudRepository<Trip, Long> {
	List<Trip> findAllByOrderByStartAsc();
	List<Trip> findByTitle(String title);
	List<Trip> findAllByUserID(long userID);
	Trip findByUserIDAndTitle(long userID, String title);
	List<Trip> findAllByUserIDOrderByStartAsc(long userID);
}