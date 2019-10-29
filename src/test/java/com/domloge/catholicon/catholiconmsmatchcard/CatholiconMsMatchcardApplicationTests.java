package com.domloge.catholicon.catholiconmsmatchcard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.domloge.catholicon.ms.common.ScraperException;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CatholiconMsMatchcardApplicationTests {

	@Autowired
	private SyncSchedulingAndPersistence ssap;
	
	@Autowired
	private MatchcardRepository repo;
	

	@Test
	@DirtiesContext
	public void knownIssueWithDuplicates() throws ScraperException {
		ssap.sync(2211);
		repo.findByFixtureId(2211);
		ssap.sync(2211);
		repo.findByFixtureId(2211);
	}
	
	
//	@Test
//	@DirtiesContext
//	public void repoReturn() throws ScraperException, InterruptedException {
//		
//		Matchcard matchCard1 = repo.findByFixtureId(2168);
//		assertNull(matchCard1);
//		
//		ssap.sync(2168);
//		
//		Matchcard matchCard2 = repo.findByFixtureId(2168);
//		assertNotNull(matchCard2);
//	}
//	
//	@Test
//	@DirtiesContext
//	public void syncByFixtureIdThenLoadByDbId() throws ScraperException {
//		ssap.sync(2168);
//		
//		Optional<Matchcard> optional = repo.findById(1);
//		Matchcard matchcard = optional.get();
//		assertNotNull(matchcard);
//		assertEquals(9, matchcard.getHomeTeamWins().size());
//	}
//	
//	@Test 
//	@DirtiesContext
//	public void basicPersistenceTest() {
//		Matchcard m = new Matchcard();
//		m.setAwayTeamName("hometeam");
//		m.setFixtureId(1);
//		repo.save(m);
//		Matchcard matchcard = repo.findByFixtureId(1);
//		assertEquals("hometeam", matchcard.getAwayTeamName());
//	}
//	
//	@Test
//	@DirtiesContext
//	public void syncTwice() throws ScraperException {
//		
//		ssap.sync(2168);
//		ssap.sync(2168);
//		assertEquals(1, repo.count());
//	}
}

