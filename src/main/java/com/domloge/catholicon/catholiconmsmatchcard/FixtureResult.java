package com.domloge.catholicon.catholiconmsmatchcard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fixtureresult", types = { Fixture.class })
public interface FixtureResult {

	@Value("#{target.getMatchcard().getHomeTeamName()}")
	String getHomeTeamName();

	@Value("#{target.getMatchcard().getAwayTeamName()}")
	String getAwayTeamName();

	@Value("#{target.getMatchcard().getHomeScore()}")
	Integer getHomeScore();

	@Value("#{target.getMatchcard().getAwayScore()}")
	Integer getAwayScore();

	Integer getHomeTeamId();

	Integer getAwayTeamId();
	
	SuppressedMatchcardProjection getMatchCard();
}
