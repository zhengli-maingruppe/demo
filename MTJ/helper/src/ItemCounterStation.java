public class ItemCounterStation extends TransparentStation{
private int counter = 0;

	public ItemCounterStation(MtjFrame frame, String name){
		super(frame, name);
	}
	
	public ItemCounterStation(MtjFrame frame){
		super(frame, "Item Counter Station");
	}
	
	public void processSignal(Signal signal){
		if (!output.isShowing()){
			output.showWindow();
		}
		if (signal==START){
			System.out.println(this.getName() + " hat Signal " + signal + " erhalten");
			output.println("Signal " + signal + " erhalten");
			counter = 0;
		}
		else{
			if (signal==END){
				System.out.println(this.getName() + " hat Signal " + signal + " erhalten");
				System.out.println("<" + this.getName() + "> hat " + counter + " Items gezaehlt.");
				output.println("Signal " + signal + " erhalten");
				output.println(counter + " Items gezaehlt.");
			}
		}
	}
	
	public int getCounter(){
		return counter;
	}
	
	public void processItem(Item item){
		counter++;
	}
	
	public void EigenschaftenDialog(MtjFrame frame){
		super.EigenschaftenDialog(frame);
		output.setName(name);
	}
}