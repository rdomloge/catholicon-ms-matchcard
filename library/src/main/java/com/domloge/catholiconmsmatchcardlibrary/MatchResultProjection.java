package com.domloge.catholiconmsmatchcardlibrary;

public interface MatchResultProjection {
    
    int getHomeScore();

    int getAwayScore();

    String getHomeTeamName();

	String getAwayTeamName();

    String getMatchDate();
}
