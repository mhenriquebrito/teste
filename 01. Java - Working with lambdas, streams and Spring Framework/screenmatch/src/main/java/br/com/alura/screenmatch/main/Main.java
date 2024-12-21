package br.com.alura.screenmatch.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

		seasons.forEach(System.out::println);

        seasons.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));
    }
}
