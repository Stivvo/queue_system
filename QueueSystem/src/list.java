
public class list 
{
	private node<Integer> p;
	
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
	
	public void in (int x)
	{
		node<Integer> n = new node<Integer>(x, p);
		p = n;
	}
	
	public Boolean rm(int n)
	{
		if (p.getInfo() == n)
		{
			p = p.getPtrNext();
			return true;
		}
		
		node<Integer> pa = p;
		
		while (pa.getPtrNext() != null)
		{
			if (pa.getPtrNext().getInfo() == n)
			{
				pa.setPtrNext(pa.getPtrNext().getPtrNext());
				return true;
			}
			pa = pa.getPtrNext();
		}
		return false;
	}
	
	public Boolean search(Integer n)
	{
		node<Integer> pa = p;
		
		while (pa != null)
		{
			if (pa.getInfo() == n)
				return true;
			
			pa = pa.getPtrNext();
		}
		return false;
	}
}
