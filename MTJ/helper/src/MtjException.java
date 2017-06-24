public class MtjException extends Exception{
public String Sender, Listener;
	public MtjException(String Sender, String Listener){
		super(Sender + "" + Listener);
		this.Sender = Sender;
		this.Listener = Listener;
	}
}