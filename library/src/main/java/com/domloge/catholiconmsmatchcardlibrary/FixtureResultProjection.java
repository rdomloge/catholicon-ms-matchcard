package com.domloge.catholiconmsmatchcardlibrary;

import java.time.ZonedDateTime;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fixtureresult", types = { Fixture.class })
public interface FixtureResultProjection {

    
	int getExternalFixtureId();
	
	int getDivisionId();
	
	int getHomeTeamId();
	
	String getHomeTeamName();
	
	int getAwayTeamId();

	String getAwayTeamName();
	
	int getSeason();

	ZonedDateTime getMatchDate();

    MatchResultProjection getMatchcard();
}
