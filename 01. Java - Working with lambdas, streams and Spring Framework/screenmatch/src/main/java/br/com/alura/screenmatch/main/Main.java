package br.com.alura.screenmatch.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.EpisodeData;
import br.com.alura.screenmatch.model.SeasonData;
import br.com.alura.screenmatch.model.SerieData;
import br.com.alura.screenmatch.service.ApiConsume;
import br.com.alura.screenmatch.service.DataConverter;

public class Main {
    private Scanner read = new Scanner(System.in);

    private ApiConsume apiConsume = new ApiConsume();
    private DataConverter converter = new DataConverter();
    private final String API_URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=cfbbc0b3";


    public void showMenu(){
        System.out.println("Digite o nome da série: ");
        String serieName = read.nextLine();

        String json = apiConsume.getData(API_URL + serieName.replace(" ", "+") + API_KEY);
        SerieData serieData = converter.getData(json, SerieData.class);

        System.out.println(serieData);
        
		List<SeasonData> seasons = new ArrayList<>();
		for (int i = 1; i <= serieData.totalSeasons(); i++) {
			json = apiConsume.getData(API_URL + serieName.replace(" ", "+") + "&season="+ i + API_KEY);
            SeasonData seasonData = converter.getData(json, SeasonData.class);
			
            seasons.add(seasonData);
		}

		//seasons.forEach(System.out::println);
        //seasons.forEach(t -> t.episodes().forEach(e -> System.out.println(t.number()+""+e.number()+e.title())));

        List<EpisodeData> episodeData = seasons.stream()
                                        .flatMap(season -> season.episodes().stream())
                                        .collect(Collectors.toList());

        System.out.println("Top 5 episódios: ");
        episodeData.stream()
                    .filter(episode -> !episode.review().equalsIgnoreCase("n/a"))
                    .sorted(Comparator.comparing(EpisodeData::review).reversed())
                    .limit(5)
                    .forEach(episode -> System.out.println(episode));

        List<Episode> episodes = seasons.stream()
                    .flatMap(season -> season.episodes().stream()
                    .map(episode -> new Episode(season.number(), episode)))
                    .collect(Collectors.toList());

        episodes.forEach(System.out::println);

        System.out.println("A partir de que ano você deseja ver os episódios? ");
        int year = read.nextInt();
        read.nextLine();

        LocalDate searchDate = LocalDate.of(year, 1, 1);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodes.stream()
                .filter(episode -> episode.getReleaseDate() != null && episode.getReleaseDate().isAfter(searchDate))
                .forEach(episode -> System.out.println(
                    " Temporada: " + episode.getSeason() +
                    " Episódio: " + episode.getTitle() +
                    " Data lançamento: " + episode.getReleaseDate().format(dateFormatter)
                ));
    }
}
 