public class StringToWordStation extends ItemSplitStation{
	
	public StringToWordStation(String name, boolean doSignals){
		super(name, doSignals);
		startSignal = new SignalEvent(WORDGROUP_START);
		endSignal = new SignalEvent(WORDGROUP_END);
		sendItemKind = StringItem.class;
		recItemKind = StringItem.class;
	}
	
	public StringToWordStation(boolean doSignals){
		super("string to word station", doSignals);
		startSignal = new SignalEvent(WORDGROUP_START);
		endSignal = new SignalEvent(WORDGROUP_END);
		sendItemKind = StringItem.class;
		recItemKind = StringItem.class;
	}
	
	public StringToWordStation(){
		super("string to word station", false);
		startSignal = new SignalEvent(WORDGROUP_START);
		endSignal = new SignalEvent(WORDGROUP_END);
		sendItemKind = StringItem.class;
		recItemKind = StringItem.class;
	}
	
	protected void processSignal(Signal signal){
	}
	
	protected void processItem(Item item){
		String text = ((StringItem)item).getValue();
		int beginIndex=0;
		for( int i = 0; i<text.length(); i++){
			if (text.charAt(i)==' ' || text.charAt(i)=='	'){
				if (i==beginIndex){
					beginIndex++;
				}
				else{
					if (i==text.length()-1){
						i++;
					}
					StringItem s = new StringItem(text.substring(beginIndex, i));
					ItemEvent e = new ItemEvent (s);
					sendEvent(e);
					beginIndex=i+1;
				}
			}
			else{
				if (i==text.length()-1){
					StringItem s = new StringItem(text.substring(beginIndex, ++i));
					ItemEvent e = new ItemEvent (s);
					sendEvent(e);
				}
			}
		}
	}
}