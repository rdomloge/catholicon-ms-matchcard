package com.domloge.catholiconmsmatchcardlibrary;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//@JsonIgnoreProperties("id")
@Document
public class Fixture {

	@Id
	@Indexed
	private int externalFixtureId;
	
	@Indexed
	private int divisionId;
	
	@Indexed
	private ZonedDateTime matchDate;
	
	@Indexed
	private int homeTeamId;
	
	@Indexed
	private int awayTeamId;

	private String homeTeamName;
	
	private String awayTeamName;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "proxy"})
	private Matchcard matchcard;
	
	@Indexed
	private int season;

	private List<Change> changes = new LinkedList<>();
	
	
	public Fixture() {
	}

	public Fixture(int externalFixtureId, int divisionId, ZonedDateTime matchDate, int homeTeamId, int awayTeamId,
			String homeTeamName, String awayTeamName, Matchcard matchcard, int season) {
		this.externalFixtureId = externalFixtureId;
		this.divisionId = divisionId;
		this.matchDate = matchDate;
		this.homeTeamId = homeTeamId;
		this.awayTeamId = awayTeamId;
		this.homeTeamName = homeTeamName;
		this.awayTeamName = awayTeamName;
		this.matchcard = matchcard;
		this.season = season;
	}

	public int getExternalFixtureId() {
		return externalFixtureId;
	}

	public void setExternalFixtureId(int newInt) {
		this.externalFixtureId = newInt;
	}
	
	public int getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}

	public ZonedDateTime getMatchDate() {
		return matchDate;
	}
	
	public void setMatchDate(ZonedDateTime matchDate) {
		this.matchDate = matchDate;
	}
	
	public int getHomeTeamId() {
		return homeTeamId;
	}
	
	public void setHomeTeamId(int homeTeamId) {
		this.homeTeamId = homeTeamId;
	}
	
	public int getAwayTeamId() {
		return awayTeamId;
	}
	
	public void setAwayTeamId(int awayTeamId) {
		this.awayTeamId = awayTeamId;
	}
	
	public Matchcard getMatchcard() {
		return matchcard;
	}

	public void setMatchcard(Matchcard matchcard) {
		this.matchcard = matchcard;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public List<Change> getChanges() {
		return changes;
	}

	public void addChange(Change change) {
		this.changes.add(change);
	}

	public void setChanges(List<Change> changes) {
		this.changes = changes;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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
