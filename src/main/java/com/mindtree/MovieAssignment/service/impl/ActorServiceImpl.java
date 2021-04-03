package com.mindtree.MovieAssignment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mindtree.MovieAssignment.dto.ActorDto;
import com.mindtree.MovieAssignment.dto.MovieDto;
import com.mindtree.MovieAssignment.entity.Actor;
import com.mindtree.MovieAssignment.entity.Movie;
import com.mindtree.MovieAssignment.exceptions.serviceexceptions.ActorServiceException;
import com.mindtree.MovieAssignment.repository.ActorRepository;
import com.mindtree.MovieAssignment.repository.MovieRepository;
import com.mindtree.MovieAssignment.service.ActorService;

@Service
public class ActorServiceImpl implements ActorService {
	@Autowired
	private ActorRepository actorRep;
	@Autowired
	private MovieRepository movieRep;
	static ModelMapper mapper = new ModelMapper();

	@Override
	public ActorDto addActorDetails(ActorDto dto, String name) throws ActorServiceException {
		Actor actor = mapper.map(dto, Actor.class);
		Movie m;
		try {
			m = movieRep.findByMovieName(name);
			// Finding the Actor name by using JPQL in actorRep
			// Actor a = actorRep.findByName(dto.getActorName());
			// if (a != null) {
			// Throwing the custom Exception
			// throw new ActorNameAlreadyExistsException("Actor Name already exists in
			// database");
			// }
			actor.setMovie(m);
			actor = actorRep.save(actor);
			List<Actor> actorList = new ArrayList<Actor>();
			actorList.add(actor);
			m.setActors(actorList);
			movieRep.save(m);
			return mapper.map(actor, ActorDto.class);
		} catch (DataAccessException e) {
			throw new ActorServiceException(e.getMessage());
		}

	}

	@Override
	public List<MovieDto> getAllMovieDetailsByActor(String name) throws ActorServiceException {
		List<Actor> actorList = actorRep.findAll();
		List<MovieDto> movieDtoList = new ArrayList<MovieDto>();
		// Filter the actor Name by using Java 8 feature Filter and Stream
		actorList = actorList.stream().filter(x -> x.getActorName().equalsIgnoreCase(name))
				.collect(Collectors.toList());
		for (Actor actor : actorList) {
			movieDtoList.add(mapper.map(actor.getMovie(), MovieDto.class));
		}
		return movieDtoList;
	}

}
