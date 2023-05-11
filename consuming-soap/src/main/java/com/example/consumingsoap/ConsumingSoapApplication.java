package com.example.consumingsoap;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.consumingsoap.wsdl.GetCityResponse;

@SpringBootApplication
public class ConsumingSoapApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumingSoapApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(CityClient quoteClient) {
		return args -> {
			String city = "Almaty";

			if (args.length > 0) {
				city = args[0];
			}
			GetCityResponse response = quoteClient.getCity(city);
			System.err.println(response.getCity().getTemperature());
		};
	}

}