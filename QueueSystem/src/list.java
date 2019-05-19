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
					pa.getPtrNext().getInfo().cmp(n))
					pa = pa.getPtrNext();
			
			if (pa.getPtrNext() != null)
			{
				t = pa.getPtrNext().getInfo();
				pa.setPtrNext(pa.getPtrNext().getPtrNext());				
			}
		}
		return t;
	}
	
	public infoCounter search(infoCounter n, Boolean update, Boolean onlyUnactive)
	{
		node<infoCounter> pa = p;
		infoCounter t = new infoCounter('0', -1);
		//aggiustare il valore < con una differenza
				
		while (pa != null && pa.getInfo().cmp(n))
		{
			if (!(
				onlyUnactive &&
				pa.getInfo().getT().getEpochSecond() <
				ZonedDateTime.now(ZoneId.of("Europe/Paris")).toInstant().getEpochSecond()
			)) 
				pa = pa.getPtrNext();
		}
		
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
	
}
