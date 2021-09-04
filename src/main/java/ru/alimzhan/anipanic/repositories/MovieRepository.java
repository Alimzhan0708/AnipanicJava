package ru.alimzhan.anipanic.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.alimzhan.anipanic.models.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

    public Iterable<Movie> findAllByOrderByIdAsc();
}
