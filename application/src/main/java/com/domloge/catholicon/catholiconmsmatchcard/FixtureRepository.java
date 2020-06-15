package com.domloge.catholicon.catholiconmsmatchcard;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "fixtures", path = "fixtures")
@CrossOrigin("*")
public interface FixtureRepository extends PagingAndSortingRepository<Fixture, Integer> {

	Fixture findByFixtureId(int fixtureId);
	
	List<Fixture> findByHomeTeamIdOrAwayTeamIdAndSeason(int homeTeamId, int awayTeamId, int season);
	
	FixtureResult[] findByDivisionId(int divisionId);

}
