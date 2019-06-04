import java.time.Instant;
import java.time.ZonedDateTime;

//ZonedDateTime zdt = ZonedDateTime.now(ZonedDateTime.now(ZoneId.of("Europe/Paris")));

public class Cliente 
{
	private String ticket;
	Instant t;
	
	public Cliente(String ticket, ZonedDateTime zdt)
	{
		this.ticket = ticket;
		this.t = zdt.toInstant();
		
		/*
		 * la zdt è fornita da serverDealer
		 * 
		 * poi si dovrà fare
		 * t.getEpochSecond(); 
		 * 
		 * che ritorna il numero di secondi passati dal 1 gennaio 1970, 
		 * il confronto si fa su 1200 secondi (20 min)
		 */
	}
	
	public String getTicket()
	{
		return ticket;
	}
	
	public Instant getT()
	{
		return t;
	}
	
	public void set(Cliente x) {
		this.ticket = x.ticket;
		this.t = x.t;
	}
	
}
