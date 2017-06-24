public abstract class ItemFilterStation extends ProcStation{
	
	public ItemFilterStation(String name){
		super(name);
	}
	
	protected abstract boolean passItem(Item item);
	
	public void handleEvent(Event e){
		if (e instanceof SignalEvent){
			sendEvent(e);
		}
		else{
			if (passItem( ((ItemEvent)e).getItem() )){
				sendEvent(e);
			}
		}
	}
	
}