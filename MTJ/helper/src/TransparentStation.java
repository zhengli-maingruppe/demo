public abstract class TransparentStation extends ProcStation{
protected OutputWindow output;

	protected abstract void processSignal( Signal signal );
	protected abstract void processItem( Item item );
	
	public void handleEvent( Event e){
		if (e instanceof ItemEvent){
			processItem(((ItemEvent)e).getItem());
		}
		else{
			processSignal(((SignalEvent)e).getSignal());
		}
		sendEvent(e);
	}
	
	public TransparentStation(MtjFrame frame, String name){
		super(name);
		output = new OutputWindow(name, frame, this);
	}
}