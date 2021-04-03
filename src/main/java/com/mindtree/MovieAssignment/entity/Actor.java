package com.mindtree.MovieAssignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Actor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Actor_Id")
	private int actorId;
	@Column(name = "Actor_Name")
	private String actorName;
	@Column(name = "Actor_Gender")
	private String actorGender;
	@Column(name = "Actor_Budget")
	private double budget;
	@ManyToOne
	@JsonIgnore
	private Movie movie;

	public Actor() {
		super();
	}


	public Actor(int actorId, String actorName, String actorGender, double budget, Movie movie) {
		super();
		this.actorId = actorId;
		this.actorName = actorName;
		this.actorGender = actorGender;
		this.budget = budget;
		this.movie = movie;
	}


	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getActorGender() {
		return actorGender;
	}

	public void setActorGender(String actorGender) {
		this.actorGender = actorGender;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	public double getBudget() {
		return budget;
	}


	public void setBudget(double budget) {
		this.budget = budget;
	}
	

}
