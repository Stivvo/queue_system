import java.time.Instant;

//ZonedDateTime zdt = ZonedDateTime.now(ZonedDateTime.now(ZoneId.of("Europe/Paris")));

public class Cliente 
{
	private String ticket;
	Instant t;
	
	public Cliente(String ticket, Instant t1)
	{
		this.ticket = ticket;
		this.t = t1;
		
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
	
}
