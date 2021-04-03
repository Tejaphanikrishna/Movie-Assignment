package com.mindtree.MovieAssignment.dto;

public class ActorDto {
	private int actorId;
	private String actorName;
	private String actorGender;
	private double budget;

	public ActorDto() {
		super();
	}

	public ActorDto(int actorId, String actorName, String actorGender, double budget) {
		super();
		this.actorId = actorId;
		this.actorName = actorName;
		this.actorGender = actorGender;
		this.budget = budget;
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

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

}
