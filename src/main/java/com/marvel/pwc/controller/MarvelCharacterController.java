package com.marvel.pwc.controller;

import com.marvel.pwc.models.MarvelCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.marvel.pwc.repositories.MarvelCharacterRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;


@Controller
@RequestMapping("/characters")
public class MarvelCharacterController {

    @Autowired
    private MarvelCharacterRepository characterRepository;

    @GetMapping("/search")
    public String showSearchPage(Model model) {
        model.addAttribute("character", new MarvelCharacter());
        return "search";
    }

    @GetMapping("/{query}")
    public String getCharacter(@RequestParam String query, Model model) {
        MarvelCharacter character = characterRepository.findByNameOrId(query);
        if (character == null) {
            return "notfound";
        } else {
            model.addAttribute("character", character);
            return "character";
        }
    }
}



