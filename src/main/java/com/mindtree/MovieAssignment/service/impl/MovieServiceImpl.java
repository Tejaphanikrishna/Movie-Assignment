package com.mindtree.MovieAssignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.mindtree.MovieAssignment.dto.MovieDto;
import com.mindtree.MovieAssignment.entity.Actor;
import com.mindtree.MovieAssignment.entity.Movie;
import com.mindtree.MovieAssignment.exceptions.serviceexceptions.MovieAlreadyExistsException;
import com.mindtree.MovieAssignment.exceptions.serviceexceptions.MovieServiceException;
import com.mindtree.MovieAssignment.exceptions.serviceexceptions.NoSuchIdFoundException;
import com.mindtree.MovieAssignment.exceptions.serviceexceptions.NoSuchMovieNameFoundException;
import com.mindtree.MovieAssignment.repository.MovieRepository;
import com.mindtree.MovieAssignment.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	private MovieRepository movieRep;
	static ModelMapper mapper = new ModelMapper();

	@Override
	public MovieDto addMovieDetails(MovieDto dto) throws MovieServiceException {
		try {
			Movie movie = mapper.map(dto, Movie.class);
			// Finding the movie name by using JPQL
			Movie m = movieRep.findByMovieName(dto.getMovieName());
			if (m != null) {
				// Throwing Custom Exception
				throw new MovieAlreadyExistsException("Movie is Already exists");
			}
			movie = movieRep.save(movie);
			return mapper.map(movie, MovieDto.class);

		} catch (DataAccessException e) {
			throw new MovieServiceException(e.getMessage());
		}

	}

	@Override
	public List<Movie> getMovieAndActorDetails(int budget) {
		List<Movie> movieList = movieRep.findAll();
		movieList = movieList.stream().filter(x -> x.getBudgetAmount() > budget).collect(Collectors.toList());
		// Sort the MovieDetails based on their Name in Ascending order by using
		// Comparator
		Collections.sort(movieList, new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
				int res = 0;
				res = o1.getMovieName().compareTo(o2.getMovieName());
				return res;
			}
		});
		return movieList;
	}

	@Override
	public Movie getAllMovieDetailsByName(String name) throws MovieServiceException {
		Movie m = null;
		try {
			// Finding the Movie Name By Using JPQL in MovieRep
			m = movieRep.findByMovieName(name);
			if (m == null) {
				// Throwing Custom Exception
				throw new NoSuchMovieNameFoundException("No such movie found in database");
			} else {
				return m;
			}
		} catch (DataAccessException | NoSuchMovieNameFoundException e) {
			throw new MovieServiceException(e.getMessage());
		}// @Cacheable(value="cache")
	}

	@Override
	@Cacheable(value = "movie-Id-Cache", key = "#id",condition = "True")

	public Movie getAllMovieDetailsById(int id) throws MovieServiceException {
		Movie movie = null;
		try {
			movie = movieRep.findById(id).orElse(null);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (movie == null) {
				throw new NoSuchIdFoundException("Such Id not Exists in Database");
			} else {
				return movie;
			}
		} catch (DataAccessException | NoSuchIdFoundException e) {
			throw new MovieServiceException(e.getMessage());
		}
	}

	@Override
	@CachePut(value = "movie-Id-Cache", key = "#id")
	public Movie updateDetails(Movie movie, int id) {
		Movie m2 = movieRep.findById(id).get();
		m2.setMovieName(movie.getMovieName());
		m2.setBudgetAmount(movie.getBudgetAmount());
		m2 = movieRep.save(m2);
		return m2;
	}

	@Override
	@CacheEvict(value = "movie-Id-Cache", key = "#id")
	public String deleteDetailsById(int id) {
		Movie movie = movieRep.findById(id).get();
		if (movie == null) {
			return "There is no such movie id " + id;
		} else {
			movieRep.delete(movie);
			return "Movie deleted successfully";
		}
	}

	@Override
	public OptionalDouble getActorAvgBudget(String name) throws MovieServiceException {
		Movie m = movieRep.findByMovieName(name);
		List<Actor> actors = new ArrayList<Actor>();
		actors = m.getActors();
//		List<Float> priceList = new ArrayList<>();
//		for (Actor actor : actors) {
//			priceList.add((float) actor.getBudget());
//		}
//		Optional<Float> sumOfPrices = priceList.stream().reduce((a, b) -> a + b);
//		int count = (int) priceList.stream().count();
//		Optional<Float> avg = sumOfPrices.map(x -> x / count);
		OptionalDouble avg1 = actors.stream().mapToDouble(x -> x.getBudget()).average();
		return avg1;
	}

	@Override
	public OptionalDouble getMovieAvgBudget() throws MovieServiceException {
		List<Movie> movieList = movieRep.findAll();
//		List<Float> priceList = new ArrayList<>();
//		for (Movie movie : movieList) {
//			priceList.add((float) movie.getBudgetAmount());
//		}
//		Optional<Float> sumOfPrices = priceList.stream().reduce((a, b) -> a + b);
//		int count = (int) priceList.stream().count();
//		Optional<Float> avg = sumOfPrices.map(x -> x / count);
		OptionalDouble avg1 = movieList.stream().mapToDouble(x -> x.getBudgetAmount()).average();
		return avg1;
	}

}
