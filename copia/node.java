
public class node {
	private int id;
	private node ptr;
	
	public node() {
		ptr = null;
		id = 0;
	}

	public node(int el) {
		id = el;
	}
	
	public node(int el, node ptr) {
		if(el < 0)
			el = 0;
		id = el;
		
		this.ptr = ptr;
	}
	
	public void setInfo(int el) {
		id = el;
	}
	
	public void setPtrNext(node ptr) {
		this.ptr = ptr;
	}
	
	public int getId() {
		return id;
	}
	
	public node getPtrNext() {
		return ptr;
	}
}
