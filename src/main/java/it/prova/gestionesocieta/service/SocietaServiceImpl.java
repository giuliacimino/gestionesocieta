package it.prova.gestionesocieta.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.exception.SocietaConDipendentiException;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.repository.SocietaRepository;

@Service
public class SocietaServiceImpl implements SocietaService{

	@Autowired 
	private SocietaRepository societaRepository;
	
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public void inserisciSocieta(Societa societaInstance) {
		societaRepository.save(societaInstance);
		
	}

	@Override
	public List<Societa> findByExample(Societa example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select s from Societa s where s.id = s.id ");

		if (StringUtils.isNotEmpty(example.getRagioneSociale())) {
			whereClauses.add(" s.ragioneSociale  like :ragioneSociale ");
			paramaterMap.put("ragioneSociale", "%" + example.getRagioneSociale() + "%");
		}
		if (StringUtils.isNotEmpty(example.getIndirizzo())) {
			whereClauses.add(" s.indirizzo like :indirizzo ");
			paramaterMap.put("indirizzo", "%" + example.getIndirizzo() + "%");
		}

		if (example.getDataFondazione() != null) {
			whereClauses.add("s.dataFondazione >= :dataFondazione ");
			paramaterMap.put("dataFondazione", example.getDataFondazione());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Societa> typedQuery = entityManager.createQuery(queryBuilder.toString(), Societa.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	


	@Transactional
	public void rimuovi(Long id) throws Exception {
			Societa societaInstance = societaRepository.findByIdEager(id);
			if (societaInstance.getDipendenti().size() > 0) {
				throw new SocietaConDipendentiException("errore: societa con dipendenti.");
			}
			societaRepository.deleteById(id);
		
	}

	@Transactional
	public Societa caricaSingolaSocietaEager(Long id) {
		return societaRepository.findByIdEager(id);
	}
	@Transactional
	public List<Societa> trovaConDipendetiConRedditoAnnuoMaggioreDi(int redditoAnno) {
		return societaRepository.findAllDistinctByDipendenti_RedditoAnnuoLordoGreaterThan(redditoAnno);
	}

	

}
