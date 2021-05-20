package com.domloge.catholicon.catholiconmsmatchcard;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.domloge.catholiconmsmatchcardlibrary.DivisionReport;
import com.domloge.catholiconmsmatchcardlibrary.DivisionReportDataItemProjection;
import com.domloge.catholiconmsmatchcardlibrary.DivisionReportLine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DivisionReportController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(DivisionReportController.class);


	@Autowired
	private FixtureRepository fixtureRepository;
	
	@RequestMapping("/divisions/{divisionId}/report")
	public DivisionReport getReportForDivision(@PathVariable int divisionId, @RequestParam("season") int season) {
		
		Map<String, DivisionReportLine> map = new HashMap<>();
		
		List<DivisionReportDataItemProjection> divisionReportDataItems = fixtureRepository.buildDivisionReport(divisionId);
		LOGGER.info("Repo found {} data items for division {} in season {}", divisionReportDataItems.size(), divisionId, season);
		
		for (DivisionReportDataItemProjection item : divisionReportDataItems) {
			if( ! map.containsKey(item.getMatchCard().getAwayTeamName())) {
				map.put(item.getMatchCard().getAwayTeamName(), new DivisionReportLine(item.getMatchCard().getAwayTeamName(), item.getAwayTeamId()));
			}
			
			if( ! map.containsKey(item.getMatchCard().getHomeTeamName())) {
				map.put(item.getMatchCard().getHomeTeamName(), new DivisionReportLine(item.getMatchCard().getHomeTeamName(), item.getHomeTeamId()));
			}
			
			map.get(item.getMatchCard().getAwayTeamName()).process(item);
			
			map.get(item.getMatchCard().getHomeTeamName()).process(item);
		}
		
		DivisionReportLine[] lines = new DivisionReportLine[map.size()];
		LinkedList<String> keys = new LinkedList<String>(map.keySet());
		Collections.sort(keys, new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return map.get(o2).getRubbers_won() - map.get(o1).getRubbers_won();
			}});
		String[] keyArr = keys.toArray(new String[0]);
		for (int i = 0; i < keyArr.length; i++) {
			lines[i] = map.get(keyArr[i]);
		}
		return new DivisionReport(lines, season);
	}
	
}
