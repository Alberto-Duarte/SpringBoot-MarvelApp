package com.marvel.pwc;

import com.marvel.pwc.controller.MarvelApiClient;
import com.marvel.pwc.models.MarvelCharacter;
import com.marvel.pwc.repositories.MarvelCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@Controller
public class hello {

    @Autowired
    private MarvelCharacterRepository marvelCharacterRepository;
    @Autowired
    private MarvelApiClient marvelApiClient;

        @GetMapping("/load")
        public String getCharacters(Model model) throws IOException, NoSuchAlgorithmException, InterruptedException {
            marvelApiClient.getCharacters();
            return "<h1>la base de datos ha sido cargada</h1>";

        }


}
