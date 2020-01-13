package com.domloge.catholicon.catholiconmsmatchcard;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DivisionReportController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(DivisionReportController.class);


	@Autowired
	private DivisionReportRepository divisionReportRepository;
	
	@RequestMapping("/divisions/{divisionId}/report")
	public DivisionReport getReportForDivision(@PathVariable int divisionId) {
		
		Map<String, DivisionReportLine> map = new HashMap<>();
		
		List<DivisionReportDataItem> divisionReportDataItems = divisionReportRepository.buildDivisionReport(divisionId);
		
		for (DivisionReportDataItem item : divisionReportDataItems) {
			LOGGER.debug("Found a match result[{}]: {}", item.getClass().getSimpleName(), item);
			if( ! map.containsKey(item.getAway_team_name())) {
				map.put(item.getAway_team_name(), new DivisionReportLine(item.getAway_team_name()));
			}
			
			if( ! map.containsKey(item.getHome_team_name())) {
				map.put(item.getHome_team_name(), new DivisionReportLine(item.getHome_team_name()));
			}
			
			map.get(item.getAway_team_name()).process(item);
			
			map.get(item.getHome_team_name()).process(item);
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
		return new DivisionReport(lines);
	}
	
}
