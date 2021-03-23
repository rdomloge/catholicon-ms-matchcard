package com.domloge.catholicon.catholiconmsmatchcard;

import com.domloge.catholiconmsmatchcardlibrary.Fixture;
import com.domloge.catholiconmsmatchcardlibrary.Matchcard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
// @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CatholiconMsMatchcardApplicationTests {

	@Autowired
	private FixtureRepository fixtureRepository;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void saveFixtureWithMatchcard() throws JsonProcessingException {
		Fixture f = new Fixture();
		f.setExternalFixtureId(1234);
		Matchcard m = new Matchcard();
		m.setAwayScore(3);
		m.setHomeScore(6);
		f.setMatchCard(m);
		byte[] bytes = mapper.writeValueAsBytes(f);
		System.out.println("Serialised to "+new String(bytes));

		Fixture saved = fixtureRepository.save(f);
		System.out.println("This is the saved entity: "+saved);
	}
	
	// @Test
	// public void contextLoadsTwice() {
	// }
	
//	@Test
//	@DirtiesContext
//	public void loadFixtureWithMatchCardThenDeleteMatchCard() throws ScraperException {
//		ssap.syncMatchcard(2361);
//		Matchcard matchcard = matchcardRepo.findByFixtureId(2361);
//		assertNotNull(matchcard);
//		ssap.syncDivision(355, 0);
//		matchcard = matchcardRepo.findByFixtureId(2361);
//		assertNotNull(matchcard);
//		Fixture fixture = fixtureRepo.findByFixtureId(matchcard.getFixtureId());
//		assertEquals(fixture.getMatchCard(), matchcard);
//	}
	
//	@Test
//	@DirtiesContext
//	public void loadFixtures() throws ScraperException {
//		scraper.load(355, 0);
//	}
//	
//	@Test
//	@DirtiesContext
//	public void repoFixtureReturn() throws ScraperException, InterruptedException {
//		
//		Fixture fixture1 = fixtureRepo.findById(2352).orElseGet(new Supplier<Fixture>() {
//			@Override
//			public Fixture get() {
//				return null;
//			}});
//		assertNull(fixture1);
//		
//		ssap.syncDivision(355, 0);
//		
//		Fixture fixture2 = fixtureRepo.findByFixtureId(2341);
//		assertNotNull(fixture2);
//	}
//	
//	@Test
//	@DirtiesContext
//	public void testSynchingDivision() throws ScraperException {
//		
//		ssap.syncDivision(355, 0);
//		Iterable<Fixture> all = fixtureRepo.findAll();
//		List<Fixture> fixtures = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
//		assertEquals(12, fixtures.size());
//	}
	
//	@Test
//	@DirtiesContext
//	public void testPlayerNameOrdering() throws ScraperException {
//		ssap.syncMatchcard(2357);
//		Matchcard matchcard = matchcardRepo.findByFixtureId(2357);
//		
//		String[] homePlayers = matchcard.getHomePlayers().stream().toArray(String[] :: new);
//		assertEquals("A. Turner", homePlayers[0]);
//		assertEquals("N. Reid", homePlayers[1]);
//		
//		assertEquals("R. Clayburn", homePlayers[2]);
//		assertEquals("M. Challa", homePlayers[3]);
//		
//		assertEquals("W. Johnson", homePlayers[4]);
//		assertEquals("R. Domloge", homePlayers[5]);
//		
//		String[] awayPlayers = matchcard.getAwayPlayers().stream().toArray(String[] :: new);
//		assertEquals("J. Li", awayPlayers[0]);
//		assertEquals("T. Hunt", awayPlayers[1]);
//		
//		assertEquals("J. Purser", awayPlayers[2]);
//		assertEquals("K. Miles", awayPlayers[3]);
//		
//		assertEquals("T. Hicks", awayPlayers[4]);
//		assertEquals("I. Small", awayPlayers[5]);
//	}
//	
//	@Test
//	@DirtiesContext
//	public void knownIssueWithDuplicates() throws ScraperException {
//		ssap.syncMatchcard(2211);
//		matchcardRepo.findByFixtureId(2211);
//		ssap.syncMatchcard(2211);
//		matchcardRepo.findByFixtureId(2211);
//	}
//	
//	@Test
//	@DirtiesContext
//	public void testRubberOrder() throws ScraperException {
//		ssap.syncMatchcard(2298);
//		Matchcard card = matchcardRepo.findByFixtureId(2298);
//		List<Rubber> rubbers = card.getRubbers();
//		Iterator<Rubber> itr = rubbers.iterator();
//		assertTrue(itr.hasNext());
//		Rubber rubber = itr.next();
//		assertEquals(20, rubber.getFirstGame().getHomeScore());
//		assertEquals(22, rubber.getFirstGame().getAwayScore());
//	}
//	
//	@Test
//	@DirtiesContext
//	public void repoReturn() throws ScraperException, InterruptedException {
//		
//		Matchcard matchCard1 = matchcardRepo.findByFixtureId(2168);
//		assertNull(matchCard1);
//		
//		ssap.syncMatchcard(2168);
//		
//		Matchcard matchCard2 = matchcardRepo.findByFixtureId(2168);
//		assertNotNull(matchCard2);
//	}
//	
//	@Test
//	@DirtiesContext
//	public void syncByFixtureIdThenLoadByDbId() throws ScraperException {
//		ssap.syncMatchcard(2168);
//		
//		Optional<Matchcard> optional = matchcardRepo.findById(1);
//		Matchcard matchcard = optional.get();
//		assertNotNull(matchcard);
//		assertEquals(9, matchcard.getHomeTeamWins().size());
//	}
	
	// @Test 
	// @DirtiesContext
	// public void basicPersistenceTest() {
	// 	Matchcard m = new Matchcard();
	// 	m.setAwayTeamName("hometeam");
	// 	m.setFixtureId(1);
	// 	matchcardRepo.save(m);
	// 	Matchcard matchcard = matchcardRepo.findByFixtureId(1);
	// 	assertEquals("hometeam", matchcard.getAwayTeamName());
	// }
	
//	@Test
//	@DirtiesContext
//	public void syncTwice() throws ScraperException {
//		
//		ssap.syncMatchcard(2168);
//		ssap.syncMatchcard(2168);
//		assertEquals(1, matchcardRepo.count());
//	}
}

