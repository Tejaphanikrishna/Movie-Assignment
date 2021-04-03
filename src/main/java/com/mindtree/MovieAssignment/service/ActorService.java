package com.mindtree.MovieAssignment.service;

import java.util.List;

import com.mindtree.MovieAssignment.dto.ActorDto;
import com.mindtree.MovieAssignment.dto.MovieDto;
import com.mindtree.MovieAssignment.entity.Actor;
import com.mindtree.MovieAssignment.exceptions.serviceexceptions.ActorServiceException;

public interface ActorService  {

	ActorDto addActorDetails(ActorDto dto, String name)throws ActorServiceException;

	List<MovieDto> getAllMovieDetailsByActor(String name)throws ActorServiceException;

}
