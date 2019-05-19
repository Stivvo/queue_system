
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
	
	public Boolean rm(infoCounter n)
	{
		if (p.getInfo() == n)
		{
			p = p.getPtrNext();
			return true;
		}
		
		node<infoCounter> pa = p;
		
		while (pa.getPtrNext() != null)
		{
			if (pa.getPtrNext().getInfo().cmp(n))
			{
				pa.setPtrNext(pa.getPtrNext().getPtrNext());
				return true;
			}
			pa = pa.getPtrNext();
		}
		return false;
	}
	
	public Boolean search(infoCounter n)
	{
		node<infoCounter> pa = p;
		
		while (pa != null)
		{
			if (pa.getInfo().cmp(n))
				return true;
			
			pa = pa.getPtrNext();
		}
		return false;
	}
}
