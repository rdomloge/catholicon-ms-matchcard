package com.domloge.catholicon.catholiconmsmatchcard;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "matchcards", path = "matchcards")
@CrossOrigin("*")
public interface MatchcardRepository extends PagingAndSortingRepository<Matchcard, Integer> {

	Matchcard findByFixtureId(int fixtureId);

}
