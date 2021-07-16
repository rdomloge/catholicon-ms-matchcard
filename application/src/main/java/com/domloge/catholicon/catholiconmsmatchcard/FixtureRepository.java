package com.domloge.catholicon.catholiconmsmatchcard;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

import com.domloge.catholiconmsmatchcardlibrary.DivisionReportDataItemProjection;
import com.domloge.catholiconmsmatchcardlibrary.Fixture;
import com.domloge.catholiconmsmatchcardlibrary.FixtureResultProjection;

@RepositoryRestResource(collectionResourceRel = "fixtures", path = "fixtures")
@CrossOrigin("*")
public interface FixtureRepository extends PagingAndSortingRepository<Fixture, Integer> {

	Fixture findByExternalFixtureId(int externalFixtureId);
	
	List<Fixture> findByHomeTeamIdOrAwayTeamIdAndSeason(int homeTeamId, int awayTeamId, int season);
	
	@Query("{ 'matchCard': { $exists : true }, 'matchCard.status': 'UNCONFIRMED', 'season': ?0 }")
	List<Fixture> findUnconfirmedMatchcardsBySeason(int season);
	
	FixtureResultProjection[] findByDivisionId(int divisionId);
	
	FixtureResultProjection findResultByExternalFixtureId(int externalFixtureId);

	@Query("{ 'season': ?0, $or: [{'homeTeamId': ?1}, {'awayTeamId': ?1}] }")
	FixtureResultProjection[] findFixturesForTeam(int season, int teamId);

	@Query("{ 'season': 0, 'matchCard': {$exists: true}, $or: [{'matchDate': { $gte: { $date: ?0 }, $lt: { $date: ?1 } } } ] }")
	// @Query("{ 'season': 0, 'matchDate': { $gte: { $date: ?0 } } }")
	FixtureResultProjection[] findResultsByTimeRange(String isoDateFrom, String isoDateTo);

	Long countBySeason(int season);

	@Query("{ 'divisionId': ?0, 'matchCard': {$exists: true}}")
	List<DivisionReportDataItemProjection> buildDivisionReport(int divisionId);

}
