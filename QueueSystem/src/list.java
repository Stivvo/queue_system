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
			
			t = pa.getPtrNext().getInfo();
			pa.setPtrNext(pa.getPtrNext().getPtrNext());
		}
		return t;
	}
	
	public Boolean search(infoCounter n, Boolean update, Boolean onlyUnactive)
	{
		node<infoCounter> pa = p;
		
		while (pa != null)
		{
			if (pa.getInfo().cmp(n))
			{
				//aggiustare il valore < con una differenza
				if (onlyUnactive && pa.getInfo().getT().getEpochSecond() <
						ZonedDateTime.now(ZoneId.of("Europe/Paris")).toInstant().getEpochSecond())
				{
					if (update)
					{
						n.setT(ZonedDateTime.now(ZoneId.of("Europe/Paris")));
						pa.setInfo(n);
					}					
				}
				return true;
			}
			
			pa = pa.getPtrNext();
		}
		return false;
	}	
	
}
