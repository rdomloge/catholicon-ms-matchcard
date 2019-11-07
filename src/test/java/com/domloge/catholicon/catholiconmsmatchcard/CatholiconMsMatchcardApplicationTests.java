package com.domloge.catholicon.catholiconmsmatchcard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.domloge.catholicon.ms.common.ScraperException;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CatholiconMsMatchcardApplicationTests {

	@Autowired
	private SyncSchedulingAndPersistence ssap;
	
	@Autowired
	private MatchcardRepository repo;
	

	@Test
	public void contextLoads() {
	}
	
	@Test
	@DirtiesContext
	public void testPlayerNameOrdering() throws ScraperException {
		ssap.sync(2357);
		Matchcard matchcard = repo.findByFixtureId(2357);
		
		String[] homePlayers = matchcard.getHomePlayers().stream().toArray(String[] :: new);
		assertEquals("A. Turner", homePlayers[0]);
		assertEquals("N. Reid", homePlayers[1]);
		
		assertEquals("R. Clayburn", homePlayers[2]);
		assertEquals("M. Challa", homePlayers[3]);
		
		assertEquals("W. Johnson", homePlayers[4]);
		assertEquals("R. Domloge", homePlayers[5]);
		
		String[] awayPlayers = matchcard.getAwayPlayers().stream().toArray(String[] :: new);
		assertEquals("J. Li", awayPlayers[0]);
		assertEquals("T. Hunt", awayPlayers[1]);
		
		assertEquals("J. Purser", awayPlayers[2]);
		assertEquals("K. Miles", awayPlayers[3]);
		
		assertEquals("T. Hicks", awayPlayers[4]);
		assertEquals("I. Small", awayPlayers[5]);
	}
	
	@Test
	@DirtiesContext
	public void knownIssueWithDuplicates() throws ScraperException {
		ssap.sync(2211);
		repo.findByFixtureId(2211);
		ssap.sync(2211);
		repo.findByFixtureId(2211);
	}
	
	@Test
	@DirtiesContext
	public void testRubberOrder() throws ScraperException {
		ssap.sync(2298);
		Matchcard card = repo.findByFixtureId(2298);
		List<Rubber> rubbers = card.getRubbers();
		Iterator<Rubber> itr = rubbers.iterator();
		assertTrue(itr.hasNext());
		Rubber rubber = itr.next();
		assertEquals(20, rubber.getFirstGame().getHomeScore());
		assertEquals(22, rubber.getFirstGame().getAwayScore());
	}
	
	@Test
	@DirtiesContext
	public void repoReturn() throws ScraperException, InterruptedException {
		
		Matchcard matchCard1 = repo.findByFixtureId(2168);
		assertNull(matchCard1);
		
		ssap.sync(2168);
		
		Matchcard matchCard2 = repo.findByFixtureId(2168);
		assertNotNull(matchCard2);
	}
	
	@Test
	@DirtiesContext
	public void syncByFixtureIdThenLoadByDbId() throws ScraperException {
		ssap.sync(2168);
		
		Optional<Matchcard> optional = repo.findById(1);
		Matchcard matchcard = optional.get();
		assertNotNull(matchcard);
		assertEquals(9, matchcard.getHomeTeamWins().size());
	}
	
	@Test 
	@DirtiesContext
	public void basicPersistenceTest() {
		Matchcard m = new Matchcard();
		m.setAwayTeamName("hometeam");
		m.setFixtureId(1);
		repo.save(m);
		Matchcard matchcard = repo.findByFixtureId(1);
		assertEquals("hometeam", matchcard.getAwayTeamName());
	}
	
	@Test
	@DirtiesContext
	public void syncTwice() throws ScraperException {
		
		ssap.sync(2168);
		ssap.sync(2168);
		assertEquals(1, repo.count());
	}
}

