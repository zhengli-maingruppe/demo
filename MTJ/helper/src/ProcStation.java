import java.util.*;
import java.awt.*;

public abstract class ProcStation extends Station implements EventListener, Runnable{

private Vector eventSenders = new Vector();
protected Class recItemKind;
private Vector deliveredEvents = new Vector();

	public ProcStation(String s){
		super(s);
		Thread th = new Thread(this);
		th.start();
	}

	public void draw(Graphics g){
		super.draw(g);
		if (recItemKind==StringItem.class){
			g.drawString("S", x, y-2);
		}
		if (recItemKind==CharItem.class){
			g.drawString("C", x, y-2);
		}
		if (recItemKind==null){
			g.drawString("?", x, y-2);
		}
	}
	
	public void setSender( EventSender s ){
		eventSenders.addElement(s);
		System.out.println("<" + this.getName() + "> hat <" + ((Station)s).getName() + "> als Sender erhalten");
	}
	
	public void releaseFromSenders(){
		for (int i=eventSenders.size()-1; i>=0; i--){
			EventSender S = (EventSender) eventSenders.elementAt(i);
			S.detachListener(this);
			eventSenders.removeElement(S);
			System.out.println(this.getName() + " hat Verbindung zu "+((Station)S).getName() + " geloest");
		}
	}
		
	public void detachSender( EventSender s ){
		eventSenders.removeElement(s);		
	}
	
	
	public Class recItemKind(){
		return this.recItemKind;
	}
	
	public Class sendItemKind(){
		return this.sendItemKind;
	}
	
	public void setItemKind(Class itemKind){
		super.setItemKind(itemKind);
		recItemKind=itemKind;
	}
	
	public void run(){
		while (true){
			Event e = nextEvent();
			handleEvent(e);
		}
	}	
	
	public synchronized void deliverEvent(Event e){
		deliveredEvents.addElement(e);
		this.notify();
	}
	
	private synchronized Event nextEvent(){
		if (deliveredEvents.size()==0){
			try{
				this.wait();
			}
			catch (InterruptedException se){}
		}
		Event e = (Event) deliveredEvents.elementAt(0);
		deliveredEvents.removeElement(e);
		return e;
	}
	
	public abstract void handleEvent(Event e);

}