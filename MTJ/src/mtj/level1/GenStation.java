package mtj.level1;

public class GenStation {
	private String name;
	
	private ProcStation eventListener;
	private Event e;
	
	public GenStation(String name){
		this.name = name;
	}
	
	public GenStation(){
		this.name = "Generatestation";
	}
	
	public String getName(){
		return this.name;
	}
	
	public void addEventListener(ProcStation listener){
		this.eventListener = listener;
	}
	
	
	public void detachListener(){
		eventListener = null;
	}

	
	public void releaseFromSender(){
		if ( eventListener != null)
			eventListener.detachListener();
		eventListener = null;
	}
	
	public void generateEvent(){
		e = new Event();
		sendEvent(e);
	}
	
	public void sendEvent(Event e){
		if( eventListener != null)
			eventListener.handleEvent(e);
		
	}
}
