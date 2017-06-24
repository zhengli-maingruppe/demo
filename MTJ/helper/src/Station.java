import java.util.*;
import java.awt.*;
import java.awt.event.*;

public abstract class Station extends MtjRectangle implements EventSender, Signal.Values{

//Variablen	
protected Vector eventListeners = new Vector();
protected Class sendItemKind;

private Dialog dlg;
private TextField txtName;
private ActionListener dHandler = new DialogHandler();
	
	//Konstruktor
	public Station(String s){
		super(s);
		System.out.println("Station <" + this.getName() + "> erzeugt");
	}
	
	//Methoden
	//Uebergibt den Namen der Station
	String getName(){
		return this.name;
	}
	
	public void draw(Graphics g){
		super.draw(g);
		if (sendItemKind==StringItem.class){
			g.drawString("S", x+width-7, y-2);
		}
		if (sendItemKind==CharItem.class){
			g.drawString("C", x+width-7, y-2);
		}
		if (sendItemKind==null){
			g.drawString("?", x+width-6, y-2);
		}
	}
	
	//Fuegt einen Nachfolger ein
	public void addEventListener( EventListener listener ) throws MtjException {
		Class listenerKind = listener.recItemKind();
		if (listenerKind==null && sendItemKind==null){
			String meldung1="Sender: " + this.name;
			String meldung2="Listener: " + ((Station)listener).getName();
			throw new MtjException ( meldung1, meldung2 );
		}
		else{
			if (listenerKind==null && sendItemKind!=null){
				((Station)listener).setItemKind(sendItemKind);
				eventListeners.addElement(listener);
				System.out.println("<" +this.getName() + "> fuegt <" + ((Station)listener).getName() + "> als Nachfolger hinzu");
				listener.setSender(this);
			}
			else{
				if (listenerKind!=null && sendItemKind==null){
					this.setItemKind(listenerKind);
					eventListeners.addElement(listener);
					System.out.println("<" +this.getName() + "> fuegt <" + ((Station)listener).getName() + "> als Nachfolger hinzu");
					listener.setSender(this);
				}
				else{
					if (listenerKind!=sendItemKind){
						String meldung1="Sender: " + this.name;
						String meldung2="Listener: " + ((Station)listener).getName();
						throw new MtjException ( meldung1, meldung2 );
					}
					else{
						eventListeners.addElement(listener);
						System.out.println("<" +this.getName() + "> fuegt <" + ((Station)listener).getName() + "> als Nachfolger hinzu");
						listener.setSender(this);
					}
				}
			}
		}
	}
	
	//Loest Verbindung zu allen Nachfolgern
	public void releaseFromListeners(){
		for (int i=eventListeners.size()-1; i>=0; i--){
			EventListener L = (EventListener) eventListeners.elementAt(i);
			L.detachSender(this);
			eventListeners.removeElement(L);
			System.out.println(this.getName() + " hat Verbindung zu "+((Station)L).getName() + " geloest");
		}
	}
	
	//Nachfolger loest selbst die Verbindung
	public void detachListener( EventListener listener ){
		eventListeners.removeElement(listener);
	}
	
	//Sendet ein Ereignis
	public void sendEvent( Event e ){
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
		}
	}
	
	public Class sendItemKind(){
		return this.sendItemKind;
	}
	
	public void setItemKind(Class itemKind){
		sendItemKind=itemKind;
	}
	
	public String toString(){
		return this.name;
	}
	
	public void EigenschaftenDialog(MtjFrame frame){
		this.setRectColor(Color.blue);
		presenter.update(presenter.getGraphics());	
		dlg = new Dialog(frame, "Eigenschaften", true);
		dlg.setLayout(new GridLayout(4,1));
		Label l1 = new Label("Name:");
		l1.setBackground(Color.lightGray);
		dlg.add(l1);
		txtName = new TextField(this.name);
		dlg.add(txtName);	
		
		Button bOK = new Button("OK");
		bOK.addActionListener(dHandler);
		dlg.add(bOK);
		Button bAbbrechen = new Button("Abbrechen");
		bAbbrechen.addActionListener(dHandler);
		dlg.add(bAbbrechen);
		dlg.setSize(new Dimension(180,120));
		dlg.setLocation(frame.getLocationOnScreen());
		dlg.setResizable(false);
		dlg.show();
	}
	
	class DialogHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand()=="OK"){
				String name = txtName.getText();
				if (name.length() > 0){
					Station.this.setString(name);
					Station.this.updateLines();
				}
				dlg.setVisible(false);
				Station.this.changeToOldColor();
				presenter.update(presenter.getGraphics());
				
			}
			
			if (e.getActionCommand()=="Abbrechen"){
				dlg.setVisible(false);
				Station.this.changeToOldColor();
				presenter.update(presenter.getGraphics());	
			}
		}
	}
}