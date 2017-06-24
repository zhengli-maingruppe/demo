public class UCFilterStation extends ItemFilterStation{

	public UCFilterStation(String name){
		super(name);
		sendItemKind=CharItem.class;
		recItemKind=CharItem.class;
	}

	public UCFilterStation(){
		super("upper case filter");
		sendItemKind=CharItem.class;
		recItemKind=CharItem.class;
	}
	
	protected boolean passItem(Item item){
		char c = ((CharItem) item).getValue();
		if (Character.isUpperCase(c)){
			return true;
		}
		return false;
	}
}