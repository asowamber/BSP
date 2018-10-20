package fr.bsp.ban;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;



public class BanImporter {
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+1"));
		importer();

	}
	
	
	static void parseFile(File file)
	{
		
	}
	
	static void importer()
	{
		
		Reader in;
		Date currentdate = new Date();
		try {
			ElasticController elasticcontroller = ElasticController.getInstance();
			
			FilenameFilter filter = new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					if(name.endsWith(".csv"))
						return true;
					
					
					return false;
				}
			};
			
			File folder = new File("/opt/data");
			File[] listOfFiles = folder.listFiles(filter);

			    
			    
			    
			for (int i = 0; i < listOfFiles.length; i++)
			{
				
				
				System.out.println("File " + listOfFiles[i].getName());
				
				in = new FileReader(listOfFiles[i]);
				//in = new FileReader("/opt/sirc-20170930.csv");
				
				CSVFormat fmt = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader();
				Iterable<CSVRecord> records = fmt.parse(in);
				
				
				BanAddresses banaddresses = new BanAddresses(currentdate);
				
				
				
				for (CSVRecord record : records) {
					
					BanAddress banaddress = new BanAddress();
					
					
					
				    String value = null;
				    
				    
				    try {
				    	value =  record.get("id");
				    	if(!value.isEmpty())
				    		banaddress.id = value ;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    try {
				    	value =  record.get("nom_voie");
				    	if(!value.isEmpty())
				    		banaddress.nom_voie =  value ;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    try {
				    	value =  record.get("id_fantoir");
				    	if(!value.isEmpty())
				    		banaddress.id_fantoir=value;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    try {
				    	value =  record.get("numero");
				    	if(!value.isEmpty())
				    		banaddress.numero=value;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    try {
				    	value =  record.get("rep");
				    	if(!value.isEmpty())
				    		banaddress.rep=value;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    try {
				    	value =  record.get("code_insee");
				    	if(!value.isEmpty())
				    		banaddress.code_insee=value;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    try {
				    	value =  record.get("code_post");
				    	if(!value.isEmpty())
				    		banaddress.code_post=value;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    try {
				    	value =  record.get("alias");
				    	if(!value.isEmpty())
				    		banaddress.alias=value;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    
				    try {
				    	value =  record.get("nom_ld");
				    	if(!value.isEmpty())
				    		banaddress.nom_ld=value;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    try {
				    	value =  record.get("nom_afnor");
				    	if(!value.isEmpty())
				    		banaddress.nom_afnor=value;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    try {
				    	
				    	
				    	value =  record.get("x");
				    	if(!value.isEmpty())
				    		banaddress.x=Float.valueOf(value) ;
		
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    try {
				    	value =  record.get("y");
				    	if(!value.isEmpty())
				    		banaddress.y=Float.valueOf(value) ;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    try {
				    	value =  record.get("lon");
				    	if(!value.isEmpty())
				    		banaddress.lon=Float.valueOf(value) ;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    try {
				    	value =  record.get("lat");
				    	if(!value.isEmpty())
				    		banaddress.lat=Float.valueOf(value) ;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    try {
				    	value =  record.get("nom_commune");
				    	if(!value.isEmpty())
				    		banaddress.nom_commune=value ;
				    	}
				    	catch (Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    
				    banaddresses.add(banaddress);
					   
				    
				    if(banaddresses.lenght()>=5000)
				    {
				    	elasticcontroller.postBulk(banaddresses);
				    	banaddresses = new BanAddresses(currentdate);
				    	
				    }
				    
				    
				}
				
				if(banaddresses.lenght()>0)
			    {
			    	elasticcontroller.postBulk(banaddresses);
			    }
				
				in.close();
				
				
			}
				elasticcontroller.close();
				
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
