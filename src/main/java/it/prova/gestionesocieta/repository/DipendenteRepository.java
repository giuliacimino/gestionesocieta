package it.prova.gestionesocieta.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionesocieta.model.Dipendente;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long> {
	
	@EntityGraph(attributePaths = {"societa"})
	Dipendente findByMaxEtaAndSocieta_DataFondazioneSmallerThan (LocalDate dataFondazioneSocieta);

}
