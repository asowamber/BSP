package fr.bsp.ban;

public class BanAddress {
	
	
	
	public String id,nom_voie,id_fantoir,numero,rep,code_insee,code_post,alias,nom_ld,nom_afnor,libelle_acheminement;
	public float x,y,lon,lat;
	public String nom_commune;
	
	
	
	public String get_friendly_address(){
		
		String friendly_address ="";
		
		if(nom_ld!=null)
			friendly_address += nom_ld;
		
		if(numero!=null)
			friendly_address += " "+numero;
		
		if(nom_voie!=null)
			friendly_address += " "+nom_voie;
		
		if(numero!=null)
			friendly_address += " "+nom_commune;
		
		
		
		return friendly_address;
		
	}

}
