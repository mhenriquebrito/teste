package br.com.alura.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private int season;
    private String title; 
    private int number; 
    private Double review; 
    private LocalDate releaseDate;
    
    public int getSeason() {
        return season;
    }
    public void setSeason(int season) {
        this.season = season;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public double getReview() {
        return review;
    }
    public void setReview(double review) {
        this.review = review;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }


    public Episode(int season, EpisodeData episodeData) {
        this.season = season;
        this.title = episodeData.title();
        this.number = episodeData.number();
        try {
            this.review = Double.parseDouble(episodeData.review());
        } catch (NumberFormatException e) {
            this.review = 0.0;
        }
        try {
            this.releaseDate = LocalDate.parse(episodeData.releaseDate());
        } catch (DateTimeParseException e) {
            this.releaseDate = null;
        }
    }

    @Override
    public String toString() {
        return "S%dE%d - %s - %s. ".formatted(this.season,this.number, this.releaseDate, this.title);
    }

}
