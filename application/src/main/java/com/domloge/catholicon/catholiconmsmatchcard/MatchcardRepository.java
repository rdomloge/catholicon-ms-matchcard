package com.domloge.catholicon.catholiconmsmatchcard;

import com.domloge.catholiconmsmatchcardlibrary.Matchcard;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "matchcards", path = "matchcards", exported = false)
@CrossOrigin("*")
public interface MatchcardRepository extends PagingAndSortingRepository<Matchcard, Integer> {

}
