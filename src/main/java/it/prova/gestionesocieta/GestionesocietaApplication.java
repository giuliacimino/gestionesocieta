package it.prova.gestionesocieta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionesocieta.service.BatteriaDiTestService;

@SpringBootApplication
public class GestionesocietaApplication implements CommandLineRunner {
	
	@Autowired 
	BatteriaDiTestService batteriaDiTestService;

	public static void main(String[] args) {
		SpringApplication.run(GestionesocietaApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("################ START   #################");
		System.out.println("################ eseguo i test  #################");
//		batteriaDiTestService.testInserisciNuovaSocieta();
//		batteriaDiTestService.testFindByExample();
//		batteriaDiTestService.testRimuoviSocieta();
//		batteriaDiTestService.testInserisciDipendente();
//		batteriaDiTestService.testAggiornaDipendente();
		batteriaDiTestService.testTrovaConDipendetiConRedditoAnnuoMaggioreDi();
		
		System.out.println("################ END   #################");
	}

}
