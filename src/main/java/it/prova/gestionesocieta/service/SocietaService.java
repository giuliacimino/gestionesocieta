package it.prova.gestionesocieta.service;

import java.util.List;

import it.prova.gestionesocieta.model.Societa;

public interface SocietaService {
	public void inserisciSocieta(Societa societaInstance);
	public List<Societa> findByExample(Societa example);
	public void rimuovi (Societa societa) throws Exception;
	public List<Societa> trovaConDipendetiConRedditoAnnuoMaggioreDi (int redditoAnno);
	
	
	



}
