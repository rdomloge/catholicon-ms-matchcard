package com.domloge.catholicon.catholiconmsmatchcard;

import com.domloge.catholiconmsmatchcardlibrary.Game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(exported = false)
@CrossOrigin("*")
public interface GameRepository extends CrudRepository<Game, Integer> {

}