package com.marvel.pwc.repositories;

import com.marvel.pwc.models.MarvelCharacter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MarvelCharacterRepository extends MongoRepository<MarvelCharacter, String> {

    @Query("{ $or: [ { name: ?0 }, { id: ?0 } ] }")
    MarvelCharacter findByNameOrId(String query);
}
