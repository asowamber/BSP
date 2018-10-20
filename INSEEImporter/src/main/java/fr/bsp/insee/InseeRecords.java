package fr.bsp.insee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InseeRecords {
	
	private Date dupdate;
	private List<InseeRecord> inseerecords = new ArrayList<InseeRecord>();
	
	public InseeRecords()
	{
		this.dupdate = new Date();
	}
	
	public InseeRecords(Date dupdate)
	{
		this.dupdate = dupdate;
	}
	
	public void add(InseeRecord record)
	{
		inseerecords.add(record);
	}
	
	public InseeRecord get( int index){
		
		
		return inseerecords.get(index);
	}
	
	public int lenght(){
		
		return inseerecords.size();
	}
	
	public Date getDate()
	{
		return dupdate;
	}

	String toBulkJSON(){
		
		StringBuilder json=new StringBuilder();
		
		
		/*
		
		Iterator<InseeRecord> it = inseerecords.iterator();
		
		while(it.hasNext())
		{
			InseeRecord inseerecord =it.next();
			json.append("{\"index\":{\"_index\":\"clients\",\"_type\":\"infos\",\"_id\":\""+inseerecord.SIREN+"\"} }\n");       
			
			JSONObject objbulk = new JSONObject();
			
			JSONArray list = new JSONArray();
			
			JSONObject obj = new JSONObject();
			
			obj.put("insee.siren", inseerecord.SIREN);
			obj.put("insee.nic", inseerecord.NIC);
			obj.put("insee.l1_normalisee", inseerecord.L1_NORMALISEE);
			obj.put("insee.l2_normalisee", inseerecord.L1_NORMALISEE);
			obj.put("insee.l3_normalisee", inseerecord.L1_NORMALISEE);
			obj.put("insee.l4_normalisee", inseerecord.L1_NORMALISEE);
			obj.put("insee.l5_normalisee", inseerecord.L1_NORMALISEE);
			obj.put("insee.l6_normalisee", inseerecord.L1_NORMALISEE);
			obj.put("insee.l7_normalisee", inseerecord.L1_NORMALISEE);
			obj.put("insee.l1_declaree", inseerecord.L1_DECLAREE);
			obj.put("insee.l2_declaree", inseerecord.L2_DECLAREE);
			obj.put("insee.l3_declaree", inseerecord.L3_DECLAREE);
			obj.put("insee.l4_declaree", inseerecord.L4_DECLAREE);
			obj.put("insee.l5_declaree", inseerecord.L5_DECLAREE);
			obj.put("insee.l6_declaree", inseerecord.L6_DECLAREE);
			obj.put("insee.l7_declaree", inseerecord.L7_DECLAREE);
			
			obj.put("insee.numvoie", inseerecord.NUMVOIE);
			obj.put("insee.indrep", inseerecord.INDREP);
			obj.put("insee.typvoie", inseerecord.TYPVOIE);
			obj.put("insee.libvoie", inseerecord.LIBVOIE);
			obj.put("insee.codpos", inseerecord.CODPOS);
			obj.put("insee.cedex", inseerecord.CEDEX);
			obj.put("insee.rpet", inseerecord.RPET);
			obj.put("insee.libreg", inseerecord.LIBREG);
			obj.put("insee.depet", inseerecord.DEPET);
			obj.put("insee.arronet", inseerecord.ARRONET);
			obj.put("insee.ctonet", inseerecord.CTONET);
			obj.put("insee.comet", inseerecord.COMET);
			obj.put("insee.libcom", inseerecord.LIBCOM);
			obj.put("insee.siege", inseerecord.SIEGE);
			obj.put("insee.enseigne", inseerecord.ENSEIGNE);
			
			obj.put("insee.natetab", inseerecord.NATETAB);
			obj.put("insee.libnatetab", inseerecord.LIBNATETAB);
			obj.put("insee.apet700", inseerecord.APET700);
			obj.put("insee.libapet", inseerecord.LIBAPET);
			obj.put("insee.dapet", inseerecord.DAPET);
			obj.put("insee.tefet", inseerecord.TEFET);
			obj.put("insee.libtefet", inseerecord.LIBTEFET);
			obj.put("insee.efetcent", inseerecord.EFETCENT);
			obj.put("insee.defet", inseerecord.DEFET);
			obj.put("insee.origine", inseerecord.ORIGINE);
			obj.put("insee.dcret", inseerecord.DCRET);
			obj.put("insee.ddebact", inseerecord.DDEBACT);
			obj.put("insee.activnat", inseerecord.ACTIVNAT);
			obj.put("insee.lieuact", inseerecord.LIEUACT);
			obj.put("insee.actisurf", inseerecord.ACTISURF);
			obj.put("insee.saisonat", inseerecord.SAISONAT);
			
			list.add(obj);
			
			objbulk.put("siren", inseerecord.SIREN);
			objbulk.put("etablissements", list);
			
			
			json.append(objbulk.toJSONString());
			json.append("\n");
			//obj.put("", inseerecord.);
			
			
		}
		
		
		json.append("\n");
		*/
		
		return json.toString();
	}

}
