public class ItemEvent extends Event{

private Item item;

	public ItemEvent( Item i){
		this.item = i;
	}

	public Item getItem(){
		return this.item;
	}

	public String toString(){
		return item.toString();
	}
}