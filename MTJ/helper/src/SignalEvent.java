public class SignalEvent extends Event{
private Signal signal;

	public SignalEvent( Signal i){
		this.signal = i;
	}
	
	public Signal getSignal(){
		return this.signal;
	}
	
	public String toString(){
		return signal.toString();
	}
}