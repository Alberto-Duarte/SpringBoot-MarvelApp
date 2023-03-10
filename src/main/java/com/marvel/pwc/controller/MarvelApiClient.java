package com.marvel.pwc.controller;

import com.marvel.pwc.models.MarvelCharacter;
import com.marvel.pwc.repositories.MarvelCharacterRepository;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;




@Service
public class MarvelApiClient {
        private final String BASE_URL = "https://gateway.marvel.com/v1/public/";
        private final String API_KEY = "37d075af85235a76c92b60dcc53bdf38";
        private final String PRIVATE_KEY = "ad760beea0edbe11702b1928b6974d13bcdc6841";

        @GetMapping("/characters")
    public void getCharacters() throws IOException, InterruptedException, NoSuchAlgorithmException {
        String url = getCharactersUrl();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        MarvelApiResponse marvelApiResponse = objectMapper.readValue(responseBody, MarvelApiResponse.class);

        List<MarvelCharacter> characters = marvelApiResponse.getData().getResults();

        marvelCharacterRepository.saveAll(characters);
    }



    private String generateHash() throws NoSuchAlgorithmException {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String hash = timestamp + PRIVATE_KEY + API_KEY;

            return MyHashUtils.md5(hash);
        }
    private String getCharactersUrl() throws NoSuchAlgorithmException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String hash = generateHash();

        return String.format("%s%s?ts=%s&apikey=%s&hash=%s",
                BASE_URL, "characters", timestamp, API_KEY, hash);
    }
    public class MyHashUtils {

        public static String md5(String text) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }
    }
    @Autowired
    private MarvelCharacterRepository marvelCharacterRepository;


    public MarvelApiClient(MarvelCharacterRepository marvelCharacterRepository) {
        this.marvelCharacterRepository = marvelCharacterRepository;
    }

    public List<MarvelCharacter> getAllCharacters() {
        return marvelCharacterRepository.findAll();
    }


}
