package com.domloge.catholiconmsmatchcardlibrary;

public class DivisionReport {
	
	private DivisionReportLine[] lines;
	
	private int seasonStartYear;
	
	
	public DivisionReport(DivisionReportLine[] lines, int seasonStartYear) {
		super();
		this.lines = lines;
		this.seasonStartYear = seasonStartYear;
	}

	public DivisionReportLine[] getLines() {
		return lines;
	}

	public int getSeasonStartYear() {
		return seasonStartYear;
	}
	
}
