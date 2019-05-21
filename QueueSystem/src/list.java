import java.time.ZoneId;
import java.time.ZonedDateTime;

public class list 
{
	private node<infoCounter> p;
	
	public list()
	{
		p = null;
	}
	
	public Boolean isEmpty() 
	{
		if(p == null)
			return true;
		return false;
	}
	
	public void in (infoCounter x)
	{
		node<infoCounter> n = new node<infoCounter>(x, p);
		p = n;
	}
	
	public infoCounter rm(infoCounter n)
	{
		infoCounter t = new infoCounter('0', -1);
		
		if (p.getInfo().cmp(n))
		{
			t = p.getInfo();
			p = p.getPtrNext();
		}
		else
		{
			node<infoCounter> pa = p;
			
			while (pa.getPtrNext() != null && 
					!pa.getPtrNext().getInfo().cmp(n))
					pa = pa.getPtrNext();
			
			if (pa.getPtrNext() != null)
			{
				t = pa.getPtrNext().getInfo();
				pa.setPtrNext(pa.getPtrNext().getPtrNext());				
			}
		}
		return t;
	}
	
	public infoCounter search()
	{
		node<infoCounter> pa = p;
		infoCounter t = new infoCounter('0', -1);
		//scegliere differenza appropriata
		//continua a scorrere mentre adesso - ultimaAttività < tot
				
		while (pa != null &&
				ZonedDateTime.now(ZoneId.of("Europe/Paris")).toInstant().getEpochSecond()
				- pa.getInfo().getT().getEpochSecond() < 5)
				pa = pa.getPtrNext();
		
		if (pa != null)
			t = pa.getInfo();
		
		//ritorno appena trovo una coda inattiva
			
		return t;
	}
	
	public infoCounter search(infoCounter n, Boolean update)
	{
		node<infoCounter> pa = p;
		infoCounter t = new infoCounter('0', -1);
				
		while (pa != null && (!pa.getInfo().cmp(n)))
				pa = pa.getPtrNext();
		
		if (pa != null)
		{
			if (update)
			{
				n.setT(ZonedDateTime.now(ZoneId.of("Europe/Paris")));
				pa.setInfo(n);
			}
			t = pa.getInfo();
		}
		return t;
	}
	
	public infoCounter search(infoCounter n, Boolean types, Boolean numbers)
	{
		node<infoCounter> pa = p;
		infoCounter t = new infoCounter('0', -1);
	
		while (pa != null 
				&& 
				!(types && pa.getInfo().getType() == n.getType())
				&&
				!(numbers && pa.getInfo().getNum() == n.getNum())
			)
			pa = pa.getPtrNext();
		
		if (pa != null)
			t = pa.getInfo();
		
		return t;
	}
	
}
