
public class CounterState {
	private Boolean active;
	
	public CounterState() {
		active = true;
	}
	
	public CounterState(Boolean state) {
		active = state;
	}
	
	
	public Boolean isActive() {
		return active;
	}
	
	public void setState(Boolean state) {
		active = state;
	}
}
