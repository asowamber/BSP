package fr.bsp.ban;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BanAddresses {
	
	List<BanAddress> banaddresses = new ArrayList<BanAddress>();
	Date date=null;
	
	public BanAddresses(Date date)
	{
		this.date=date;
	}
	
	public void add(BanAddress banaddress)
	{
		banaddresses.add(banaddress);
	}
	
	
	public BanAddress get(int i)
	{
		
		return banaddresses.get(i);
	}
	
	public int lenght()
	{
		
		return banaddresses.size();
	}

}
