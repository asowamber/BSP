package fr.bsp.insee;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.metadata.MetaDataIndexTemplateService.PutRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.google.maps.model.PlacesSearchResult;

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
		});*/
		
		
		
		
		RestClient restClient = builder.build();
		
		RestHighLevelClient client =
			    new RestHighLevelClient(restClient);
		
		this.restClient=client;
		this.restClient_low=restClient;
		
	}
	
	void postBulk(InseeRecords records)
	{
		BulkRequest request = new BulkRequest();
		
		int size = records.lenght();
		
		for(int i =0; i<size; i++)
		{
			
			InseeRecord inseerecord = records.get(i);
			
			String siren = String.format("%09d", inseerecord.SIREN);
			String nic = String.format("%05d", inseerecord.NIC);
			String siret =siren+nic;
			IndexRequest indexrequest = new IndexRequest("clients", "infos", siret);
			
			
			try {
				XContentBuilder xbMapping = XContentFactory.jsonBuilder();
				xbMapping.startObject();
				
				xbMapping.field("insee_siren",siren);
				xbMapping.field("insee_nic",nic);
				xbMapping.field("insee_siret",siret);
				
				xbMapping.field("insee_friendly_fullname", inseerecord.getFriendlyFullName());
				xbMapping.field("insee_friendly_name", inseerecord.getFriendlyName());
				xbMapping.field("insee_friendly_address", inseerecord.getFriendlyAddressName());
				
				xbMapping.field("insee_dupdate", records.getDate());
				xbMapping.field("insee_l1_normalisee", inseerecord.L1_NORMALISEE);
				xbMapping.field("insee_l2_normalisee", inseerecord.L2_NORMALISEE);
				xbMapping.field("insee_l3_normalisee", inseerecord.L3_NORMALISEE);
				xbMapping.field("insee_l4_normalisee", inseerecord.L4_NORMALISEE);
				xbMapping.field("insee_l5_normalisee", inseerecord.L5_NORMALISEE);
				xbMapping.field("insee_l6_normalisee", inseerecord.L6_NORMALISEE);
				
				xbMapping.field("insee_l1_declaree", inseerecord.L1_DECLAREE);
				xbMapping.field("insee_l2_declaree", inseerecord.L2_DECLAREE);
				xbMapping.field("insee_l3_declaree", inseerecord.L3_DECLAREE);
				xbMapping.field("insee_l4_declaree", inseerecord.L4_DECLAREE);
				xbMapping.field("insee_l5_declaree", inseerecord.L5_DECLAREE);
				xbMapping.field("insee_l6_declaree", inseerecord.L6_DECLAREE);
				xbMapping.field("insee_l7_declaree", inseerecord.L7_DECLAREE);
				
				xbMapping.field("insee_numvoie", inseerecord.NUMVOIE);
				xbMapping.field("insee_indrep", inseerecord.INDREP);
				xbMapping.field("insee_typvoie", inseerecord.TYPVOIE);
				xbMapping.field("insee_libvoie", inseerecord.LIBVOIE);
				xbMapping.field("insee_codpos", inseerecord.CODPOS);
				xbMapping.field("insee_cedex", inseerecord.CEDEX);
				xbMapping.field("insee_rpet", inseerecord.RPET);
				xbMapping.field("insee_libreg", inseerecord.LIBREG);
				xbMapping.field("insee_depet", inseerecord.DEPET);
				xbMapping.field("insee_arronet", inseerecord.ARRONET);
				xbMapping.field("insee_ctonet", inseerecord.CTONET);
				xbMapping.field("insee_comet", inseerecord.COMET);
				xbMapping.field("insee_libcom", inseerecord.LIBCOM);
				xbMapping.field("insee_siege", inseerecord.SIEGE);
				xbMapping.field("insee_enseigne", inseerecord.ENSEIGNE);
				
				xbMapping.field("insee_natetab", inseerecord.NATETAB);
				xbMapping.field("insee_libnatetab", inseerecord.LIBNATETAB);
				xbMapping.field("insee_apet700", inseerecord.APET700);
				xbMapping.field("insee_libapet", inseerecord.LIBAPET);
				xbMapping.field("insee_dapet", inseerecord.DAPET);
				xbMapping.field("insee_tefet", inseerecord.TEFET);
				xbMapping.field("insee_libtefet", inseerecord.LIBTEFET);
				xbMapping.field("insee_efetcent", inseerecord.EFETCENT);
				xbMapping.field("insee_defet", inseerecord.DEFET);
				xbMapping.field("insee_origine", inseerecord.ORIGINE);
				xbMapping.field("insee_dcret", inseerecord.DCRET);
				xbMapping.field("insee_ddebact", inseerecord.DDEBACT);
				xbMapping.field("insee_activnat", inseerecord.ACTIVNAT);
				xbMapping.field("insee_lieuact", inseerecord.LIEUACT);
				xbMapping.field("insee_actisurf", inseerecord.ACTISURF);
				xbMapping.field("insee_saisonat", inseerecord.SAISONAT);
				xbMapping.field("insee_modet", inseerecord.MODET);
				xbMapping.field("insee_prodet", inseerecord.PRODET);
				xbMapping.field("insee_prodpart", inseerecord.PRODPART);
				xbMapping.field("insee_auxilt", inseerecord.AUXILT);
				xbMapping.field("insee_nomen_long", inseerecord.NOMEN_LONG);
				xbMapping.field("insee_sigle", inseerecord.SIGLE);
				xbMapping.field("insee_nom", inseerecord.NOM);
				xbMapping.field("insee_prenom", inseerecord.PRENOM);
				xbMapping.field("insee_civilite", inseerecord.CIVILITE);
				xbMapping.field("insee_rna", inseerecord.RNA);
				xbMapping.field("insee_nicsiege", inseerecord.NICSIEGE);
				xbMapping.field("insee_rpen", inseerecord.RPEN);
				xbMapping.field("insee_depcomen", inseerecord.DEPCOMEN);
				xbMapping.field("insee_adr_mail", inseerecord.ADR_MAIL);
				xbMapping.field("insee_nj", inseerecord.NJ);
				xbMapping.field("insee_libnj", inseerecord.LIBNJ);
				xbMapping.field("insee_apen700", inseerecord.APEN700);
				xbMapping.field("insee_libapen", inseerecord.LIBAPEN);
				xbMapping.field("insee_dapen", inseerecord.DAPEN);
				xbMapping.field("insee_aprm", inseerecord.APRM);
				xbMapping.field("insee_ess", inseerecord.ESS);
				xbMapping.field("insee_dateess", inseerecord.DATEESS);
				xbMapping.field("insee_tefen", inseerecord.TEFEN);
				xbMapping.field("insee_libtefen", inseerecord.LIBTEFEN);
				xbMapping.field("insee_efencent", inseerecord.EFENCENT);
				xbMapping.field("insee_defen", inseerecord.DEFEN);
				xbMapping.field("insee_categorie", inseerecord.CATEGORIE);
				xbMapping.field("insee_dcren", inseerecord.DCREN);
				xbMapping.field("insee_amintren", inseerecord.AMINTREN);
				xbMapping.field("insee_monoact", inseerecord.MONOACT);
				xbMapping.field("insee_moden", inseerecord.MODEN);
				xbMapping.field("insee_proden", inseerecord.PRODEN);
				xbMapping.field("insee_esaann", inseerecord.ESAANN);
				xbMapping.field("insee_tca", inseerecord.TCA);
				xbMapping.field("insee_esaapen", inseerecord.ESAAPEN);
				xbMapping.field("insee_esasec1n", inseerecord.ESASEC1N);
				xbMapping.field("insee_esasec2n", inseerecord.ESASEC2N);
				xbMapping.field("insee_esasec3n", inseerecord.ESASEC3N);
				xbMapping.field("insee_esasec4n", inseerecord.ESASEC4N);
				
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
	
	public Result postGoogleInfo(String id, PlacesSearchResult placessearchresult) throws IOException
	{
		
		/*
		"google.id":    "1234",
				"google.place_id":    "1234",
				"google.rating":    "4.5" 
		
		 */
		
		XContentBuilder builder = XContentFactory.jsonBuilder();
		builder.startObject();
		{
		    
		    builder.field("google.place_id", placessearchresult.placeId);
		    builder.field("google.rating", placessearchresult.rating);
		    builder.field("google.location", placessearchresult.geometry.location);
		}
		builder.endObject();
		UpdateRequest request = new UpdateRequest("clients", "infos", id)
		        .doc(builder); 
		
		UpdateResponse updateResponse = restClient.update(request);
		
		return updateResponse.getResult();
		
	}
	
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
}
