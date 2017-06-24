package mtj.level1;

public class ProcStation {
	private String name;
	private GenStation eventSenderGen = null;
	private ProcStation eventSenderProc = null;

	private ProcStation eventListener = null;

	public ProcStation(String name){
		this.name = name;
	}
	
	public ProcStation(){
		this.name = "Arbeitsstation";
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public void addEventListener(ProcStation listener) {
		this.eventListener = listener;
	}

	public void setSender(GenStation sender) {
		this.eventSenderGen = sender;
	}

	public void setSender(ProcStation sender) {
		this.eventSenderProc = sender;
	}

	public void detachSender() {
		
		eventSenderGen = null;

		
		eventSenderProc = null;
	}

	public void detachListener() {
	
		eventListener = null;
	}
	
	public void releaseFromSender(){
		
	}
	
	public void releaseFromListener(){
		if( eventSenderGen != null)
			eventSenderGen.detachListener();
		
		if( eventSenderProc != null)
			eventSenderProc.detachListener();
	}
	
	public void sendEvent(Event e){
		if( eventListener != null)
			eventListener.handleEvent(e);
		
		
	}
	
	public void handleEvent(Event e){
		System.out.println("Station " + getName() + ": Ereignis");
		if( eventListener != null)
			eventListener.handleEvent(e);
		
	}
}
