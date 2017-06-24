public class StringItem extends Item{

private String c;

	public StringItem( String x ){
		this.c = x;
	}
	
	public String getValue(){
		return c;
	}
	
	public String toString(){
		return c;
	}
}