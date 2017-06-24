package mtj.level2;

import java.util.Vector;

public abstract class Station implements EventSender{
	private String name;
	private Vector<EventListener> eventListeners = new Vector<EventListener> ();
	
	public Station(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void addEventListener(EventListener listener){
		eventListeners.add(listener);
		listener.setSender(this);
	}
	
	public void releaseFromListeners(){
		for( EventListener listener: eventListeners){
			listener.detachSender(this);
			detachListener(listener);
			
		}
	}
	public void detachListener(EventListener listener){
		eventListeners.remove(listener);
		System.out.println(this.getName() + " has removed the listener '"+((Station)listener).getName() + "'");
	}
	
	public void sendEvent(Event e){
		for(EventListener listener: eventListeners)
			listener.handleEvent(e);
	}
}

