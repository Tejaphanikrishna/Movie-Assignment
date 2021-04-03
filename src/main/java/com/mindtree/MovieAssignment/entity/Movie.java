package com.mindtree.MovieAssignment.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Movie_Id")
	private int movieId;
	@Column(name = "Movie_Name")
	private String movieName;
	@Column(name = "Movie_Budget")
	private int budgetAmount;
	@OneToMany(mappedBy = "movie")
	private List<Actor> actors;

	public Movie() {
		super();
	}

	public Movie(int movieId, String movieName, int budgetAmount, List<Actor> actors) {
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
