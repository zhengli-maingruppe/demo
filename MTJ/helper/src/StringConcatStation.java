import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class StringConcatStation extends ItemCollectStation{
private int maxLength;
private String separator="";
private StringBuffer collection = new StringBuffer("");

private Dialog dlg;
private TextField txtName, txtSep, txtAnz;
private ActionListener dHandler = new DialogHandler();
private Checkbox box1, box2 , box3, box4;
	
	public StringConcatStation(String name, int maxLength, String separator, Signal[] passOnSignals){
		super(name, new Signal[] {START, END}, passOnSignals);
		this.maxLength = maxLength;
		this.separator = separator;
		sendItemKind=StringItem.class;
		recItemKind=StringItem.class;
	}
	
	public StringConcatStation(int maxLength, String separator, Signal[] passOnSignals){
		super("string concat station", new Signal[] {START, END}, passOnSignals);
		this.maxLength = maxLength;
		this.separator = separator;
		sendItemKind=StringItem.class;
		recItemKind=StringItem.class;
	}
	
	public StringConcatStation(int maxLength){
		super("string concat station", new Signal[] {START, END}, new Signal[] {FILE_START, FILE_END});
		this.maxLength = maxLength;
		sendItemKind=StringItem.class;
		recItemKind=StringItem.class;
	}
	
	protected void processSignal( Signal signal ){
	}
	
	protected boolean doFlush( Item item){
		if ((collection.length() + (((StringItem)item).getValue()).length() + separator.length()) > maxLength){
			return true;
		}
		return false;
	}
	
	protected void collectItem( Item item){
		if (collection.length() > 0){
			collection.append(separator);
		}
		collection.append(((StringItem)item).getValue());
	}
	
	protected void flushCollectedItems(){
		StringItem s = new StringItem(collection.toString());
		ItemEvent e = new ItemEvent (s);
		sendEvent(e);
		collection.setLength(0);
	}
	
	public void EigenschaftenDialog(MtjFrame frame){
		this.setRectColor(Color.blue);
		presenter.update(presenter.getGraphics());
		
		dlg = new Dialog(frame, "Eigenschaften", true);
		dlg.setLayout(new GridLayout(12,1));
		Label l1 = new Label("Name:");
		l1.setBackground(Color.lightGray);
		dlg.add(l1);
		txtName = new TextField(this.name);
		dlg.add(txtName);
		Label l2=new Label("Maximale Laenge:");
		l2.setBackground(Color.lightGray);
		dlg.add(l2);
		txtAnz = new TextField("" + maxLength);
		dlg.add(txtAnz);
		Label l3=new Label("Separator:");
		l3.setBackground(Color.lightGray);
		dlg.add(l3);
		txtSep = new TextField(separator);
		dlg.add(txtSep);
		
		box1 = new Checkbox("START / END", false);
		box2 = new Checkbox("FILE_START / FILE_END", false);
		box3 = new Checkbox("WORDGROUP_START / WORDGROUP_END", false);
		box4 = new Checkbox("STRING_START / STRING_END", false);
		dlg.add(box1);
		dlg.add(box2);
		dlg.add(box3);
		dlg.add(box4);
		if (passOnSignals.contains(START)){
			box1.setState(true);
		}
		if (passOnSignals.contains(FILE_START)){
			box2.setState(true);
		}
		if (passOnSignals.contains(WORDGROUP_START)){
			box3.setState(true);
		}
		if (passOnSignals.contains(STRING_START)){
			box4.setState(true);
		}
		
		Button bOK = new Button("OK");
		bOK.addActionListener(dHandler);
		dlg.add(bOK);
		Button bAbbrechen = new Button("Abbrechen");
		bAbbrechen.addActionListener(dHandler);
		dlg.add(bAbbrechen);
		dlg.setSize(new Dimension(300,300));
		dlg.setLocation(frame.getLocationOnScreen());
		dlg.setResizable(false);
		dlg.show();
	}
	
	class DialogHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand()=="OK"){
				String name = txtName.getText();
				if (name.length() > 0){
					StringConcatStation.this.setString(name);
					StringConcatStation.this.updateLines();
				}
				int laenge=0;
				try{
					laenge = Integer.parseInt(txtAnz.getText()) ;
				}
				catch (NumberFormatException exc){
					System.out.println("Idiot!");
				}
				separator = txtSep.getText();
				Vector temp = new Vector();
				if (box1.getState()){
					temp.addElement(START);
					temp.addElement(END);
				}
				if (box2.getState()){
					temp.addElement(FILE_START);
					temp.addElement(FILE_END);
				}
				if (box3.getState()){
					temp.addElement(WORDGROUP_START);
					temp.addElement(WORDGROUP_END);
				}
				if (box4.getState()){
					temp.addElement(STRING_START);
					temp.addElement(STRING_END);
				}
				passOnSignals=temp;
				dlg.setVisible(false);
				StringConcatStation.this.changeToOldColor();
				presenter.update(presenter.getGraphics());
				
			}
			
			if (e.getActionCommand()=="Abbrechen"){
				dlg.setVisible(false);
				StringConcatStation.this.changeToOldColor();
				presenter.update(presenter.getGraphics());	
			}
		}
	}
}