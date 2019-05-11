
public class queue {
	private node pFront;
	private node pRear;
	private int counter;
	private int dim;

	public queue() {
		pFront = pRear = null;
		counter = dim = 0;
	}
	
	public boolean isEmpty() {
		if(pFront == null && pRear == null)
			return true;
		return false;
	}
	
	public int getDim() {return dim;}

	public int NEXT() {
		int el = -1;
		node pn = pFront;
		
		if(!isEmpty()) {
			dim--;
			if(pFront == pRear) {
				el = pFront.getId();
				pFront = pRear = null;
			}else {
				pFront = pFront.getPtrNext();
				el = pn.getId();
				pn = null;
			}
		}
		
		return el;
	}

	public void NEWENTRY() {
		node pn = new node(++counter);
		dim++;
		
		if(isEmpty()) {
			pFront = pRear = pn; 
		}else {
			pRear.setPtrNext(pn);
			pRear = pn;
		}
	}
	
	public void scan() {
		node pn = pFront;
		while(pn != null) {
			System.out.print(pn.getId() + " -> ");
			pn = pn.getPtrNext();
		}
		System.out.print("NULL");
		System.out.println();
	}
	
	public node top() {
		return pFront;
	}
}
