package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.model.SerieData;
import br.com.alura.screenmatch.service.ApiConsume;
import br.com.alura.screenmatch.service.DataConverter;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ApiConsume apiConsume = new ApiConsume();
		String json = apiConsume.getData("https://www.omdbapi.com/?t=game+of+thrones&apikey=cfbbc0b3");

		DataConverter converter = new DataConverter();
		SerieData data = converter.getData(json, SerieData.class);

		System.out.println(data);
	}

}
