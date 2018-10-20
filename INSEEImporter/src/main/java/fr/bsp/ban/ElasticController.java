package fr.bsp.ban;


import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.http.HttpHost;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public class ElasticController {
	
	

	RestHighLevelClient restClient;
	RestClient restClient_low;
	
	private ElasticController() throws UnknownHostException
	{
		
		
		RestClientBuilder builder = RestClient.builder(new HttpHost("elasticv2.sowamber.com"));
		/*
		builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
		    @Override
		    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
		        return httpClientBuilder.setProxy(new HttpHost("proxydch.transgourmet.fr", 8080, "http"));  
		    }
		});
		*/
		
		
		
		RestClient restClient = builder.build();
		
		RestHighLevelClient client =
			    new RestHighLevelClient(restClient);
		
		this.restClient=client;
		this.restClient_low=restClient;
		
	}
	
	void postBulk(BanAddresses banaddresses)
	{
		BulkRequest request = new BulkRequest();
		
		int size = banaddresses.lenght();
		
		for(int i =0; i<size; i++)
		{
			
			BanAddress banaddresse = banaddresses.get(i);
			
			
			IndexRequest indexrequest = new IndexRequest("adresses", "infos", banaddresse.id);
			
			
			try {
				XContentBuilder xbMapping = XContentFactory.jsonBuilder();
				xbMapping.startObject();
				
				
				xbMapping.field("ban_dupdate",banaddresses.date);
				xbMapping.field("ban_friendly_address", banaddresse.get_friendly_address() );
				xbMapping.field("ban_id", banaddresse.id );
				xbMapping.field("ban_nom_voie", banaddresse.nom_voie );
				xbMapping.field("ban_id_fantoir", banaddresse.id_fantoir );
				xbMapping.field("ban_numero", banaddresse.numero );
				xbMapping.field("ban_rep", banaddresse.rep );
				xbMapping.field("ban_code_insee", banaddresse.code_insee );
				
				xbMapping.field("ban_code_post", banaddresse.code_post );
				xbMapping.field("ban_alias", banaddresse.alias );
				xbMapping.field("ban_nom_ld", banaddresse.nom_ld );
				xbMapping.field("ban_nom_afnor", banaddresse.nom_afnor );
				xbMapping.field("ban_libelle_acheminement", banaddresse.libelle_acheminement );
				xbMapping.field("ban_x", banaddresse.x );
				xbMapping.field("ban_y", banaddresse.y);
				
				xbMapping.field("ban_geopoint", banaddresse.lat+", "+ banaddresse.lon );
				
				xbMapping.field("ban_nom_commune", banaddresse.nom_commune );
				
				
				xbMapping.endObject();
				xbMapping.close();
				
				indexrequest.source(xbMapping);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.add(indexrequest);
		}
		
		try {
			BulkResponse bulkResponse = restClient.bulk(request);
			
			System.out.println(bulkResponse.buildFailureMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public static ElasticController getInstance() throws UnknownHostException
	{
		return new ElasticController();
		
	}
	
	public void close() throws IOException
	{
		restClient_low.close();
	}
	
	/*
	
	public InseeRecords  getRecords(Date date,int from, int size){
		
		
		InseeRecords irs = new InseeRecords();
		
		SearchRequest searchRequest = new SearchRequest("clients"); 
		searchRequest.types("infos");
		
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
		
		sourceBuilder.from(from); 
		sourceBuilder.size(size); 
		
		//QueryBuilders.
		sourceBuilder.query(QueryBuilders.termQuery("insee.comet", 42));
		
		searchRequest.source(sourceBuilder);
		
		try {
			//restClient.
			SearchResponse searchResponse = restClient.search(searchRequest);
			RestStatus status = searchResponse.status();
			
			//if(status.equals(RestStatus.ACCEPTED))
			{
			
			SearchHits hits = searchResponse.getHits();
			
			SearchHit[] searchHits = hits.getHits();
			for (SearchHit hit : searchHits) {
			    // do something with the SearchHit
				
				Map<String, Object> sourceAsMap = hit.getSourceAsMap();
				String id = hit.getId();
				int tefet = (Integer) sourceAsMap.get("insee.tefet");
				String enseigne = (String) sourceAsMap.get("insee.enseigne");
				String l1_normalisee = (String) sourceAsMap.get("insee.l1_normalisee");
				String l2_normalisee = (String) sourceAsMap.get("insee.l2_normalisee");
				String l3_normalisee = (String) sourceAsMap.get("insee.l3_normalisee");
				String l4_normalisee = (String) sourceAsMap.get("insee.l4_normalisee");
				String libcom = (String) sourceAsMap.get("insee.libcom");
				String libapet = (String) sourceAsMap.get("insee.libapet");
				String APET700 = (String) sourceAsMap.get("insee.apet700");
				int codpos = (Integer) sourceAsMap.get("insee.codpos");
				
				
				
				
				if(tefet!=1&&tefet!=0)
				{
					InseeRecord ir = new InseeRecord();
					if(enseigne==null)
					{
											
						if(l2_normalisee!=null)
							ir.SIMPLE_LIB = l2_normalisee+" "+l4_normalisee+" "+codpos+" "+libcom;
						else if((!l1_normalisee.startsWith("MONSIEUR")&&!l1_normalisee.startsWith("MADAME")))
						{
							ir.SIMPLE_LIB =l1_normalisee+" "+l4_normalisee+" "+codpos+" "+libcom;
						}
					}
					else
					{
						ir.SIMPLE_LIB = enseigne + " " + l2_normalisee +" " + l4_normalisee + " " + codpos+" "+libcom;
						
					}
					
					ir.id=id;
					ir.TEFET=tefet;
					ir.ENSEIGNE = enseigne ;
					ir.L1_NORMALISEE = l1_normalisee;
					ir.L2_NORMALISEE = l2_normalisee;
					ir.L3_NORMALISEE = l3_normalisee;
					ir.L4_NORMALISEE = l4_normalisee;
					ir.LIBCOM = libcom ;
					ir.LIBAPET = libapet;
					ir.APET700 = APET700;
										
					irs.add(ir);
					
				}
				
				
				
				
			}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irs;
	}
	*/
}
