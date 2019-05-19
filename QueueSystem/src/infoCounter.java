import java.time.Instant;
import java.time.ZonedDateTime;

public class infoCounter 
{
	private char type;
	private int num;
	private Instant t;
	
	public infoCounter(char type, int num)
	{
		this.type = type;
		this.num = num;
	}
	
	public infoCounter(char type, int num, ZonedDateTime zdt)
	{
		this.type = type;
		this.num = num;
		this.setT(zdt);
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
				this.num == x.num)
			return true;
		
		return false;
	}
	
	public void setT(ZonedDateTime zdt)
	{
		this.t = zdt.toInstant();
	}
	
	public Instant getT()
	{
		return t;
	}
	
	
}
