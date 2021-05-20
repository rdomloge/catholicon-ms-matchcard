package com.domloge;

import com.domloge.catholiconmsmatchcardlibrary.Fixture;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "divisionreportmatchcard", types = { Fixture.class })
public interface DivisionReportMatchcardProjection {
    
    int getAwayScore();
	
	String getAwayTeamName();
	
	int getHomeScore();
	
	String getHomeTeamName();
	
	String getMatchDate();
	
	boolean isTeamSize6();
}
