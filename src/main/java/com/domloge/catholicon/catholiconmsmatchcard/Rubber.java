package com.domloge.catholicon.catholiconmsmatchcard;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class Rubber {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn
	private Game firstEnd;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn
	private Game secondEnd;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn
	private Game finalEnd;
	
	
	
	public void setGameProgrammatically(int gameNum, boolean homeScore, String score) {
		Game g = null;
		switch(gameNum) {
			case 1:
				if(null == firstEnd) firstEnd = new Game();
				g = firstEnd;
				break;
			case 2:
				if(null == secondEnd) secondEnd = new Game();
				g = secondEnd;
				break;
			case 3:
				if(null == finalEnd) finalEnd = new Game();
				g = finalEnd;
				break;
			default:
				throw new IllegalArgumentException("Invalid gameNum param: "+gameNum);
		}
		
		g.setGameNum(gameNum);
		
		if(homeScore) g.setHomeScore(Integer.parseInt(score));
		else g.setAwayScore(Integer.parseInt(score));
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Game getFirstEnd() {
		return firstEnd;
	}

	public void setFirstEnd(Game firstEnd) {
		this.firstEnd = firstEnd;
	}

	public Game getSecondEnd() {
		return secondEnd;
	}

	public void setSecondEnd(Game secondEnd) {
		this.secondEnd = secondEnd;
	}

	public Game getFinalEnd() {
		return finalEnd;
	}

	public void setFinalEnd(Game finalEnd) {
		this.finalEnd = finalEnd;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "id");
	}
}
