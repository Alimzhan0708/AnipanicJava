package ru.alimzhan.anipanic.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.alimzhan.anipanic.models.Movie;
import ru.alimzhan.anipanic.models.MovieDto;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mappings({
            @Mapping(target="id", source="movieId")
    })
    Movie fromDto(MovieDto movieDto);

    @Mappings({
            @Mapping(target="movieId", source="id")
    })
    MovieDto toDto(Movie movie);

    Iterable<MovieDto> toDtos(Iterable<Movie> movies);
}
