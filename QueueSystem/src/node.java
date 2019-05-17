public class node 
{
	private Cliente info;
	private node ptr;
	
	public node() 
	{
		ptr = null;		
	}
	
	public node(Cliente info, node ptr) 
	{
		this.info = info;
		this.ptr = ptr;
	}
	
	public void setPtrNext(node ptr) 
	{
		this.ptr = ptr;
	}
	
	public Cliente getInfo() 
	{
		return info;
	}
	
	public node getPtrNext() 
	{
		return ptr;
	}
}