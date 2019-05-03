
public class node {
	private int code;
	private node ptr;
	
	public node() {
		code = 0;
		ptr = null;
	}
	
	public node(int code) {
		if ( code > 3 || code < 0)
			code = 0;
		this.code = code;
	}
	
	public node(int code, node ptr) {
		if ( code > 2 || code < 0)
			code = 0;
		this.code = code;
		
		this.ptr = ptr;
	}
	
	public setInfo(int el) {
		if(el < 0 || el > 2) {
			
		}
	}
}
