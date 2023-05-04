package it.prova.gestionesocieta.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	SocietaService societaService;

	@Autowired
	DipendenteService dipendenteService;

	public void testInserisciNuovaSocieta() {
		Long nowInMillisecondi = new Date().getTime();

		Societa nuovaSocieta = new Societa("sephora", "via dei castani 1", LocalDate.of(2010, 12, 10));
		if (nuovaSocieta.getId() != null)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");
		// salvo
		societaService.inserisciSocieta(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");

		System.out.println("testInserisciNuovaSocieta........OK");
	}

	public void testFindByExample() {
		Long nowInMillisecondi = new Date().getTime();

		Societa nike = new Societa("nike", "via delle rose 2", LocalDate.of(2002, 11, 10));
		societaService.inserisciSocieta(nike);

		Societa adidas = new Societa("adidas", "via delle primule 1", LocalDate.of(2001, 9, 20));
		societaService.inserisciSocieta(adidas);

		Societa esempio = new Societa("adidas");
		List<Societa> risultatiRicerca = societaService.findByExample(esempio);
		System.out.println(risultatiRicerca);

	}

	public void testRimuoviSocieta() throws Exception {
		Societa lancome = new Societa("lancome", "via delle margherite 1", LocalDate.of(1987, 8, 20));
		societaService.inserisciSocieta(lancome);
		if (lancome.getId() == null) {
			throw new RuntimeException("errore: societa non inserita.");
		}
		Dipendente dipendente1 = new Dipendente("mario", "rossi", LocalDate.of(2012, 6, 12), 20000, lancome);
		dipendenteService.inserisciNuovoDipendente(dipendente1);
		if (dipendente1.getId() == null) {
			throw new RuntimeException("errore: non è stato inserito il dipendente.");
		}

		dipendente1.setSocieta(lancome);
		try {
			societaService.rimuovi(lancome);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("*********** testRimuoviSocieta: fine ***********");

	}

	public void testInserisciDipendente() {
		System.out.println("*********** testInserisciDipendente: inizio ***********");

		Societa chanel = new Societa("chanel", "via delle camelie 1", LocalDate.of(1990, 9, 10));
		societaService.inserisciSocieta(chanel);
		if (chanel.getId() == null) {
			throw new RuntimeException("errore: societa non inserita.");
		}
		Dipendente dipendente3 = new Dipendente("maria", "mario", LocalDate.of(2012, 11, 2), 30000, chanel);

		dipendenteService.inserisciNuovoDipendente(dipendente3);
		dipendente3.setSocieta(chanel);
		if (dipendente3.getId() == null) {
			throw new RuntimeException("errore: dipendente non inserito.");

		}
		System.out.println("*********** testInserisciDipendente: fine ***********");

	}
	
	public void testAggiornaDipendente () {
		Societa chanel = new Societa("chanel", "via delle camelie 1", LocalDate.of(1990, 9, 10));
		societaService.inserisciSocieta(chanel);
		if (chanel.getId() == null) {
			throw new RuntimeException("errore: societa non inserita.");
		}
		Dipendente dipendente3 = new Dipendente("maria", "mario", LocalDate.of(2012, 11, 2), 30000, chanel);

		dipendenteService.inserisciNuovoDipendente(dipendente3);
		dipendente3.setSocieta(chanel);
		String nuovoNome = "Marco";
		dipendente3.setNome(nuovoNome);
		dipendenteService.modificaDipendente(dipendente3);
		System.out.println(dipendente3);
		System.out.println("*********** testAggiornaDipendente: fine ***********");

	}
	
	public void testTrovaConDipendetiConRedditoAnnuoMaggioreDi () {
		System.out.println("*********** testTrovaConDipendetiConRedditoAnnuoMaggioreDi: inizio ***********");
		int redditoEsempio= 20000;
		Societa chanel = new Societa("chanel", "via delle camelie 1", LocalDate.of(1990, 9, 10));
		societaService.inserisciSocieta(chanel);
		if (chanel.getId() == null) {
			throw new RuntimeException("errore: societa non inserita.");
		}
		Societa nike= new Societa("nike", "via delle scarpe 1", LocalDate.of(1980, 9, 10));
		societaService.inserisciSocieta(nike);
		if (nike.getId()==null) {
			throw new RuntimeException("errore: nike non inserita.");
		}
		Dipendente dipendente3 = new Dipendente("maria", "mario", LocalDate.of(2012, 11, 2), 30000, chanel);
		dipendenteService.inserisciNuovoDipendente(dipendente3);
		Dipendente dipendente4 = new Dipendente("sofia", "gatta", LocalDate.of(2010, 7, 12), 150000, nike);
		dipendenteService.inserisciNuovoDipendente(dipendente4);
		
		List<Societa> listaSocietaConDipendentiConRedditoAnnuoMaggioreDi=   societaService.trovaConDipendetiConRedditoAnnuoMaggioreDi(redditoEsempio);
		System.out.println(listaSocietaConDipendentiConRedditoAnnuoMaggioreDi);
		System.out.println("*********** testTrovaConDipendetiConRedditoAnnuoMaggioreDi: fine ***********");

		
	}

}
