package it.prova.gestionesocieta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.repository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService{
	@Autowired
	DipendenteRepository dipendenteRepository;
	
	@Transactional
	public void inserisciNuovoDipendente(Dipendente dipendente) {
		dipendenteRepository.save(dipendente);
		
		
	}

	@Transactional
	public void modificaDipendente(Dipendente dipendenteInstance) {
		dipendenteRepository.save(dipendenteInstance);
		
	}

	@Transactional(readOnly = true)
	public List<Dipendente> listaDipendenti() {
		return (List<Dipendente>) dipendenteRepository.findAll();
		
		
		
	}

}
