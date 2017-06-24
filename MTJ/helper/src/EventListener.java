public interface EventListener{
	public void setSender( EventSender s );
	public void releaseFromSenders();
	public void detachSender( EventSender s );
	public void deliverEvent( Event e );
	
	public Class recItemKind();
}