package com.domloge.catholicon.catholiconmsmatchcard;

import java.util.List;

import com.domloge.catholiconmsmatchcardlibrary.DivisionReportDataItemProjection;
import com.domloge.catholiconmsmatchcardlibrary.Fixture;
import com.domloge.catholiconmsmatchcardlibrary.FixtureResultProjection;
import com.domloge.catholiconmsmatchcardlibrary.Matchcard;
import com.domloge.catholiconmsmatchcardlibrary.MatchcardStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
// @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CatholiconMsMatchcardApplicationTests {

	@Autowired
	private FixtureRepository fixtureRepository;

	@Autowired
	private ObjectMapper mapper;

	// @Autowired
	// private ApplicationContext ctx;

	@Test
	public void contextLoads() {
		// String[] names = ctx.getBeanDefinitionNames();
		// for (String name : names) {
		// 	System.out.println("> "+name);
		// }
	}

	@Test
	public void nullDatePatternValidation() {
		Fixture f = new Fixture();
		fixtureRepository.save(f);

		// f.setMatchDate("not valid");
		// fixtureRepository.save(f);
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

	@Test
	public void loadDivisionReport() {
		Fixture f = new Fixture();
		f.setExternalFixtureId(1234);
		f.setDivisionId(1);
		f.setHomeTeamName("Division report home team");
		f.setAwayTeamName("Division report away team");
		Matchcard m = new Matchcard();
		m.setAwayScore(2);
		m.setHomeScore(7);
		f.setMatchCard(m);
		fixtureRepository.save(f);

		Fixture wrongF = new Fixture();
		wrongF.setExternalFixtureId(4321);
		wrongF.setDivisionId(17);
		Matchcard wrongM = new Matchcard();
		wrongM.setAwayScore(9);
		wrongM.setHomeScore(0);
		wrongF.setMatchCard(wrongM);
		fixtureRepository.save(wrongF);

		FixtureResultProjection[] loadedFix = fixtureRepository.findByDivisionId(1);
		Assert.assertEquals("Fixture did not save!", 1, loadedFix.length);
		System.out.println("Saved (& retrieved) fixture: "+loadedFix);
		
		List<DivisionReportDataItemProjection> items = fixtureRepository.buildDivisionReport(1);
		Assert.assertEquals("Wrong number of data items", 1, items.size());
	}

	@Test
	public void findUnconfirmedMatchcardsBySeason() {
		Fixture fUnplayed = new Fixture();
		fUnplayed.setSeason(0);
		fUnplayed.setExternalFixtureId(1234);
		fixtureRepository.save(fUnplayed);

		Fixture fUnconfirmed = new Fixture();
		fUnconfirmed.setSeason(0);
		fUnconfirmed.setExternalFixtureId(4321);
		Matchcard m = new Matchcard();
		m.setAwayScore(3);
		m.setHomeScore(6);
		m.setStatus(MatchcardStatus.UNCONFIRMED);
		fUnconfirmed.setMatchCard(m);
		fixtureRepository.save(fUnconfirmed);

		Fixture fUnconfirmedWrongSeason = new Fixture();
		fUnconfirmedWrongSeason.setSeason(1);
		fUnconfirmedWrongSeason.setExternalFixtureId(7);
		Matchcard mWrongSeason = new Matchcard();
		mWrongSeason.setAwayScore(3);
		mWrongSeason.setHomeScore(6);
		mWrongSeason.setStatus(MatchcardStatus.UNCONFIRMED);
		fUnconfirmedWrongSeason.setMatchCard(mWrongSeason);
		fixtureRepository.save(fUnconfirmedWrongSeason);

		Fixture fConfirmed = new Fixture();
		fConfirmed.setSeason(0);
		fConfirmed.setExternalFixtureId(6789);
		Matchcard mConfirmed = new Matchcard();
		mConfirmed.setAwayScore(1);
		mConfirmed.setHomeScore(8);
		mConfirmed.setStatus(MatchcardStatus.CONFIRMED);
		fConfirmed.setMatchCard(mConfirmed);
		fixtureRepository.save(fConfirmed);

		System.out.println("Should get "+fixtureRepository.findByExternalFixtureId(4321));
		Assert.assertEquals(1, fixtureRepository.findUnconfirmedMatchcardsBySeason(0).size());
	}
	
	@Test
	public void contextLoadsTwice() {
	}
	
}

