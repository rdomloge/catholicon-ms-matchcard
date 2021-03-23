package com.domloge.catholiconmsmatchcardlibrary;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fixtureresult", types = { Fixture.class })
public interface FixtureResultProjection {

    
	int getExternalFixtureId();
	
	int getDivisionId();
	
	int getHomeTeamId();
	
	int getAwayTeamId();
	
	int getSeason();

    MatchResultProjection getMatchCard();
}
