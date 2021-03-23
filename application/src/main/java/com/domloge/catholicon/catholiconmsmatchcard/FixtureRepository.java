package com.domloge.catholicon.catholiconmsmatchcard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

import com.domloge.catholiconmsmatchcardlibrary.Fixture;
import com.domloge.catholiconmsmatchcardlibrary.FixtureResultProjection;

@RepositoryRestResource(collectionResourceRel = "fixtures", path = "fixtures")
@CrossOrigin("*")
public interface FixtureRepository extends PagingAndSortingRepository<Fixture, Integer> {

	Fixture findById(int fixtureId);

	Fixture findByExternalFixtureId(int externalFixtureId);
	
	List<Fixture> findByHomeTeamIdOrAwayTeamIdAndSeason(int homeTeamId, int awayTeamId, int season);

	@Query("select f from Fixture f inner join f.matchCard m "+
		" where m.status='UNCONFIRMED' and f.season=:season")
	List<Fixture> findUnconfirmedMatchcardsBySeason(@Param("season") int season);
	
	FixtureResultProjection[] findByDivisionId(int divisionId);

	long countBySeason(int season);

}
