package com.domloge.catholicon.catholiconmsmatchcard;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.domloge.catholicon.ms.common.Diff;
import com.domloge.catholicon.ms.common.ScraperException;
import com.domloge.catholicon.ms.common.Sync;

@Component
public class SyncSchedulingAndPersistence {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SyncSchedulingAndPersistence.class);

	@Autowired
	private ResultScraper scraper;
	
	@Autowired
	private MatchcardRepository repository;
	
	@Autowired
	private Sync<Matchcard> sync;
	
	
	public void sync(int fixtureId) throws ScraperException {
		LOGGER.info("Synching");
		
		Matchcard masterMatchcard = scraper.loadMatchcard(fixtureId);
		LOGGER.debug("Scrape complete");
		
		Matchcard dbMatchCard = repository.findByFixtureId(fixtureId);
		
		Diff<Matchcard> compare = sync.compare(map(masterMatchcard), map(dbMatchCard));
		LOGGER.debug("Compare complete");
		
		for (Matchcard card : compare.getDelete()) {
			repository.delete(card);
			LOGGER.debug("Delete {}", card);
		}
		
		for (Matchcard card : compare.getNewValues()) {
			repository.save(card);
			LOGGER.info("Persisted {}", card);
		}
		
		for (Matchcard card : compare.getUpdate()) {
			repository.save(card);
			LOGGER.debug("Update {}", card);
		}
		
		LOGGER.info("Synch complete");
	}
	
	private Map<Integer, Matchcard> map(Matchcard mc) {
		Map<Integer, Matchcard> map = new HashMap<>();
		if(null != mc) {
			map.put(mc.getFixtureId(), mc);
		}
		return map;
	}
}
