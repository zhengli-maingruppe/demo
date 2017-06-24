public class RectRegion{
protected String name;

	public static interface Values{
		public static final RectRegion
			LEFT=new RectRegion("LINKS"),
			CENTER=new RectRegion("MITTE"),
			RIGHT=new RectRegion("RECHTS");
	}
	
	public RectRegion(String name){
		this.name=name;
	}
	
	public String toString(){
		return this.name;
	}
}