package mtj.level2;

public interface EventListener {

	public void setSender(EventSender sender);
	
	public void detachSender(EventSender sender);
	
	public void releaseFromSenders();
	
	public void handleEvent(Event e);
}
