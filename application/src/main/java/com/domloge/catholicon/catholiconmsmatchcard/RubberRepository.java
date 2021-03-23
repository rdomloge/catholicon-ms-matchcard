package com.domloge.catholicon.catholiconmsmatchcard;

import com.domloge.catholiconmsmatchcardlibrary.Rubber;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(exported = false)
@CrossOrigin("*")
public interface RubberRepository extends CrudRepository<Rubber, Integer> {

}
