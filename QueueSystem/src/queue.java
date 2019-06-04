import java.time.ZonedDateTime;

public class queue
{
	private node<Cliente> pFront;
	private node<Cliente> pRear;
	private int dim;
	private char service;
	private int counter;
	
	public queue(char service) 
	{
		pFront = pRear = null;
		dim = 0;
		counter = 0;
		this.service = service;
	}
	
	public node<Cliente> front() {return pFront;}
	
	public node<Cliente> rear() {return pRear;}
	
	public int getDim() {return dim;}
	
	public char getService() {return service;}
	
	public Boolean isEmpty() 
	{
		if(pFront == null && pRear == null)
			return true;
		return false;
	}

	public Cliente Rear() {
		if (!isEmpty())
			return pRear.getInfo();
		
		return null;
	}
	public String NEXT() 
	{
		if (isEmpty())
			return "---";
		
		dim--;
		String i = pFront.getInfo().getTicket();
		
		if(pFront == pRear)
		{
			pFront = null;
			pRear = null;
		}
		else 
		{
			node<Cliente> pn = pFront;
			System.out.println("pn: " + pn.getInfo().getTicket());
			pFront = pFront.getPtrNext();
			pn = null;
		}
		return i;
	}

	public void NEWENTRY(ZonedDateTime zdt)
	{
		counter++;
		if (counter > 99) {
			counter = 0;
		}
		Cliente clientE = new Cliente(String.valueOf(service) + counter, zdt);
		node<Cliente> pn = new node<Cliente>(clientE, null);
		dim++;
		
		if(isEmpty()) {
			pFront = pRear = pn;
		}else {
			pRear.setPtrNext(pn);
			pRear = pn;
		}
	}
	
	public int getAvg() {
		if (isEmpty())
			return -1;
		
		node<Cliente>pa = pFront;
		long avg = 0;
		int n = 0;
		
		while (pa != null) {
			
			if (pa.getPtrNext() == null)
				avg += pa.info.getT().getEpochSecond() - QueueManagement.getNow().getEpochSecond();
			else
				avg += pa.info.getT().getEpochSecond() - pa.info.getT().getEpochSecond();
			
			n++;
			pa = pa.getPtrNext();
		}
		
		return (int)(avg / n);
	}
	
}