package com.domloge.catholiconmsmatchcardlibrary;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Rubber {
	
	@Id
	private String id;
	
	private Game firstGame;
	
	private Game secondGame;
	
	private Game finalGame;
	
	
	
	public void setGameProgrammatically(int gameNum, boolean homeScore, String score) {
		Game g = null;
		switch(gameNum) {
			case 1:
				if(null == firstGame) firstGame = new Game();
				g = firstGame;
				break;
			case 2:
				if(null == secondGame) secondGame = new Game();
				g = secondGame;
				break;
			case 3:
				if(null == finalGame) finalGame = new Game();
				g = finalGame;
				break;
			default:
				throw new IllegalArgumentException("Invalid gameNum param: "+gameNum);
		}
		
		g.setGameNum(gameNum);
		
		if(homeScore) g.setHomeScore(Integer.parseInt(score));
		else g.setAwayScore(Integer.parseInt(score));
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Game getFirstGame() {
		return firstGame;
	}

	public void setFirstGame(Game firstGame) {
		this.firstGame = firstGame;
	}

	public Game getSecondGame() {
		return secondGame;
	}

	public void setSecondGame(Game secondGame) {
		this.secondGame = secondGame;
	}

	public Game getFinalGame() {
		return finalGame;
	}

	public void setFinalGame(Game finalGame) {
		this.finalGame = finalGame;
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
