package com.domloge.catholicon.catholiconmsmatchcard;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

@Repository
@Transactional(value = TxType.SUPPORTS)
public class DivisionReportRepository {

	@PersistenceContext
    private EntityManager entityManager;
	
	public List<DivisionReportDataItem> buildDivisionReport(int divisionId) {
		
		Query query = entityManager.createNativeQuery(
				"select f.division_id, f.home_team_id, f.away_team_id, m.* "
				+ "from matchcard m "
				+ "left outer join fixture f on f.fixture_id=m.fixture_id "
				+ "where f.division_id=?", 
				"DivisionReportDataResult");
		
        query.setParameter(1, divisionId);
        
        return (List<DivisionReportDataItem>) query.getResultList();
	}
}
