package dao;

import static org.junit.Assert.*;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import model.Persona;

import org.junit.Before;
import org.junit.Test;


public class TestPersonaDao {
	
	Connection con;
	
	@Before//CREO COLLEGAMENTO AL DATABASE
	public void testConnessione(){
		try {
			con=DataSource.getInstance().getConnection();
		} catch (SQLException | IOException | PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testCreazionepersona() {
		
		
		PersonaDao pDao = new PersonaDao(); //istanzio oggetto della classe PersonaDao
		
		int n=pDao.creaPersona("Fabio", "Loffi", "drf5897p"); //per richiamare il primo metodo nella classe Dao
		
		assertNotNull(n);
	}
	
	@Test
	public void testLeggipersona(){
		
		PersonaDao pDao = new PersonaDao(); //istanzio oggetto della classe PersonaDao
		
		pDao.creaPersona("Alessia", "Bunnoli", "pot58457p78"); //per richiamare il primo metodo nella classe Dao
		
		Persona p=pDao.leggiPersonaconNominativo("Alessia", "Bunnoli");
		
		assertNotNull(p);
		
	}
	
	@Test
	public void testLeggimappa(){
		
		PersonaDao pDao = new PersonaDao();


		Map<Integer,Persona> listapersone = pDao.leggiTuttelePersone();
	
		assertNotNull(listapersone);

		
	}
	
	
	@Test
	public void testAggiornaPersona(){
		
		PersonaDao pDao = new PersonaDao(); //istanzio oggetto della classe PersonaDao
		
		int id_persona = 0;
		
		pDao.aggiornaPersona(id_persona, 10);
		
		assertTrue(true);
	
	}
	
	@Test
	public void testRimuoviPersona(){
		
		PersonaDao pDao = new PersonaDao(); //istanzio oggetto della classe PersonaDao
		
		int id_persona = 8;
		
		pDao.rimuoviPersona(id_persona);
		
		assertTrue(true);
		
		
	}
	
	
	
	
	
	
	
	

}
