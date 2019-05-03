
public class queue {
	private node pFront;
	private node pRear;

	public queue() {
		pFront = pRear = null;
	}
	
	public queue(node ptr) {
		pFront = pRear = ptr;
	}
	
	public boolean isEmpty() {
		if(pFront == null && pRear == null)
			return true;
		return false;
	}

	public int pop() {
		int el = -1;
		node pn = pFront;
		
		if(!isEmpty()) {
			if(pFront == pRear) {
				el = pFront.getInfo();
				pFront = pRear = null;
			}else {
				pFront = pFront.getPtrNext();
				el = pn.getInfo();
				pn = null;
			}
		}
		
		return el;
	}

	public void push(int el) {
		node pn = new node(el);
		
		if(isEmpty()) {
			pFront = pRear = pn;
		}else {
			pRear.setPtrNext(pn);
			pRear = pn;
		}
	}
}
