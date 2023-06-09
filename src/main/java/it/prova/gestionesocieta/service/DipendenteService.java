package it.prova.gestionesocieta.service;

import java.time.LocalDate;
import java.util.List;

import it.prova.gestionesocieta.model.Dipendente;

public interface DipendenteService {
	
	public void inserisciNuovoDipendente(Dipendente dipendente);
	public void modificaDipendente (Dipendente dipendenteInstance);
	public List<Dipendente> listaDipendenti ();
	public Dipendente cercaMaxDataAssunzioneEDataFondazioneMinoreDi (LocalDate dataFondazioneSocieta);

}
