package br.com.alura.screenmatch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonData(
    @JsonAlias("Season") int number, 
    @JsonAlias("Episodes") List<EpisodeData> episodes
    ) {}
