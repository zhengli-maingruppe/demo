public class LCFilterStation extends ItemFilterStation{

	public LCFilterStation(String name){
		super(name);
		sendItemKind=CharItem.class;
		recItemKind=CharItem.class;
	}

	public LCFilterStation(){
		super("lower case filter");
		sendItemKind=CharItem.class;
		recItemKind=CharItem.class;
	}
	
	protected boolean passItem(Item item){
		char c = ((CharItem) item).getValue();
		if (Character.isLowerCase(c)){
			return true;
		}
		return false;
	}
}