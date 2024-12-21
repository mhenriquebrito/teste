package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.main.Main;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Main main = new Main();
		main.showMenu();
		// ApiConsume apiConsume = new ApiConsume();

		// DataConverter converter = new DataConverter();
		
		// String json = apiConsume.getData("https://www.omdbapi.com/?t=game+of+thrones&apikey=cfbbc0b3");
		// SerieData data = converter.getData(json, SerieData.class);
		// System.out.println(data);

		// json = apiConsume.getData("https://www.omdbapi.com/?t=game+of+thrones&season=1&episode=2&apikey=cfbbc0b3");
		// EpisodeData episodeData = converter.getData(json, EpisodeData.class);
		// System.out.println(episodeData);

	// 	List<SeasonData> seasons = new ArrayList<>();
	// 	for (int i = 1; i <= data.totalSeasons(); i++) {
	// 		json = apiConsume.getData("https://www.omdbapi.com/?t=game+of+thrones&season="+ i +"&apikey=cfbbc0b3");
	// 		SeasonData seasonData = converter.getData(json, SeasonData.class);
	// 		seasons.add(seasonData);
	// 	}

	// 	seasons.forEach(System.out::println);
	}

}
