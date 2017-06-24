public class Signal{
protected String name;

	public static interface Values{
		public static final Signal
			START=new Signal("START"),
			END=new Signal("END"),
			FILE_START=new Signal("FILE_START"),
			FILE_END=new Signal("FILE_END"),
			WORDGROUP_START=new Signal("WORDGROUP_START"),
			WORDGROUP_END=new Signal("WORDGROUP_END"),
			STRING_START=new Signal("STRING_START"),
			STRING_END=new Signal("STRING_END");
	}
	
	public Signal(String name){
		this.name=name;
	}
	
	public String getSignal(){
		return this.name;
	}
	
	public String toString(){
		return this.name;
	}
}