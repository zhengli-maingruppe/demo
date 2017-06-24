import java.util.*;
import java.awt.*;

public abstract class GenStation extends Station implements EventGenerator{

protected Vector events = new Vector();

	public GenStation(String s){
		super(s);
	}

	public void generateEvents(){
		for (int j=0; j<events.size(); j++){
			System.out.println();
			Event e = (Event) events.elementAt(j);
			System.out.println("Erzeuge Ereignis...");
			for (int i=0; i<eventListeners.size(); i++){
				EventListener L = (EventListener) eventListeners.elementAt(i);
				((ConnectionLine) lineOut.elementAt(i)).setLineColor(Color.green);
				if (e instanceof SignalEvent){
					( (ConnectionLine) lineOut.elementAt(i)).setLineColor(Color.red);
				}
				System.out.println("<" + this.getName() + "> sendet Ereignis <" + e + "> an <" + ((Station)L).getName() + ">");
				((ConnectionLine) lineOut.elementAt(i)).draw(presenter.getGraphics());
				//presenter.update(presenter.getGraphics());
				try{
					Thread.currentThread().sleep(250);
				}
				catch (InterruptedException se){}
				( (ConnectionLine) lineOut.elementAt(i)).setLineColor(Color.black);
				((ConnectionLine) lineOut.elementAt(i)).draw(presenter.getGraphics());
				//presenter.update(presenter.getGraphics());
				try{
					Thread.currentThread().sleep(250);
				}
				catch (InterruptedException se){}
				L.deliverEvent( e );
				/*ProcStation L = (ProcStation) eventListeners.elementAt(j);
				System.out.println("<" + this.getName() + "> sendet Ereignis <" + e + "> an <" + ((Station)L).getName() + ">");
				L.deliverEvent(e);*/
			}
		}
	}
}