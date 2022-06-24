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
		boolean homeTeam = item.getHomeTeamName().equals(teamName);
		
		if(homeTeam) {
			rubbers_won += item.getMatchcard().getHomeScore();
			rubbers_lost += item.getMatchcard().getAwayScore();
			matches_won += (item.getMatchcard().getHomeScore() > item.getMatchcard().getAwayScore() ? 1 : 0);
		}
		else {
			rubbers_won += item.getMatchcard().getAwayScore();
			rubbers_lost += item.getMatchcard().getHomeScore();
			matches_won += (item.getMatchcard().getAwayScore() > item.getMatchcard().getHomeScore() ? 1 : 0);
		}
		
		matches_played++;
	}
}
