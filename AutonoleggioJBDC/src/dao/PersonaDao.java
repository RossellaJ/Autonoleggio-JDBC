package dao;


import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




import java.util.Map;
import java.util.TreeMap;

import model.Persona;


public class PersonaDao {

	
	//1) creare
	public int creaPersona(String nome, String cognome, String cF) {
		
		
		int id=0;
		
		Connection con;
	    String sql="insert into PERSONA(NOME,COGNOME,CF) VALUES (?,?,?)";
	    
		PreparedStatement pst = null;
		ResultSet res = null; //ATTENZIONE!!! USO PRAPAREDSTATMENT PER RESTITUIRE AUTOMATICAMENTE ID DELLA RIGA CHE STO CREANDO ED INSERENDO IN TABELLA.QUINDI DOVRà INSERT MA ANCHE LEGGERE L'ID IN USCITA.OCCORRE RESULTSET(PRIMA AVEVO INT CON UPDATE!!!)
		
		try {
			con = DataSource.getInstance().getConnection();
			pst= con.prepareStatement(sql,new String[]{"id_persona"});
			
			
			pst.setString(1, nome);
			pst.setString(2, cognome);
			pst.setString(3, cF);
			
			
			pst.executeUpdate(); //scrive la riga
			res = pst.getGeneratedKeys(); // COMANDO CHE FA RITORNARE , DOPO AVERLO LETTO, ID-PERSONA DI TIPO INT COME RESULTSET

			if(res.next()){
				
				id = res.getInt(1);
				
			}
		} catch (SQLException | IOException | PropertyVetoException e) {


			e.printStackTrace();

		}
		
		return id;
	}
	



	//2) read
	public Persona leggiPersonaconNominativo(String nome, String cognome) {
		
		Persona persona=null;
		PreparedStatement pst = null;
		ResultSet res = null;
		
		try {
			Connection con = DataSource.getInstance().getConnection();
		    String sql="select * from PERSONA where nome=? and cognome=?";
			pst = con.prepareStatement(sql);
			pst.setString(1,nome);
			pst.setString(2,cognome);
	
			res=pst.executeQuery(); 
			
			if(res.next()){		//se esiste
				
				int id_persona1=res.getInt(1);
				String nome1 =res.getString(2);
				String cognome1 =res.getString(3);
				String cF =res.getString(4);
				
						
				persona =new Persona(id_persona1,nome1,cognome1,cF);
				
			}
			
		} catch (SQLException | IOException | PropertyVetoException e) {
			e.printStackTrace();
		}finally{
			
			if (pst != null) try { pst.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		
		return persona;	
	}
	
	//2b) read tutte le persone
	
	 
	public Map<Integer,Persona> leggiTuttelePersone(){
	    	
			Map<Integer, Persona> listapers = new TreeMap<Integer, Persona>();
			Persona persona=null;
			
		
			PreparedStatement pst = null;
			ResultSet res = null;
			
			try {
				
				Connection con = DataSource.getInstance().getConnection();
				String sql="select * from PERSONA";
				pst = con.prepareStatement(sql);
				
				res=pst.executeQuery(); 
				
					if(res.next()){		//se esiste
					
					int id_persona=res.getInt(1);
					String nome =res.getString(2);
					String cognome =res.getString(3);
					String cF =res.getString(4);
					
							
					persona=new Persona(id_persona,nome,cognome,cF);
					
					listapers.put(persona.getId_persona(), persona);
				}
				
			} catch (SQLException | IOException | PropertyVetoException e) {
				e.printStackTrace();
			}finally{
				
				if (pst != null) try { pst.close(); } catch (SQLException e) {e.printStackTrace();}
			}
			
			return listapers;
	    	
	    }
	    
	    
	
	//3)update
	 public boolean aggiornaPersona(int id_persona1, int id_persona2){
			
	    	boolean prendeAggiorna=false;
	    	
	    	Connection con = null;
		    String sql="update PERSONA set id_persona1=? where id_persona2=?";
			PreparedStatement pst = null;
			int res = 0;
			
			try {
				con = DataSource.getInstance().getConnection();
				pst= con.prepareStatement(sql);
				
				pst.setInt(1, id_persona1);
				pst.setInt(2, id_persona2);
				
				res= pst.executeUpdate();

				if(res==1){
					
					prendeAggiorna=true;
				}
	    	
			return prendeAggiorna;
	    	
			} catch (SQLException | IOException | PropertyVetoException e) {


				e.printStackTrace();

			}finally{

				if (pst != null)

					try {

						pst.close();

					} catch (SQLException e) {

						e.printStackTrace();

					}

			}	return prendeAggiorna;
	    }
	  
			//4)DELETE
			
			public boolean rimuoviPersona(int id_persona){

				boolean prendeElimina = false;

				Connection con = null;
			    String sql="delete from PERSONA  where id_persona=?";
				PreparedStatement pst = null;
				int res = 0;

				try {

					con = DataSource.getInstance().getConnection();
					pst = con.prepareStatement(sql);

					pst.setInt(1, id_persona);

					res = pst.executeUpdate();

					if(res==1){

						prendeElimina = true;

					}

				} catch (SQLException | IOException | PropertyVetoException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}finally{

					if (pst != null)

						try {

							pst.close();

						} catch (SQLException e) {

							e.printStackTrace();

						}

				}

				return prendeElimina;

			}
			
			
			
			
				
		
		
		
		
		

	
	
	
	
	
	
	
	
}
