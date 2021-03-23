package com.domloge.catholiconmsmatchcardlibrary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "matchcard", indexes = {
	@Index(columnList = "status")
})
public class Matchcard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	// @Column(unique=true)
	// private int fixtureId;
	// @OneToOne(mappedBy = "matchCard")
	// private Fixture fixture;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ElementCollection(fetch = FetchType.LAZY, targetClass = String.class)
	@OrderColumn(name = "PLAYERORDER")
	private List<String> homePlayers;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ElementCollection(fetch = FetchType.LAZY, targetClass = String.class)
	@OrderColumn(name = "PLAYERORDER")
	private List<String> awayPlayers;
	
	private String homeTeamName;
	
	private int homeScore;
	
	private String awayTeamName;
	
	private int awayScore;

	@Enumerated(EnumType.STRING)
	private MatchcardStatus status;
	
	/**
	 * 
	 * https://stackoverflow.com/questions/23409026/duplicate-entry-by-hibernate-on-join-table-in-springmvc
	 * 
	 * This issue is really just a result of the well documented effect of using a List without an Index column. 
	 * This causes Hibernate to treat the List like a Bag and so deleting the join table before inserting is 
	 * expected and necessary behaviour. Bags and their behaviour are well documented in countless blogs and 
	 * the Hibernate documentation so I won't go into any of that here.
	 * The solution was just to slap a @OrderColumn annotation on each collection. With this index hibernate 
	 * no longer needs to treat the List like a Bag and so has no need to perform the delete before insert. 
	 * So no need to use the tables of outer joins.
	 * 
	 * So then I added an 'order column' with the name 'order' and that broke it due to this being a reserved keyword
	 * 
	 * https://stackoverflow.com/questions/20152311/hibernate-table-not-found-error-on-runtime
	 */
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 @ElementCollection(fetch = FetchType.LAZY, targetClass = Boolean.class)
	@OrderColumn(name = "RUBBER") // this is critical to prevent issues with findById returning a list with > 2k elements
	private List<Boolean> homeTeamWins;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
	@OrderColumn(name = "RUBBER")
	private List<Rubber> rubbers = new LinkedList<Rubber>();
	
	private boolean teamSize6;

	private String matchDate;
	
	
	
	public Matchcard(Map<Integer, Rubber> scoreMap, String[] homePlayers, String[] awayPlayers, String homeTeam,
			String awayTeam, String matchDate, int homeScore, int awayScore, Boolean[] homeTeamWins,
			boolean teamSize6, Fixture fixture, MatchcardStatus status) {
		
		rubbers.addAll(scoreMap.values());
		this.homePlayers = Arrays.asList(homePlayers);
		this.awayPlayers = Arrays.asList(awayPlayers);
		this.homeTeamName = homeTeam;
		this.awayTeamName = awayTeam;
		this.matchDate = convertDayMonthYearToYearMonthDay(matchDate);
		
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		
		this.homeTeamWins = Arrays.asList(homeTeamWins);
		this.teamSize6 = teamSize6;
		this.status = status;
	}
	
	public Matchcard() {
		
	}

	public static String convertDayMonthYearToYearMonthDay(String dayMonthYear) {
		SimpleDateFormat dmyFormat = new SimpleDateFormat("dd MMM yyyy");
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return ymdFormat.format(dmyFormat.parse(dayMonthYear));
		} 
		catch (ParseException e) {
			return "[Cannot parse] "+dayMonthYear;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatchDate() {
		return matchDate;
	}
	
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	
	// @JsonIgnore
	// public Fixture getFixture() {
	// 	return fixture;
	// }

	// public void setFixture(Fixture fixture) {
	// 	this.fixture = fixture;
	// }

	public List<String> getHomePlayers() {
		return homePlayers;
	}

	public void setHomePlayers(List<String> homePlayers) {
		this.homePlayers = homePlayers;
	}

	public List<String> getAwayPlayers() {
		return awayPlayers;
	}

	public void setAwayPlayers(List<String> awayPlayers) {
		this.awayPlayers = awayPlayers;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}

	public List<Boolean> getHomeTeamWins() {
		return homeTeamWins;
	}

	public void setHomeTeamWins(List<Boolean> homeTeamWins) {
		this.homeTeamWins = homeTeamWins;
	}

	public List<Rubber> getRubbers() {
		return rubbers;
	}

	public void setRubbers(List<Rubber> rubbers) {
		this.rubbers = rubbers;
	}

	public boolean isTeamSize6() {
		return teamSize6;
	}

	public void setTeamSize6(boolean teamSize6) {
		this.teamSize6 = teamSize6;
	}

	public MatchcardStatus getStatus() {
		return status;
	}

	public void setStatus(MatchcardStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id");
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "id");
	}
	
}
