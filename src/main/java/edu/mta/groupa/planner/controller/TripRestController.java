package edu.mta.groupa.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.mta.groupa.planner.ItemIdMismatchException;
import edu.mta.groupa.planner.ItemNotFoundException;
import edu.mta.groupa.planner.model.Trip;
import edu.mta.groupa.planner.repository.TripRepository;

/**
 * The Trip controller is a REST API interface to trip model objects. It uses
 * standard JSON and HTTP GET, POST, DELETE and PUT methods to create, update
 * and/or delete Trip objects.
 * 
 * Return a list of all trips		GET /api/trips
 * Find a trip by title				GET /api/trips/{tripTitle}
 * Find a trip by id				GET /api/trips/{id}
 * Create a new trip				POST /api/trips
 * 										body: { "name": "My Cool Trip", ... }
 * Delete a trip					DELETE /api/trips/{id}
 * Update a trip					PUT /api/trips/{id}
 * 										body: { "name": "My Awesome Trip", ... }
 * @author Maryse
 *
 */
@RestController
@RequestMapping("/api/trips")
public class TripRestController {

	@Autowired
	private TripRepository tripRepository;

	@GetMapping
	public Iterable<Trip> findAll() {
		return tripRepository.findAll();
	}

	@GetMapping("/title/{tripTitle}")
	public List<Trip> findByTitle(@PathVariable String tripTitle) {
		return tripRepository.findByTitle(tripTitle);
	}

	@GetMapping("/{id}")
	public Trip findOne(@PathVariable Long id) {
		return tripRepository.findById(id).orElseThrow(ItemNotFoundException::new);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Trip create(@RequestBody Trip trip) {
		return tripRepository.save(trip);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		tripRepository.findById(id).orElseThrow(ItemNotFoundException::new);
		tripRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public Trip updateTrip(@RequestBody Trip book, @PathVariable Long id) {
		if (book.getId() != id) {
			throw new ItemIdMismatchException();
		}
		tripRepository.findById(id).orElseThrow(ItemNotFoundException::new);
		return tripRepository.save(book);
	}
}