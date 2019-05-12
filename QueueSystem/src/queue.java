import java.time.ZonedDateTime;

public class queue 
{
	private node pFront;
	private node pRear;
	private int dim;

	public queue() 
	{
		pFront = pRear = null;
		dim = 0;
	}
	
	public node front() {return pFront;}
	
	public node rear() {return pRear;}
	
	public int getDim() {return dim;}
	
	public Boolean isEmpty() 
	{
		if(pFront == null || pRear == null)
			return true;
		return false;
	}

	public int NEXT() 
	{
		if (isEmpty())
			return -1;
		
		dim--;
		int i = pFront.getInfo().getTicket();
		
		if(pFront == pRear)  {
			pFront = null;
			pRear = null;
		}
		else 
		{
			node pn = pFront;
			pFront = pFront.getPtrNext();
			pn = null;
		}
		return i;
	}

	public void NEWENTRY(int ticket, ZonedDateTime zdt)
	{
		Cliente clientE = new Cliente(ticket, zdt);
		node pn = new node(clientE, null);
		dim++;
		
		if(isEmpty()) {
			pFront = pRear = pn;
		}else {
			pRear.setPtrNext(pn);
			pRear = pn;
		}
	}
}