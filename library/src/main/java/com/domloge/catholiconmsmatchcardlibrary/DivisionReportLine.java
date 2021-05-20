package com.domloge.catholiconmsmatchcardlibrary;

public class DivisionReportLine {
	
	private String teamName;
	
	private int teamId;
	
	private int rubbers_won, rubbers_lost;
	
	private int matches_played, matches_won;
	
	
	public DivisionReportLine(String teamName, int teamId) {
		super();
		this.teamName = teamName;
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public int getRubbers_won() {
		return rubbers_won;
	}

	public int getRubbers_lost() {
		return rubbers_lost;
	}

	public int getMatches_played() {
		return matches_played;
	}

	public int getMatches_won() {
		return matches_won;
	}
	
	public int getTeamId() {
		return teamId;
	}

	public void process(DivisionReportDataItemProjection item) {
		boolean homeTeam = item.getMatchCard().getHomeTeamName().equals(teamName);
		
		if(homeTeam) {
			rubbers_won += item.getMatchCard().getHomeScore();
			rubbers_lost += item.getMatchCard().getAwayScore();
			matches_won += (item.getMatchCard().getHomeScore() > item.getMatchCard().getAwayScore() ? 1 : 0);
		}
		else {
			rubbers_won += item.getMatchCard().getAwayScore();
			rubbers_lost += item.getMatchCard().getHomeScore();
			matches_won += (item.getMatchCard().getAwayScore() > item.getMatchCard().getHomeScore() ? 1 : 0);
		}
		
		matches_played++;
	}
}
