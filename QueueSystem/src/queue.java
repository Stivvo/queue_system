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
	
	public node top() {return pFront;}
	
	public int getDim() {return dim;}
	
	public boolean isEmpty() 
	{
		if(pFront == null && pRear == null)
			return true;
		return false;
	}

	public int NEXT() 
	{
		if (isEmpty())
			return -1;
		
		dim--;
		int i = pFront.getInfo().getTicket();
		
		if(pFront == pRear) 
			return pFront.getInfo().getTicket();
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