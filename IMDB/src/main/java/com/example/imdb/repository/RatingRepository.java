package com.example.imdb.repository;

import com.example.imdb.model.Movie;
import com.example.imdb.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Rating findByMovie(Movie movie);

    List<Rating> findByAvgRatingBetween(Float min, Float max);

    List<Rating> findByNumVotesBetween(int min, int max);
}
