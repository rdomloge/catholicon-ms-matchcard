package com.domloge.catholiconmsmatchcardlibrary;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "divisionreportdataitem", types = { Fixture.class })
public interface DivisionReportDataItemProjection {
	
    String getId();
	
	int getDivisionId();
	
	int getExternalFixtureId();
	
	int getAwayTeamId();
	
	String getAwayTeamName();
	
	int getHomeTeamId();
	
	String getHomeTeamName();

	DivisionReportMatchcardProjection getMatchCard();

}
