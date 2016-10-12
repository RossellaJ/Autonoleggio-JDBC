package dao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Macchina;


public class MacchinaDao {
	
	//1) creare
		public int creaMacchina(String modello, String targa) {
			
			
			int id=0;
			
			Connection con;
		    String sql="insert into MACCHINA(MODELLO,TARGA) VALUES (?,?)";
		    
			PreparedStatement pst = null;
			ResultSet res = null; 
			
			try {
				con = DataSource.getInstance().getConnection();
				pst= con.prepareStatement(sql,new String[]{"id_macchina"});
				
				
				pst.setString(1, modello);
				pst.setString(2, targa);
			
				
				pst.executeUpdate(); //scrive la riga
				res = pst.getGeneratedKeys(); // COMANDO CHE FA RITORNARE , DOPO AVERLO LETTO, ID-macchina

				if(res.next()){
					
					id = res.getInt(1);
					
				}
			} catch (SQLException | IOException | PropertyVetoException e) {


				e.printStackTrace();

			}
			
			return id;
		}
		



		//2) read
		public Macchina leggiMacchinaconTarga(String targa) {
			
			Macchina macchina=null;
			PreparedStatement pst = null;
			ResultSet res = null;
			
			try {
				Connection con = DataSource.getInstance().getConnection();
			    String sql="select * from MACCHINA where targa=?";
				pst = con.prepareStatement(sql);
				pst.setString(1,targa);
			
				res=pst.executeQuery(); 
				
				if(res.next()){		//se esiste
					
					int id_macchina=res.getInt(1);
					String modello =res.getString(2);
					String targa1 =res.getString(3);
					
					
							
					macchina =new Macchina(id_macchina,modello,targa1);
					
				}
				
			} catch (SQLException | IOException | PropertyVetoException e) {
				e.printStackTrace();
			}finally{
				
				if (pst != null) try { pst.close(); } catch (SQLException e) {e.printStackTrace();}
			}
			
			return macchina;	
		}
		
		
		
		//3)update
		 public boolean aggiornaMacchina(int id_macchinaNuova, int id_macchinaVecchia){
				
		    	boolean prendeAggiorna=false;
		    	
		    	Connection con = null;
			    String sql="update MACCHINA set id_macchinaNuova=? where id_macchinaVecchia=?";
				PreparedStatement pst = null;
				int res = 0;
				
				try {
					con = DataSource.getInstance().getConnection();
					pst= con.prepareStatement(sql);
					
					pst.setInt(1, id_macchinaNuova);
					pst.setInt(1, id_macchinaVecchia);
					
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
				
		 public boolean rimuoviMacchina(int id_macchina){

				boolean prendeElimina = false;

				Connection con = null;
			    String sql="delete from MACCHINA where id_macchina=?";
				PreparedStatement pst = null;
				int res = 0;

				try {

					con = DataSource.getInstance().getConnection();
					pst = con.prepareStatement(sql);

					pst.setInt(1, id_macchina);

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
