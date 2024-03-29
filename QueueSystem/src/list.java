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
		node<infoCounter> n = new node<infoCounter>(x, null);
		n.setPtrNext(p);
		p = n;
	}
	
	public infoCounter rm(infoCounter n)
	{
		infoCounter t = new infoCounter('0', -1);
		
		if (p.getInfo().eq(n))
		{
			t = p.getInfo();
			p = p.getPtrNext();
		} else {
			node<infoCounter> pa = p;
			
			while (pa.getPtrNext() != null && 
					!pa.getPtrNext().getInfo().eq(n))
					pa = pa.getPtrNext();
			
			if (pa.getPtrNext() != null) {
				t = pa.getPtrNext().getInfo();
				pa.setPtrNext(pa.getPtrNext().getPtrNext());
			}
		}
		return t;
	}
	
	public infoCounter search() //searches for inactive counters
	{
		infoCounter t = new infoCounter('0', -1);
		
		if (!this.isEmpty()) {
			node<infoCounter>pa = p;
			Boolean[] nCount = {false, false, false, false};
			int i = 0;
			long diff = 0;
					
			while (pa != null && pa.getInfo() != null) {
				
				diff = QueueManagement.getNow().getEpochSecond() 
						- pa.getInfo().getT().getEpochSecond();
				
				if (diff > 20) {
					i = ((int)pa.getInfo().getType()) - 65;
					
					if (nCount[i] == true) {
						return pa.getInfo();

					}
					else {
						nCount[i] = true;
					}
						
				} else
				if (diff > 9999) {//se l'attesa la differenza di tempo esce dallo spazio long
					infoCounter temp = pa.getInfo();
					temp.setT(QueueManagement.getNow());
					pa.setInfo(temp);
				}
				pa = pa.getPtrNext();
			}
			
			for (int j = 0; j < 3; j++) {
				if (nCount[j] && nCount[3]) {
					t = search((char)('A'+j));
					return t;
				}
			}
		} //end if !this.isEmpty()
		
		
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
				n.setT(QueueManagement.getNow());
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
			System.out.println(pa.getInfo().print());
			pa = pa.getPtrNext();
		}
	}
	
	public void move(infoCounter t) {
		node<infoCounter> pa = p; 
		node<infoCounter> pc = null;
		node<infoCounter> pb = null;
		if (pa != null) {
			if (pa.getInfo().eq(t)) {
				p = p.getPtrNext();
				
			} else {
				pb = pa;
				while (pb != null && pb.getInfo().eq(t)) {
					pb = pb.getPtrNext();
					pa = pa.getPtrNext();
				}
				
				if (pb != null) {
					pa.setPtrNext(pb.getPtrNext());
					pa = pb;
				}
			}
			
			pc = p;
			pa.setPtrNext(null);
			while (pc.getPtrNext() != null) {
				pc = pc.getPtrNext();
			}
			pc.setPtrNext(pa);
		}
		
		
	}
}
