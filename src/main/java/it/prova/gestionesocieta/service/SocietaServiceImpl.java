package it.prova.gestionesocieta.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		String query = "select s from Societa s where s.id = s.id ";

		if (StringUtils.isNotEmpty(example.getRagioneSociale()))
			query += " and s.ragioneSociale like '%" + example.getRagioneSociale() + "%' ";
		if (StringUtils.isNotEmpty(example.getIndirizzo()))
			query += " and s.indirizzo like '%" + example.getIndirizzo() + "%' ";
		if (example.getDataFondazione() != null)
			query += " and a.eta = " + example.getDataFondazione();
		
		return entityManager.createQuery(query, Societa.class).getResultList();
	}

	@Transactional
	public void rimuovi(Societa societa) throws Exception {
		if (societa.getDipendenti().size() > 0) {
			throw new SocietaConDipendentiException("sono presenti dipendenti collegati a questa societa");
		}
		societaRepository.delete(societa);
		
	}

	@Override
	public List<Societa> trovaConDipendetiConRedditoAnnuoMaggioreDi(int redditoAnno) {
		return societaRepository.findAllDistinctByDipendenti_RedditoAnnuoLordoGreaterThan(redditoAnno);
	}

}
