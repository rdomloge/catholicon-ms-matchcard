package com.domloge.catholiconmsmatchcardlibrary;

import com.domloge.DivisionReportMatchcardProjection;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "divisionreportdataitem", types = { Fixture.class })
public interface DivisionReportDataItemProjection {
	
    String getId();
	
	int getDivisionId();
	
	int getExternalFixtureId();
	
	int getAwayTeamId();
	
	int getHomeTeamId();

	DivisionReportMatchcardProjection getMatchCard();

}
