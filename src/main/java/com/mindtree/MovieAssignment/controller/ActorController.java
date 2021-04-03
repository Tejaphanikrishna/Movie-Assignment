package com.mindtree.MovieAssignment.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.MovieAssignment.dto.ActorDto;
import com.mindtree.MovieAssignment.dto.MovieDto;
import com.mindtree.MovieAssignment.exceptions.controllerexception.ControllerException;
import com.mindtree.MovieAssignment.exceptions.serviceexceptions.ActorServiceException;
import com.mindtree.MovieAssignment.service.ActorService;

@RestController
@RequestMapping("/Actor")
public class ActorController {
	@Autowired
	private ActorService actorService;

	// 1. Adding Actor Details
	@PostMapping("/addActor/{name}")
	public ResponseEntity<?> addDetails(@RequestBody ActorDto dto, @PathVariable @NotBlank(message = "Movie name not be blank") String name)
			throws ControllerException {
		ActorDto a = null;
		try {
			a = actorService.addActorDetails(dto, name);
			return new ResponseEntity<ActorDto>(a, HttpStatus.ACCEPTED);
		} catch (ActorServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	// 2.Getting Movie Details by Passing Actor Name as Path Variable
	@GetMapping("/getMovieDetails/{name}")
	public ResponseEntity<?> getDetails(@PathVariable String name) throws ControllerException {
		List<MovieDto> movieList = null;
		try {
			movieList = actorService.getAllMovieDetailsByActor(name);
			return new ResponseEntity<>(movieList, HttpStatus.ACCEPTED);
		} catch (ActorServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

}
