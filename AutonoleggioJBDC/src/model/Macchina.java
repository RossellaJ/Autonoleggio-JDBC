package model;

public class Macchina {
	
	private int id_macchina;
	private String modello;
	private String targa;
	
	
	
	



	public Macchina(int id_macchina, String modello, String targa) {
		
		this.id_macchina = id_macchina;
		this.modello = modello;
		this.targa = targa;
	}






	public Macchina(String modello, String targa) {
		
		this.modello = modello;
		this.targa = targa;
	}



	public String getModello() {
		return modello;
	}



	public void setModello(String modello) {
		this.modello = modello;
	}



	public String getTarga() {
		return targa;
	}



	public void setTarga(String targa) {
		this.targa = targa;
	}
	
	

	public int getId_macchina() {
		return id_macchina;
	}
	
	
	
	public void setId_macchina(int id_macchina) {
		this.id_macchina = id_macchina;
	}
}
