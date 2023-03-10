package com.marvel.pwc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelCharacter {
        public MarvelCharacter() {
        }

        public String getInfo() {
                return id + ", " + name + ", " + description;
        }

        public int getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public String getDescription() {
                return description;
        }

        @JsonProperty("id")
        private int id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("description")
        private String description;
        private String thumbnail;
        private List<String> comics;
        // getters and setters


}
