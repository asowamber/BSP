package fr.bsp.insee;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Importer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+1"));
		importer();
	}
	
	
	static void importer()
	{
		
		Reader in;
		try {
			ElasticController elasticcontroller = ElasticController.getInstance();
		//in = new FileReader("E:/Downloads/sirene/sirc-17804_9075_14209_201708_L_M_20170901_025839232.csv");
		in = new FileReader("/opt/sirc/sirc-20170930.csv");
		
		CSVFormat fmt = CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader();
		Iterable<CSVRecord> records = fmt.parse(in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdfmonth = new SimpleDateFormat("yyyyMM");
		Date currentDate= new Date();
		
		InseeRecords irs = new InseeRecords(currentDate);
		
		
		
		for (CSVRecord record : records) {
			
			InseeRecord ir = new InseeRecord();
			
			
			
		    String value = null;
		   // String simpleInfo ="";
		    
		    
		    //value =  record.get("APET700");
		    //String region=record.get("RPET");
			   
		    
		    /*
		    if((value.startsWith("5510Z")
		    		||value.startsWith("1071C")
		    		||value.startsWith("1071D")
		    		||value.startsWith("5610A")||value.startsWith("5610B")
		    		||value.startsWith("5629B")
		    		||value.startsWith("5621")	)
		    		//&&region.equals("11")
		    		
		    		)
		    		*/
		    {
		    	/*
		    	value =  record.get("APET700");
		    	ir.APET700=value;
		    	*/
		    	
		    	try {
			    	value =  record.get("APET700");
			    	if(value!=null&&!value.isEmpty())
			    	ir.APET700=value;
			    	}
			    	catch (Exception e)
			    	{
			    		System.out.println(e.getMessage());
			    	}
		    	
		    	try {
		    	value =  record.get("SIREN");
		    	if(value!=null&&!value.isEmpty())
		    	ir.SIREN=Integer.valueOf( value );
		    	}
		    	catch (Exception e)
		    	{
		    		System.out.println(e.getMessage());
		    	}
				
		    	try {
				value =  record.get("NIC");
				if(value!=null&&!value.isEmpty())
				ir.NIC=Integer.valueOf(value);
		    	}
		    	catch (Exception e)
		    	{
		    		System.out.println(e.getMessage());
		    	}
		    	
		    	try {
				value =  record.get("L1_NORMALISEE");
				if(value!=null&&!value.isEmpty())
				ir.L1_NORMALISEE=value;
				
				//simpleInfo=simpleInfo+ir.L1_NORMALISEE;
		    	}
		    	catch (Exception e)
		    	{
		    		System.out.println(e.getMessage());
		    	}
				    
				try {
				value =  record.get("L2_NORMALISEE");
				if(value!=null&&!value.isEmpty())
				ir.L2_NORMALISEE=value;
				
				//simpleInfo=simpleInfo+ir.L2_NORMALISEE;
				}
		    	catch (Exception e)
		    	{
		    		System.out.println(e.getMessage());
		    	}
				
				try {
				value =  record.get("L3_NORMALISEE");
				if(value!=null&&!value.isEmpty())
				ir.L3_NORMALISEE=value;
				
				//simpleInfo=simpleInfo+ir.L3_NORMALISEE;
				}
		    	catch (Exception e)
		    	{
		    		System.out.println(e.getMessage());
		    	}
				
				try {
				value =  record.get("L4_NORMALISEE");
				if(value!=null&&!value.isEmpty())
		    	ir.L4_NORMALISEE=value;
				}
		    	catch (Exception e)
		    	{
		    		System.out.println(e.getMessage());
		    	}
				
		    	try {
		    	value =  record.get("L5_NORMALISEE");
		    	if(value!=null&&!value.isEmpty())
			    ir.L5_NORMALISEE=value;
		    	}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("L6_NORMALISEE");
			    if(value!=null&&!value.isEmpty())
			    ir.L6_NORMALISEE=value;
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("L7_NORMALISEE");
			    if(value!=null&&!value.isEmpty())
			    ir.L7_NORMALISEE=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("L1_DECLAREE");
			    if(value!=null&&!value.isEmpty())
			    ir.L1_DECLAREE=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("L2_DECLAREE");
			    if(value!=null&&!value.isEmpty())
			    ir.L2_DECLAREE=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("L3_DECLAREE");
			    if(value!=null&&!value.isEmpty())
			    ir.L3_DECLAREE=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("L4_DECLAREE");
			    if(value!=null&&!value.isEmpty())
			    ir.L4_DECLAREE=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("L5_DECLAREE");
			    if(value!=null&&!value.isEmpty())
			    ir.L5_DECLAREE=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("L6_DECLAREE");
			    if(value!=null&&!value.isEmpty())
			    ir.L6_DECLAREE=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("L7_DECLAREE");
			    if(value!=null&&!value.isEmpty())
			    ir.L7_DECLAREE=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("NUMVOIE");
			    if(value!=null&&!value.isEmpty())
			    ir.NUMVOIE=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("INDREP");
			    
			    if(value!=null&&value.length()==1)
			    {
			    	ir.INDREP=value.charAt(0);
			    }
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("TYPVOIE");
			    if(value!=null&&!value.isEmpty())
			    ir.TYPVOIE=value;}
			    catch (Exception e)
		    	{
			    	
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("LIBVOIE");
			    if(value!=null&&!value.isEmpty())
			    ir.LIBVOIE=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("CODPOS");
			    if(value!=null&&!value.isEmpty())
			    ir.CODPOS=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("CEDEX");
			    if(value!=null&&!value.isEmpty())
			    ir.CEDEX=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("RPET");
			    if(value!=null&&!value.isEmpty())
			    ir.RPET=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("LIBREG");
			    if(value!=null&&!value.isEmpty())
			    ir.LIBREG=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("DEPET");
			    if(value!=null&&!value.isEmpty())
			    ir.DEPET=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ARRONET");
			    if(value!=null&&!value.isEmpty())
			    ir.ARRONET=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("CTONET");
			    if(value!=null&&!value.isEmpty())
			    ir.CTONET=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("COMET");
			    if(value!=null&&!value.isEmpty())
			    ir.COMET=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("LIBCOM");
			    if(value!=null&&!value.isEmpty())
			    ir.LIBCOM=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("DU");
			    if(value!=null&&!value.isEmpty())
			    ir.DU=value;
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("TU");
			    if(value!=null&&!value.isEmpty())
			    ir.TU=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("UU");
			    if(value!=null&&!value.isEmpty())
			    ir.UU=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("EPCI");
			    if(value!=null&&!value.isEmpty())
			    ir.EPCI=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("TCD");
			    if(value!=null&&!value.isEmpty())
			    ir.TCD=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ZEMET");
			    if(value!=null&&!value.isEmpty())
			    ir.ZEMET=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("SIEGE");
			    if(value!=null&&!value.isEmpty())
			    ir.SIEGE=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ENSEIGNE");
			    if(value!=null&&!value.isEmpty())
			    ir.ENSEIGNE=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("IND_PUBLIPO");
			    if(value!=null&&!value.isEmpty())
			    ir.IND_PUBLIPO=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("DIFFCOM");
			    if(value!=null&&value.length()==1)
			    {
			    ir.DIFFCOM=value.charAt(0);}
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("AMINTRET");
			    if(value!=null&&!value.isEmpty())
			    ir.AMINTRET=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("NATETAB");
			    if(value!=null&&!value.isEmpty())
			    ir.NATETAB=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("LIBNATETAB");
			    if(value!=null&&!value.isEmpty())
			    ir.LIBNATETAB=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("LIBAPET");
			    if(value!=null&&!value.isEmpty())
			    ir.LIBAPET=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("DAPET");
			    if(value!=null&&!value.isEmpty())
			    ir.DAPET=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("TEFET");
			    if(value!=null&&!value.isEmpty()&&!value.equals("NN"))
			    ir.TEFET=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("LIBTEFET");
			    if(value!=null&&!value.isEmpty()&&!value.equals("NN"))
			    ir.LIBTEFET=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("EFETCENT");
			    if(value!=null&&!value.isEmpty()&&!value.equals("NN"))
			    ir.EFETCENT=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("DEFET");
			    if(value!=null&&!value.isEmpty()&&!value.equals("NN"))
			    ir.DEFET=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ORIGINE");
			    if(value!=null&&!value.isEmpty()&&!value.equals("NR"))
			    ir.ORIGINE=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("DCRET");
			    
			    
			    //ir.DCRET=Date.valueOf(value);
			    if(value!=null&&!value.isEmpty())
			    ir.DCRET=sdf.parse(value);
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("DDEBACT");
			    if(value!=null&&!value.isEmpty())
			    ir.DDEBACT=sdf.parse(value);
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ACTIVNAT");
			    if(value!=null&&!value.isEmpty()&&!value.equals("NR"))
			    ir.ACTIVNAT=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("LIEUACT");
			    if(value!=null&&!value.isEmpty()&&!value.equals("NR"))
			    ir.LIEUACT=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ACTISURF");
			    if(value!=null&&!value.isEmpty())
			    ir.ACTISURF=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("SAISONAT");
			    
			    if(value!=null&&value.length()==1)
			    {
			    ir.SAISONAT=value.charAt(0);
			    }
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("MODET");
			    if(value!=null&&value.length()==1)
			    {
			    ir.MODET=value.charAt(0);
			    }
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("PRODET");
			    if(value!=null&&value.length()==1)
			    {
			    ir.PRODET=value.charAt(0);
			    }
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("PRODPART");
			    if(value!=null&&!value.isEmpty())
			    ir.PRODPART=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("AUXILT");
			    if(value!=null&&!value.isEmpty())
			    ir.AUXILT=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("NOMEN_LONG");
			    if(value!=null&&!value.isEmpty())
			    ir.NOMEN_LONG=value;
			    
			    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("SIGLE");
			    if(value!=null&&!value.isEmpty())
			    ir.SIGLE=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("NOM");
			    if(value!=null&&!value.isEmpty())
			    ir.NOM=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("PRENOM");
			    if(value!=null&&!value.isEmpty())
			    ir.PRENOM=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("CIVILITE");
			    if(value!=null&&!value.isEmpty())
			    ir.CIVILITE=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("RNA");
			    if(value!=null&&!value.isEmpty())
			    ir.RNA=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("NICSIEGE");
			    if(value!=null&&!value.isEmpty())
			    ir.NICSIEGE=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("RPEN");
			    if(value!=null&&!value.isEmpty())
			    ir.RPEN=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("DEPCOMEN");
			    if(value!=null&&!value.isEmpty())
			    ir.DEPCOMEN=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ADR_MAIL");
			    if(value!=null&&!value.isEmpty())
			    ir.ADR_MAIL=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("NJ");
			    if(value!=null&&!value.isEmpty())
			    ir.NJ=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("LIBNJ");
			    if(value!=null&&!value.isEmpty())
			    ir.LIBNJ=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("APEN700");
			    if(value!=null&&!value.isEmpty())
			    ir.APEN700=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("LIBAPEN");
			    if(value!=null&&!value.isEmpty())
			    ir.LIBAPEN=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("DAPEN");
			    if(value!=null&&!value.isEmpty())
			    ir.DAPEN=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("APRM");
			    if(value!=null&&!value.isEmpty())
			    ir.APRM=value;}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ESS");
			    if(value!=null&&!value.isEmpty())
			    ir.ESS=Integer.valueOf(value);}
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("DATEESS");
			    if(value!=null&&!value.isEmpty())
			    ir.DATEESS=sdf.parse(value);
			    
			    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    
			    try {
			    value =  record.get("TEFEN");
			    if(value!=null&&!value.isEmpty()&&!value.equals("NN"))
				    ir.TEFEN=Integer.valueOf(value);
				    
				    
				    }
				    catch (Exception e)
			    	{
				    	System.out.println(e.getMessage());
			    	}
			    
			    try {
			    value =  record.get("LIBTEFEN");
			    if(value!=null&&!value.isEmpty())
				    ir.LIBTEFEN=value;
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("EFENCENT");
			    if(value!=null&&!value.isEmpty()&&!value.equals("NN"))
				    ir.EFENCENT=Integer.valueOf(value);
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("DEFEN");
			    if(value!=null&&!value.isEmpty())
				    ir.DEFEN=Integer.valueOf(value);
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("CATEGORIE");
			    if(value!=null&&!value.isEmpty())
				    ir.CATEGORIE=value;
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("DCREN");
			    if(value!=null&&!value.isEmpty())
				    ir.DCREN=sdf.parse(value);
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("AMINTREN");
			    if(value!=null&&!value.isEmpty())
				    ir.AMINTREN=sdfmonth.parse(value);
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get( "MONOACT");
			    if(value!=null&&!value.isEmpty())
				    ir.MONOACT=Integer.valueOf(value);
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("MODEN");
			    if(value!=null&&value.length()==1)
				    ir.MODEN=value.charAt(0);
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("PRODEN");
			    if(value!=null&&value.length()==1)
				    ir.PRODEN=value.charAt(0);
				    
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ESAANN");
			    if(value!=null&&!value.isEmpty())
				    ir.ESAANN=Integer.valueOf(value);
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("TCA");
			    if(value!=null&&!value.isEmpty())
				    ir.TCA=Integer.valueOf(value);
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ESAAPEN");
			    if(value!=null&&!value.isEmpty())
				    ir.ESAAPEN=value;
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ESASEC1N");
			    if(value!=null&&!value.isEmpty())
				    ir.ESASEC1N=value;
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ESASEC2N");
			    if(value!=null&&!value.isEmpty())
				    ir.ESASEC2N=value;
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ESASEC3N");
			    if(value!=null&&!value.isEmpty())
				    ir.ESASEC3N=value;
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}
			    
			    try {
			    value =  record.get("ESASEC4N");
			    if(value!=null&&!value.isEmpty())
				    ir.ESASEC4N=value;
				    
				    
			    }
			    catch (Exception e)
		    	{
			    	System.out.println(e.getMessage());
		    	}

			    
			    irs.add(ir);
			    
			    if(irs.lenght()>=5000)
			    {
			    	elasticcontroller.postBulk(irs);
			    	irs = new InseeRecords(currentDate);
			    	
			    }
			    	
		    }
   
		}
		
		if(irs.lenght()>0)
	    {
	    	elasticcontroller.postBulk(irs);
	    	irs=null;
	    	irs = new InseeRecords(currentDate);
	    	
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
