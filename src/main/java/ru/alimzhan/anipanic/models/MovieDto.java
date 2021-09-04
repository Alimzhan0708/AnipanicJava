package ru.alimzhan.anipanic.models;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class MovieDto {

    private int movieId;
    @NotBlank(message = "russianName is required")
    @Length(max = 255)
    private String russianName;
    @NotBlank(message = "russianName is required")
    @Length(max = 255)
    private String englishName;
    @NotBlank(message = "russianName is required")
    @Length(max = 255)
    private String description;

    public MovieDto() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getRussianName() {
        return russianName;
    }

    public void setRussianName(String russianName) {
        this.russianName = russianName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
