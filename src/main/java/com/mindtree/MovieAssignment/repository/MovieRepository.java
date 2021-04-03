package com.mindtree.MovieAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mindtree.MovieAssignment.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	@Query("select m from Movie m where m.movieName=?1")
	Movie findByMovieName(String name);

}
