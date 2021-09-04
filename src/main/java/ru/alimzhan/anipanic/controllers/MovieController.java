package ru.alimzhan.anipanic.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alimzhan.anipanic.mappers.MovieMapper;
import ru.alimzhan.anipanic.models.Movie;
import ru.alimzhan.anipanic.models.MovieDto;
import ru.alimzhan.anipanic.repositories.EpisodeRepository;
import ru.alimzhan.anipanic.repositories.MovieRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class MovieController {

    Logger logger = LoggerFactory.getLogger(MovieController.class);

    private MovieRepository movieRepository;
    private EpisodeRepository episodeRepository;
    private Environment environment;

    @Autowired
    public MovieController(
            MovieRepository movieRepository,
            EpisodeRepository episodeRepository,
            Environment environment
    ) {
        this.movieRepository = movieRepository;
        this.episodeRepository = episodeRepository;
        this.environment = environment;
    }

    @GetMapping
    public ResponseEntity<Iterable<MovieDto>> getMovies() {
        Iterable<Movie> movies = movieRepository.findAllByOrderByIdAsc();
        Iterable<MovieDto> moviesDtos = MovieMapper.INSTANCE.toDtos(movies);
        logger.info("logging");
        System.out.println(environment.getProperty("spring.datasource.url"));
        return ResponseEntity.ok(moviesDtos);
    }

    @GetMapping("{movieId}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable String movieId) {
        Optional<Movie> movie = movieRepository.findById(Integer.parseInt(movieId));
        if(movie.isPresent()) {
            return ResponseEntity.ok(MovieMapper.INSTANCE.toDto(movie.get()));
        }
        return new ResponseEntity("Bad request", HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public String createMovie(@Valid @RequestBody MovieDto movieDto) {
        Movie movie = MovieMapper.INSTANCE.fromDto(movieDto);
        movieRepository.save(movie);
        return "Saved";
    }

    @PutMapping
    public String updateMovie(@Valid @RequestBody MovieDto movieDto ) {
        Optional<Movie> movieOptional = movieRepository.findById(movieDto.getMovieId());
        if(movieOptional.isPresent()) {
            Movie movie = MovieMapper.INSTANCE.fromDto(movieDto);
            movieRepository.save(movie);
        }
        return "Error";
    }
}
