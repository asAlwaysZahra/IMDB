package com.example.imdb.controller;

import com.example.imdb.model.Movie;
import com.example.imdb.model.Rating;
import com.example.imdb.model.requests.MovieRequest;
import com.example.imdb.model.responses.MovieResponse;
import com.example.imdb.model.responses.PersonResponse;
import com.example.imdb.model.responses.RatingResponse;
import com.example.imdb.repository.RatingRepository;
import com.example.imdb.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;
    private RatingRepository ratingRepository;

    @PostMapping
    public ResponseEntity<MovieResponse> addMovie(@RequestBody MovieRequest request) {
        return new ResponseEntity<>(movieService.addMovie(request), HttpStatus.CREATED);
    }

    @PutMapping("/{titleId}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable String titleId,
                                                     @RequestBody MovieRequest request) {
        return new ResponseEntity<>(movieService.updateMovie(titleId, request), HttpStatus.OK);
    }

    @DeleteMapping("/{titleId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String titleId) {
        movieService.deleteMovie(titleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{titleId}")
    public ResponseEntity<MovieResponse> getMovie(@PathVariable String titleId) {
        return new ResponseEntity<>(movieService.getMovieById(titleId), HttpStatus.OK);
    }

    @GetMapping("/{titleId}/directors")
    public ResponseEntity<List<PersonResponse>> getDirectors(@PathVariable String titleId) {
        return new ResponseEntity<>(movieService.getDirectors(titleId), HttpStatus.OK);
    }

    @GetMapping("/{titleId}/actors")
    public ResponseEntity<List<PersonResponse>> getActors(@PathVariable String titleId) {
        return new ResponseEntity<>(movieService.getActors(titleId), HttpStatus.OK);
    }

    @GetMapping("/{titleId}/ratings")
    public ResponseEntity<RatingResponse> getRating(@PathVariable String titleId) {
        return new ResponseEntity<>(movieService.getRating(titleId), HttpStatus.OK);
    }

    @GetMapping("/actor/{id}")
    public ResponseEntity<List<MovieResponse>> getMoviesByActor(@PathVariable String id) {
        return new ResponseEntity<>(movieService.findByActor(id), HttpStatus.OK);
    }

    @GetMapping("/director/{name}")
    public ResponseEntity<List<MovieResponse>> getMoviesByDirector(@PathVariable String name) {
        // todo check this - by name or id?
        return new ResponseEntity<>(movieService.findByDirectors_Name(name), HttpStatus.OK);
    }

    // filters ---------------------------------------------------------------------------------------

    @GetMapping("/title/{title}")
    public ResponseEntity<List<MovieResponse>> getMoviesByTitle(@PathVariable String title) {
        return new ResponseEntity<>(movieService.findByTitleContaining(title), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<MovieResponse>> getMoviesByType(@PathVariable String type) {
        return new ResponseEntity<>(movieService.findByType(type), HttpStatus.OK);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MovieResponse>> getMoviesByGenre(@PathVariable String genre) {
        return new ResponseEntity<>(movieService.findByGenresContaining(genre), HttpStatus.OK);
    }

    @GetMapping("/start-year/{year}")
    public ResponseEntity<List<MovieResponse>> getMoviesByYear(@PathVariable Integer year) {
        return new ResponseEntity<>(movieService.findByStartYear(year), HttpStatus.OK);
    }

    @GetMapping("/end-year/{year}")
    public ResponseEntity<List<MovieResponse>> getMoviesByEndYear(@PathVariable Integer year) {
        return new ResponseEntity<>(movieService.findByEndYear(year), HttpStatus.OK);
    }

    @GetMapping("/start-year/{y1}/{y2}")
    public ResponseEntity<List<MovieResponse>> getMoviesByYear(@PathVariable Integer y1,
                                                               @PathVariable Integer y2) {
        return new ResponseEntity<>(movieService.findByStartYearBetween(y1, y2), HttpStatus.OK);
    }

    @GetMapping("/end-year/{y1}/{y2}")
    public ResponseEntity<List<MovieResponse>> getMoviesByEndYear(@PathVariable Integer y1,
                                                                  @PathVariable Integer y2) {
        return new ResponseEntity<>(movieService.findByEndYearBetween(y1, y2), HttpStatus.OK);
    }

    @GetMapping("/rating-moreThan/{r}")
    public ResponseEntity<List<MovieResponse>> getMoviesByRatingGreaterThan(@PathVariable Float r) {
        System.out.println("rating more than " + r);
        List<MovieResponse> list = ratingRepository.findByAvgRatingBetween(Float.valueOf(r), 10f).stream().map(Rating::getMovie).map(Movie::movieResponse).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/rating-lessThan/{r}")
    public ResponseEntity<List<MovieResponse>> getMoviesByRatingLessThan(@PathVariable Float r) {
        List<MovieResponse> list = ratingRepository.findByAvgRatingBetween(0f, Float.valueOf(r)).stream().map(Rating::getMovie).map(Movie::movieResponse).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/rating-between/{r1}/{r2}")
    public ResponseEntity<List<MovieResponse>> getMoviesByRatingBetween(@PathVariable Float r1,
                                                                        @PathVariable Float r2) {
        List<MovieResponse> list = ratingRepository.findByAvgRatingBetween(r1, r2).stream().map(Rating::getMovie).map(Movie::movieResponse).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/votes/{v1}/{v2}")
    public ResponseEntity<List<MovieResponse>> getMoviesByVotes(@PathVariable Integer v1,
                                                                @PathVariable Integer v2) {
        List<MovieResponse> list = ratingRepository.findByNumVotesBetween(v1, v2).stream().map(Rating::getMovie).map(Movie::movieResponse).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // sort

    @GetMapping("/sort/title")
    public ResponseEntity<List<MovieResponse>> getMoviesSortedByTitle() {
        return new ResponseEntity<>(movieService.orderByTitle(), HttpStatus.OK);
    }

    @GetMapping("/sort/year")
    public ResponseEntity<List<MovieResponse>> getMoviesSortedByYear() {
        return new ResponseEntity<>(movieService.orderByStartYear(), HttpStatus.OK);
    }

    @GetMapping("/sort/rating")
    public ResponseEntity<List<MovieResponse>> getMoviesSortedByRating() {
        return new ResponseEntity<>(movieService.orderByRating(), HttpStatus.OK);
    }

    @GetMapping("/sort/time")
    public ResponseEntity<List<MovieResponse>> getMoviesSortedByTime() {
        return new ResponseEntity<>(movieService.orderByTime(), HttpStatus.OK);
    }
}
