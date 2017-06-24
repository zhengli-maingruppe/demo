public class CharItem extends Item{

private char c;

	public CharItem( char x ){
		this.c = x;
	}
	
	public char getValue(){
		return c;
	}
	
	public String toString(){
		return (new Character(c)).toString();
	}
}