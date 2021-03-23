package com.domloge.catholiconmsmatchcardlibrary;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "fixture", indexes = {
	@Index(columnList = "externalFixtureId"),
	@Index(columnList = "divisionId"),
	@Index(columnList = "season")
})
public class Fixture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(unique=true)
	private int externalFixtureId;
	
	private int divisionId;
	
	private String matchDate;
	
	private int homeTeamId;
	
	private int awayTeamId;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "matchcard_id", referencedColumnName = "id")
	private Matchcard matchCard;
	
	private int season;
	
	
	public Fixture() {
	}

	public Fixture(int externalFixtureId, String matchDate, int homeTeamId, int awayTeamId, int divisionId, Matchcard card, int season) {
		this.externalFixtureId = externalFixtureId;
		this.divisionId = divisionId;
		this.matchDate = matchDate;
		this.homeTeamId = homeTeamId;
		this.awayTeamId = awayTeamId;
		this.matchCard = card;
		this.season = season;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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

	public String getMatchDate() {
		return matchDate;
	}
	
	public void setMatchDate(String matchDate) {
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
	
	public Matchcard getMatchCard() {
		return matchCard;
	}

	public void setMatchCard(Matchcard matchCard) {
		this.matchCard = matchCard;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
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
