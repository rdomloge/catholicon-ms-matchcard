package com.domloge.catholiconmsmatchcardlibrary;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "divisionreportmatchcard", types = { Fixture.class })
public interface DivisionReportMatchcardProjection {
    
    int getAwayScore();
	
	int getHomeScore();
	
	String getMatchDate();
	
	boolean isTeamSize6();
}
