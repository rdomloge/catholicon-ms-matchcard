package com.domloge.catholicon.catholiconmsmatchcard;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/*
 division_id |  id  | away_score |  away_team_name   | fixture_id | home_score |  home_team_name   | match_date | team_size6 
-------------+------+------------+-------------------+------------+------------+-------------------+------------+------------
          56 | 1398 |          8 | Waverley Open A   |       2355 |          1 | BH Pegasus Open A | 2019-11-12 | t
          56 | 1437 |          2 | Viking Open A     |       2324 |          7 | Waverley Open A   | 2019-10-16 | t

 */
@Entity
@SqlResultSetMapping(name = "DivisionReportDataResult", classes = {
		@ConstructorResult (targetClass = DivisionReportDataItem.class, columns = {
				@ColumnResult(name = "division_id", type = Integer.class),
				@ColumnResult(name = "away_score", type = Integer.class),
				@ColumnResult(name = "away_team_name", type = String.class),
				@ColumnResult(name = "away_team_id", type = Integer.class),
				@ColumnResult(name = "fixture_id", type = Integer.class),
				@ColumnResult(name = "home_score", type = Integer.class),
				@ColumnResult(name = "home_team_name", type = String.class),
				@ColumnResult(name = "home_team_id", type = Integer.class),
				@ColumnResult(name = "match_date", type = String.class),
				@ColumnResult(name = "team_size6", type = Boolean.class),
		} )
})
public class DivisionReportDataItem {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private int division_id;
	
	private int away_score;
	
	private String away_team_name;
	
	private int away_team_id;
	
	private int fixture_id;
	
	private int home_score;
	
	private String home_team_name;
	
	private int home_team_id;
	
	private String match_date;
	
	private boolean team_size6;
	

	public DivisionReportDataItem(int division_id, int away_score, String away_team_name, int away_team_id, int fixture_id,
			int home_score, String home_team_name, int home_team_id, String match_date, boolean team_size6) {
		super();
		this.division_id = division_id;
		this.away_score = away_score;
		this.away_team_name = away_team_name;
		this.away_team_id = away_team_id;
		this.fixture_id = fixture_id;
		this.home_score = home_score;
		this.home_team_name = home_team_name;
		this.match_date = match_date;
		this.team_size6 = team_size6;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDivision_id() {
		return division_id;
	}

	public void setDivision_id(int division_id) {
		this.division_id = division_id;
	}

	public int getAway_score() {
		return away_score;
	}

	public void setAway_score(int away_score) {
		this.away_score = away_score;
	}

	public String getAway_team_name() {
		return away_team_name;
	}

	public void setAway_team_name(String away_team_name) {
		this.away_team_name = away_team_name;
	}

	public int getFixture_id() {
		return fixture_id;
	}

	public void setFixture_id(int fixture_id) {
		this.fixture_id = fixture_id;
	}

	public int getHome_score() {
		return home_score;
	}

	public void setHome_score(int home_score) {
		this.home_score = home_score;
	}

	public String getHome_team_name() {
		return home_team_name;
	}

	public void setHome_team_name(String home_team_name) {
		this.home_team_name = home_team_name;
	}

	public String getMatch_date() {
		return match_date;
	}

	public void setMatch_date(String match_date) {
		this.match_date = match_date;
	}

	public boolean isTeam_size6() {
		return team_size6;
	}

	public void setTeam_size6(boolean team_size6) {
		this.team_size6 = team_size6;
	}

	public int getAway_team_id() {
		return away_team_id;
	}

	public void setAway_team_id(int away_team_id) {
		this.away_team_id = away_team_id;
	}

	public int getHome_team_id() {
		return home_team_id;
	}

	public void setHome_team_id(int home_team_id) {
		this.home_team_id = home_team_id;
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
