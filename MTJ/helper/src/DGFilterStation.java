public class DGFilterStation extends ItemFilterStation{

	public DGFilterStation(String name){
		super(name);
		sendItemKind=CharItem.class;
		recItemKind=CharItem.class;
	}

	public DGFilterStation(){
		super("digit filter");
		sendItemKind=CharItem.class;
		recItemKind=CharItem.class;
	}
	
	protected boolean passItem(Item item){
		char c = ((CharItem) item).getValue();
		if (Character.isDigit(c)){
			return true;
		}
		return false;
	}
}