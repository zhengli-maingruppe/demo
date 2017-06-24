public interface EventSender{
	public void addEventListener( EventListener listener ) throws MtjException;
	public void releaseFromListeners();
	public void detachListener( EventListener listener );
	public void sendEvent( Event e );
	
	public Class sendItemKind();
}