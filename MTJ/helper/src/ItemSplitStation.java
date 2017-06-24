import java.awt.event.*;
import java.awt.*;

public abstract class ItemSplitStation extends ProcStation{
protected SignalEvent startSignal, endSignal;
private boolean doSignals;

private Dialog dlg;
private TextField txtName;
private ActionListener dHandler = new DialogHandler();
private Checkbox box1;

	protected abstract void processSignal( Signal signal );
	protected abstract void processItem( Item item );
	
	public void handleEvent( Event e){
		if (e instanceof ItemEvent){
			if (doSignals) {sendEvent(startSignal);}
			processItem(((ItemEvent)e).getItem());
			if (doSignals) {sendEvent(endSignal);}
		}
		else{
			processSignal(((SignalEvent)e).getSignal());
			sendEvent(e);
		}
	}
	
	public ItemSplitStation(String name, boolean signals){
		super(name);
		doSignals = signals;
	}
	
	public void EigenschaftenDialog(MtjFrame frame){
		this.setRectColor(Color.blue);
		presenter.update(presenter.getGraphics());	
		dlg = new Dialog(frame, "Eigenschaften", true);
		dlg.setLayout(new GridLayout(5,1));
		Label l1 = new Label("Name:");
		l1.setBackground(Color.lightGray);
		dlg.add(l1);
		txtName = new TextField(this.name);
		dlg.add(txtName);	
		box1 = new Checkbox("Signale senden", false);
		dlg.add(box1);
		box1.setState(doSignals);		
		Button bOK = new Button("OK");
		bOK.addActionListener(dHandler);
		dlg.add(bOK);
		Button bAbbrechen = new Button("Abbrechen");
		bAbbrechen.addActionListener(dHandler);
		dlg.add(bAbbrechen);
		dlg.setSize(new Dimension(180,180));
		dlg.setLocation(frame.getLocationOnScreen());
		dlg.setResizable(false);
		dlg.show();
	}
	
	class DialogHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand()=="OK"){
				String name = txtName.getText();
				if (name.length() > 0){
					ItemSplitStation.this.setString(name);
					ItemSplitStation.this.updateLines();
				}
				doSignals=box1.getState();
				dlg.setVisible(false);
				ItemSplitStation.this.changeToOldColor();
				presenter.update(presenter.getGraphics());
				
			}
			
			if (e.getActionCommand()=="Abbrechen"){
				dlg.setVisible(false);
				ItemSplitStation.this.changeToOldColor();
				presenter.update(presenter.getGraphics());	
			}
		}
	}
}