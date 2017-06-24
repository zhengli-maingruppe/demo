import java.util.*;
public abstract class ItemCollectStation extends ProcStation{
protected Vector passOnSignals = new Vector();

	protected abstract void processSignal( Signal signal );
	protected abstract boolean doFlush( Item item);
	protected abstract void collectItem( Item item);
	protected abstract void flushCollectedItems();
	
	public void handleEvent( Event e){
		if (e instanceof ItemEvent){
			if (doFlush(((ItemEvent)e).getItem())){
				flushCollectedItems();
			}
			collectItem(((ItemEvent)e).getItem());
		}
		else{
			processSignal(((SignalEvent)e).getSignal());
			if (passOnSignals.contains(((SignalEvent)e).getSignal())){
				flushCollectedItems();
				sendEvent(e);
			}
		}
	}
	
	public ItemCollectStation(String name, Signal[] signals1, Signal[] signals2){
		super(name);
		for(int i = 0; i<signals1.length; i++){
			passOnSignals.addElement(signals1[i]);
		}
		for(int i = 0; i<signals2.length; i++){
			passOnSignals.addElement(signals2[i]);
		}
	}
}