public class node <T>
{
	private T info;
	private node<T> ptr;
	
	public node() 
	{
		ptr = null;
	}
	
	public node(T info, node<T> ptr) 
	{
		this.info = info;
		this.ptr = ptr;
	}
	
	public void setPtrNext(node<T> ptr) 
	{
		this.ptr = ptr;
	}
	
	public T getInfo() 
	{
		return info;
	}
	
	public void setInfo(T info)
	{
		this.info = info;
	}
	
	public node<T> getPtrNext() 
	{
		return ptr;
	}
}