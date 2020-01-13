package com.domloge.catholicon.catholiconmsmatchcard;

public class DivisionReportLine {
	
	private String teamName;
	
	private int rubbers_won, rubbers_lost;
	
	private int matches_played, matches_won;
	
	
	public DivisionReportLine(String teamName) {
		super();
		this.teamName = teamName;
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
	
	public void process(DivisionReportDataItem item) {
		boolean homeTeam = item.getHome_team_name().equals(teamName);
		
		if(homeTeam) {
			rubbers_won += item.getHome_score();
			rubbers_lost += item.getAway_score();
			matches_won += (item.getHome_score() > item.getAway_score() ? 1 : 0); 
		}
		else {
			rubbers_won += item.getAway_score();
			rubbers_lost += item.getHome_score();
			matches_won += (item.getAway_score() > item.getHome_score() ? 1 : 0);
		}
		
		matches_played++;
	}
}
