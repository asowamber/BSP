package fr.bsp.insee;


import java.util.Date;

public class InseeRecord {
	
	public String id;
	
	int SIREN = -1;
	int NIC = -1;
	public String L1_NORMALISEE;
	public String L2_NORMALISEE;
	public String L3_NORMALISEE;
	public String L4_NORMALISEE;
	public String L5_NORMALISEE;
	public String L6_NORMALISEE;
	public String L7_NORMALISEE;
	String L1_DECLAREE;
	String L2_DECLAREE;
	String L3_DECLAREE;
	String L4_DECLAREE;
	String L5_DECLAREE;
	String L6_DECLAREE;
	String L7_DECLAREE;
	int NUMVOIE= -1;
	char INDREP;
	String TYPVOIE;
	String LIBVOIE;
	int CODPOS= -1;
	int CEDEX= -1;
	int RPET= -1;
	String LIBREG;
	String DEPET;
	int ARRONET= -1;
	int CTONET= -1;
	int COMET= -1;
	String LIBCOM;
	String DU;
	int TU= -1;
	int UU= -1;
	int EPCI= -1;
	int TCD= -1;
	int ZEMET= -1;
	int SIEGE= -1;
	public String ENSEIGNE;
	int IND_PUBLIPO= -1;
	char DIFFCOM;
	int AMINTRET= -1;
	int NATETAB= -1;
	public String LIBNATETAB;
	public String APET700;
	String LIBAPET;
	int DAPET= -1;
	int TEFET= -1;
	String LIBTEFET;
	int EFETCENT= -1;
	int DEFET= -1;
	int ORIGINE= -1;
	Date DCRET;
	Date DDEBACT;
	int ACTIVNAT= -1;
	int LIEUACT= -1;
	int ACTISURF= -1;
	char SAISONAT;
	char MODET;
	char PRODET;
	int PRODPART= -1;
	int AUXILT= -1;
	String NOMEN_LONG;
	String SIGLE;
	String NOM;
	String PRENOM;
	String CIVILITE;
	String RNA;
	int NICSIEGE= -1;
	int RPEN= -1;
	String DEPCOMEN;
	String ADR_MAIL;
	int NJ= -1;
	String LIBNJ;
	String APEN700;
	String LIBAPEN;
	int DAPEN= -1;
	String APRM;
	int ESS= -1;
	Date DATEESS;
	int TEFEN= -1;
	String LIBTEFEN;
	int EFENCENT= -1;
	int DEFEN= -1;
	String CATEGORIE;
	Date DCREN;
	Date AMINTREN;
	int MONOACT= -1;
	char MODEN;
	char PRODEN;
	int ESAANN= -1;
	int TCA= -1;
	String ESAAPEN;
	String ESASEC1N;
	String ESASEC2N;
	String ESASEC3N;
	String ESASEC4N;
	char VMAJ;
	int VMAJ1= -1;
	int VMAJ2= -1;
	int VMAJ3= -1;
	Date DATEMAJ;
	String EVE;
	Date DATEVE;
	int TYPCREH= -1;
	Date DREACTET;
	Date DREACTEN;
	int MADRESSE= -1;
	int MENSEIGNE= -1;
	int MAPET= -1;
	int MPRODET= -1;
	int MAUXILT= -1;
	int MNOMEN= -1;
	int MSIGLE= -1;
	int MNICSIEGE= -1;
	int MNJ= -1;
	int MAPEN= -1;
	int MPRODEN= -1;
	int SIRETPS= -1;
	int TEL= -1;

	public String getFriendlyName(){
		
		String SIMPLE_LIB=null;
		
		if(ENSEIGNE==null)
		{
								
			if(L2_NORMALISEE!=null)
				SIMPLE_LIB = L2_NORMALISEE;
			//else if((!L1_NORMALISEE.startsWith("MONSIEUR")&&!L1_NORMALISEE.startsWith("MADAME")))
			else
			{
				SIMPLE_LIB =L1_NORMALISEE;
			}
		}
		else
		{
			SIMPLE_LIB = ENSEIGNE;
			
		}
		
		return SIMPLE_LIB;
		
	}
	
	public String getFriendlyFullName(){
		
		String SIMPLE_LIB=null;
		
		if(ENSEIGNE==null)
		{
								
			if(L2_NORMALISEE!=null)
				SIMPLE_LIB = L2_NORMALISEE+" "+L4_NORMALISEE+" "+LIBCOM;
			//else if((!L1_NORMALISEE.startsWith("MONSIEUR")&&!L1_NORMALISEE.startsWith("MADAME")))
			else
			{
				SIMPLE_LIB =L1_NORMALISEE+" "+L4_NORMALISEE+" "+" "+LIBCOM;
			}
		}
		else
		{
			SIMPLE_LIB = ENSEIGNE + /*" " + l2_normalisee +*/ " " + L4_NORMALISEE + " " +" "+LIBCOM;
			
		}
		
		return SIMPLE_LIB;
		
	}
	
	
	public String getFriendlyAddressName(){
		
		String SIMPLE_LIB=null;
		
		
		SIMPLE_LIB = L4_NORMALISEE+" "+LIBCOM;
			
		
		return SIMPLE_LIB;
		
	}
}
