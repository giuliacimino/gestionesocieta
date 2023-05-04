package it.prova.gestionesocieta.service;

import java.util.List;

import it.prova.gestionesocieta.model.Societa;

public interface SocietaService {
	public void inserisciSocieta(Societa societaInstance);
	public List<Societa> findByExample(Societa example);
	public void rimuovi (Long id) throws Exception;
	public Societa caricaSingolaSocietaEager(Long id);
	public List<Societa> trovaConDipendetiConRedditoAnnuoMaggioreDi (int redditoAnno);
	
	
	



}
