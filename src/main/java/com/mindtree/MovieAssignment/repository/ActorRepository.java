package com.mindtree.MovieAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mindtree.MovieAssignment.entity.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	@Query("select a from Actor a where a.actorName=?1")
	Actor findByName(String name);

}
