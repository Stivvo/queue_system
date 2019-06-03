import java.net.Socket;
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
	
	public void setlast(Socket s) {
		
		if (!isEmpty()) {
			node<infoCounter> top = p;
			
			while (top.getPtrNext() != null) {
				top = top.getPtrNext();
			}
			
			top.getInfo().setSock(s);
			
		}
	}
	
	public infoCounter rm(infoCounter n)
	{
		infoCounter t = new infoCounter('0', -1);
		
		if (p.getInfo().eq(n))
		{
			t = p.getInfo();
			p = p.getPtrNext();
		}
		else
		{
			node<infoCounter> pa = p;
			
			while (pa.getPtrNext() != null && 
					!pa.getPtrNext().getInfo().eq(n))
					pa = pa.getPtrNext();
			
			if (pa.getPtrNext() != null)
			{
				t = pa.getPtrNext().getInfo();
				pa.setPtrNext(pa.getPtrNext().getPtrNext());				
			}
		}
		return t;
	}
	
	public infoCounter search() //searches for inactive counters
	{
		node<infoCounter>pa = p;
		infoCounter t = new infoCounter('0', -1);
		Boolean[] nCount = {false, false, false, false};
		int i = 0;
		
		if (!this.isEmpty()) {
		
			long diff = 0;
					
			while (pa != null) {
				
				diff = ZonedDateTime.now(ZoneId.of("Europe/Paris")).toInstant().getEpochSecond() 
				- pa.getInfo().getT().getEpochSecond();
				
				System.out.println("second difference: " + diff);
				
				if (diff > 5) {
					i = ((int)pa.getInfo().getType()) - 65;
					
					if (nCount[i]) {
						return pa.getInfo();
					} else {
						nCount[i] = true;
					}
				}
				else if (diff > 9999) {//se l'attesa la differenza di tempo esce dallo spazio long
					infoCounter temp = pa.getInfo();
					temp.setT(ZonedDateTime.now(ZoneId.of("Europe/Paris")));
					pa.setInfo(temp);
				}
				pa = pa.getPtrNext();
				
				if (pa != null)
					diff = ZonedDateTime.now(ZoneId.of("Europe/Paris")).toInstant().getEpochSecond()
						- pa.getInfo().getT().getEpochSecond();
			}
		}
		return t;
	}
	
	public infoCounter search(infoCounter n, Boolean update) //searchs for both numbers and types
	{
		node<infoCounter> pa = p;
		infoCounter t = new infoCounter('0', -1);
		
		while (pa != null && (!pa.getInfo().eq(n)))
				pa = pa.getPtrNext();
		
		if (pa != null) {
			if (update) {
				n.setT(ZonedDateTime.now(ZoneId.of("Europe/Paris")));
				pa.setInfo(n);
			}
			t = pa.getInfo();
		}
		return t;
	}
	
	public infoCounter search(int n) //searches for numbers
	{
		node<infoCounter> pa = p;
		infoCounter t = new infoCounter('0', -1);
	
		while (pa != null &&
				pa.getInfo().getNum() != n)
			pa = pa.getPtrNext();
		
		if (pa != null)
			t = pa.getInfo();
		
		return t;
	}
	
	public infoCounter search(char c) //searches for types
	{
		node<infoCounter> pa = p;
		infoCounter t = new infoCounter('0', -1);
	
		while (pa != null &&
				pa.getInfo().getType() != c)
			pa = pa.getPtrNext();
		
		if (pa != null)
			t = pa.getInfo();
		
		return t;
	}
	
	public void stampa()
	{
		node<infoCounter> pa = p;
		while (pa != null)
		{
			System.out.println(pa.getInfo().getType() + ", " + pa.getInfo().getNum());
			pa = pa.getPtrNext();
		}
	}
	
}
