
public class infoCounter 
{
	private char type;
	private int num;
	
	public infoCounter(char type, int num)
	{
		this.type = type;
		this.num = num;
	}
	
	public char getType() {return type;}
	
	public int getNum() {return num;}
	
	public String print()
	{
		return (type + " " + num);
	}
	
	public Boolean cmp(infoCounter x)
	{
		if (this.type == x.type && 
				this.num == x.getNum())
			return true;
		
		return false;
	}
	
	
}
