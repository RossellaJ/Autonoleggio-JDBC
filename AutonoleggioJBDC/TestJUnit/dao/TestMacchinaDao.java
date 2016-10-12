package dao;

import static org.junit.Assert.*;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import model.Macchina;



import org.junit.Before;
import org.junit.Test;

public class TestMacchinaDao {


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
	public void testCreazionemacchina() {
		
		
		MacchinaDao pDao = new MacchinaDao(); //istanzio oggetto della classe PersonaDao
		
		int n=pDao.creaMacchina("audiA3", "TOdrf5897p"); //per richiamare il primo metodo nella classe Dao
		
		assertNotNull(n);
	}
	
	@Test
	public void testLeggimacchina(){
		
		MacchinaDao pDao = new MacchinaDao(); //istanzio oggetto della classe PersonaDao
		
		pDao.creaMacchina("bmw55","MIpot58457p78"); //per richiamare il primo metodo nella classe Dao
		
		Macchina p=pDao.leggiMacchinaconTarga("MIpot58457p78");
		
		assertNotNull(p);
		
	}
	
	@Test
	public void testAggiornaMacchina(){
		
		MacchinaDao pDao = new MacchinaDao(); //istanzio oggetto della classe PersonaDao
		
		int id_macchina = 2;
		
		pDao.aggiornaMacchina(id_macchina, 10);
		
		assertTrue(true);
	
	}
	
	@Test
	public void testRimuoviPersona(){
		
		MacchinaDao pDao = new MacchinaDao(); //istanzio oggetto della classe PersonaDao
		
		int id_persona = 8;
		
		pDao.rimuoviMacchina(id_persona);
		
		assertTrue(true);
		
		
	}
	
	
	
	
	
	
	
	

}



