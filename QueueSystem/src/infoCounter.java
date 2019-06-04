import java.net.Socket;
import java.time.Instant;

public class infoCounter 
{
	private char type;
	private int num;
	private Instant t;
	private Socket s;
	
	public infoCounter(char type, int num)
	{ //mustn't be used to store infoCounter in a list
		this.type = type;
		this.num = num;
	}
	
	public infoCounter(char type, int num, Instant t, Socket sock)
	{
		this.type = type;
		this.num = num;
		this.t = t;
		s = sock;
	}
	
	public char getType() {return type;}
	
	public int getNum() {return num;}
	
	
	public void setSock(Socket sock) {
		s = sock;
	}
	public String print()
	{
		return (type + " " + num);
	}
	
	public Boolean eq(infoCounter x)
	{
		if (this.type == x.type && 
				this.num == x.num)
			return true;
		
		return false;
	}
	
	public void set(infoCounter x) {
		this.type = x.type;
		this.num = x.num;
		this.t = x.t;
		this.s = x.s;
	}
	
	public void setT(Instant t)
	{
		this.t = t;
	}
	
	public Instant getT()
	{
		return t;
	}
	
	public Socket getSocket() {
		return s;
	}
	
}
