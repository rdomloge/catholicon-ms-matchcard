package com.domloge.catholicon.catholiconmsmatchcard;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="result", types= {Matchcard.class})
public interface Result {

	String getHomeTeamName();

	int getHomeScore();
	
	int getAwayScore();

	String getAwayTeamName();
	
	String getMatchDate();
}
