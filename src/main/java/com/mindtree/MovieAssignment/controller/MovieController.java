package com.mindtree.MovieAssignment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.MovieAssignment.dto.MovieDto;
import com.mindtree.MovieAssignment.entity.Movie;
import com.mindtree.MovieAssignment.exceptions.controllerexception.ControllerException;
import com.mindtree.MovieAssignment.exceptions.serviceexceptions.MovieServiceException;
import com.mindtree.MovieAssignment.service.MovieService;

@RestController
@RequestMapping("/Movie")
public class MovieController {
	@Autowired
	private MovieService movieService;

//	@PostMapping(path = "/addMovie", consumes = { MediaType.APPLICATION_XML_VALUE,
//			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
//					MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<?> addDetails(@RequestHeader(value = "Accept") String acceptHeader,
//			@RequestHeader(value = "Accept-Language") String languageHeader,
//			@RequestHeader(value = "Connection") String connectionHeader,
//			@RequestHeader(value = "Authorization") String authorizationHeader,
//			@RequestHeader(value = "Content-Type") String contentTypeHeader,
//			@RequestHeader(value = "Content-Length") String contentLengthHeader, @RequestBody @Valid MovieDto dto)
//			throws ControllerException {
//		MovieDto m = null;
//		try {
//			m = movieService.addMovieDetails(dto);
//			return new ResponseEntity<>(m, HttpStatus.ACCEPTED);
//		} catch (MovieServiceException e) {
//			throw new ControllerException(e.getMessage());
//		}
//	}

	// 1. Adding Movie Details
	@PostMapping("/addMovie")
	public ResponseEntity<?> addDetails(@RequestBody @Valid MovieDto dto) throws ControllerException {
		MovieDto m = null;
		try {
			m = movieService.addMovieDetails(dto);
			return new ResponseEntity<MovieDto>(m, HttpStatus.ACCEPTED);
		} catch (MovieServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	// 2. Getting Movie Details by passing Movie Id as Path Variable
	@GetMapping("/getMovieDetailsById/{id}")
	public Movie getMovieDetails(@PathVariable int id) throws ControllerException {
		try {
			return movieService.getAllMovieDetailsById(id);
		} catch (MovieServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	// 3.Getting actor and movie Details by giving Budget as Path Variable
	@GetMapping("/getMovieAndActorDetails/{budget}")
	public ResponseEntity<?> getDetails(@PathVariable int budget) throws ControllerException {
		List<Movie> movie = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("movie", "Bahubali");
			headers.add(HttpHeaders.WARNING, "Validated");
			headers.add(HttpHeaders.AGE, "22");
			movie = movieService.getMovieAndActorDetails(budget);
			return new ResponseEntity<>(movie, headers, HttpStatus.ACCEPTED);
		} catch (MovieServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	// 4. Getting Movie Details by passing Movie name as Path Variable
	@GetMapping("getMovieDetails/{name}")
	public Movie getMovieDetails(@PathVariable String name) throws ControllerException {
		try {

			return movieService.getAllMovieDetailsByName(name);
		} catch (MovieServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	// 5.Getting the average actor budget by passing Movie name as Path variable
	@GetMapping("/get/{name}")
	public OptionalDouble getActorBudget(@PathVariable String name) throws ControllerException {
		try {
			return movieService.getActorAvgBudget(name);
		} catch (MovieServiceException e) {
			throw new ControllerException(e.getMessage());
		}

	}

	// 6.Getting the average Movie budget
	@GetMapping("/getMovieBudget")
	public OptionalDouble getMovieBudget() throws ControllerException {
		try {
			return movieService.getMovieAvgBudget();
		} catch (MovieServiceException e) {
			throw new ControllerException(e.getMessage());
		}

	}

	@PutMapping("/update/{id}")
	public Movie updateDetails(@RequestBody Movie movie, @PathVariable int id) {
		return movieService.updateDetails(movie, id);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		return movieService.deleteDetailsById(id);
	}

}
