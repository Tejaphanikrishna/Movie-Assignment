package com.mindtree.MovieAssignment.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.mindtree.MovieAssignment.entity.Actor;

public class MovieDto {
	@NotNull(message = "Id Should be present")
	private int movieId;
	@NotBlank(message = "Movie name Should not be blank")
	private String movieName;
	@NotNull(message = "Budget amount Should not be Null")
	private int budgetAmount;
	private List<Actor> actors;

	public MovieDto() {
		super();
	}

	public MovieDto(int movieId, String movieName, int budgetAmount, List<Actor> actors) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.budgetAmount = budgetAmount;
		this.actors = actors;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(int budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

}
