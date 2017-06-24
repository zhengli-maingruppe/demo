public class StringToCharStation extends ItemSplitStation{
	public StringToCharStation(String name, boolean doSignals){
		super(name, doSignals);
		startSignal = new SignalEvent(STRING_START);
		endSignal = new SignalEvent(STRING_END);
		sendItemKind = CharItem.class;
		recItemKind = StringItem.class;
	}
	
	public StringToCharStation(boolean doSignals){
		super("string to char station", doSignals);
		startSignal = new SignalEvent(STRING_START);
		endSignal = new SignalEvent(STRING_END);
		sendItemKind = CharItem.class;
		recItemKind = StringItem.class;
	}
	
	public StringToCharStation(){
		super("string to char station", false);
		startSignal = new SignalEvent(STRING_START);
		endSignal = new SignalEvent(STRING_END);
		sendItemKind = CharItem.class;
		recItemKind = StringItem.class;
	}
	
	protected void processSignal(Signal signal){
	}
	
	protected void processItem(Item item){
		String text = ((StringItem)item).getValue();
		for( int i = 0; i<text.length(); i++){
			CharItem c = new CharItem(text.charAt(i));
			ItemEvent e = new ItemEvent (c);
			sendEvent(e);
		}
	}
			
}