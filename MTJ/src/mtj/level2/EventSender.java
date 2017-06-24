package mtj.level2;

public interface EventSender {
	public void addEventListener(EventListener listener);
	public void releaseFromListeners();
	public void detachListener(EventListener listener);
	public void sendEvent(Event e);
}
