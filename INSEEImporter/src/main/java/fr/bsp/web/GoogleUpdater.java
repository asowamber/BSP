package fr.bsp.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

import fr.bsp.insee.ElasticController;
import fr.bsp.insee.InseeRecord;
import fr.bsp.insee.InseeRecords;

public class GoogleUpdater {
	
	GeoApiContext context;

	public static void main(String[] args) {
		
		
		try {
			
			GoogleUpdater gu = new GoogleUpdater();
			
			
			
			
			SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			Date date= spf.parse("2017-10-01T13:30:14.641Z");
			
			ElasticController es =ElasticController.getInstance();
			
			InseeRecords irs = es.getRecords(date,0,100);
			int i = 0;
			
			while ( i<irs.lenght())
			{
			
				try {
			
					//System.out.println(irs.get(i).SIMPLE_LIB);
						
					PlacesSearchResult placessearchresult =  gu.searchPlace(irs.get(i));
					
					if (placessearchresult!=null)
					{
						System.out.println(es.postGoogleInfo(irs.get(i).id, placessearchresult));
					}
					
						Thread.sleep(2000);
						System.out.println();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			
			i++;
			}
			
			es.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		

	}
	
	
	GoogleUpdater()
	{
		/*
		SocketAddress addr = new
				InetSocketAddress("proxydch.transgourmet.fr", 8080);
				Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
				*/
				
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyAQi8SML9kR1tkPSepCRzChf9G6kzC_5fA")
			    //.proxy(proxy)
			    .build();
		
		this.context = context;
	}
	
	PlacesSearchResult searchPlace(InseeRecord inseeRecord) throws ApiException, InterruptedException, IOException
	{
		System.out.println("L1_NORMALISEE "+inseeRecord.L1_NORMALISEE);
		System.out.println("L2_NORMALISEE "+inseeRecord.L2_NORMALISEE);
		System.out.println("L3_NORMALISEE "+inseeRecord.L3_NORMALISEE);
		System.out.println("L4_NORMALISEE "+inseeRecord.L4_NORMALISEE);
		System.out.println("ENSEIGNE "+inseeRecord.ENSEIGNE);
		System.out.println("SIMPLE_LIB "+inseeRecord.getFriendlyFullName());
		
		PlacesSearchResult placessearchresult = null;
		
		//if(inseeRecord.SIMPLE_LIB!=null)
		{
			TextSearchRequest tsr= PlacesApi.textSearchQuery(context, inseeRecord.getFriendlyFullName());
			
			tsr.language("fr");
			
			
			if((!inseeRecord.APET700.equals("5629B")))
			{
				PlacesSearchResponse psr = tsr.await();

				if((psr.results.length>0)
						&&((Arrays.asList(psr.results[0].types).contains(PlaceType.FOOD.toString()))
						||(Arrays.asList(psr.results[0].types).contains(PlaceType.RESTAURANT.toString()))
						||(Arrays.asList(psr.results[0].types).contains(PlaceType.BAKERY.toString()))
						||(Arrays.asList(psr.results[0].types).contains(PlaceType.LODGING.toString()))
						||(Arrays.asList(psr.results[0].types).contains(PlaceType.BAR.toString()))
						)
						)
				{
						System.out.println(psr.results[0].name+" "+psr.results[0].formattedAddress+" "+Arrays.asList(psr.results[0].types));
						placessearchresult = psr.results[0];
				}
				else
				{
					System.out.println("Inconnu");
				}
			}
			else
			{
				System.out.println("Non pris en charge");
			}
		
		}
		
		return placessearchresult;
	}
	
}
