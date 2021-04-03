package com.mindtree.MovieAssignment.service;

import java.util.List;
import java.util.OptionalDouble;

import com.mindtree.MovieAssignment.dto.MovieDto;
import com.mindtree.MovieAssignment.entity.Movie;
import com.mindtree.MovieAssignment.exceptions.serviceexceptions.MovieServiceException;

public interface MovieService {

	MovieDto addMovieDetails(MovieDto dto) throws MovieServiceException;

	List<Movie> getMovieAndActorDetails(int budget) throws MovieServiceException;

	Movie getAllMovieDetailsByName(String name) throws MovieServiceException;

	Movie getAllMovieDetailsById(int id) throws MovieServiceException;

	OptionalDouble getActorAvgBudget(String name) throws MovieServiceException;

	OptionalDouble getMovieAvgBudget() throws MovieServiceException;

	Movie updateDetails(Movie movie, int id);

	String deleteDetailsById(int id);

}
