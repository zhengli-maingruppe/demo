import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class StringGenerator extends GenStation{
private Dialog dlg, stringAnzeige;
private TextField txtName, txtZeichen;
private TextArea txtAnzeige;
private ActionListener dHandler = new DialogHandler();
private Vector folge = new Vector();
private boolean textWindow=false;

	public StringGenerator (String name, String[] liste){
		super(name);
		sendItemKind=StringItem.class;
		events.addElement(new SignalEvent(START));
		for (int i=0; i<liste.length; i++){
			StringItem c = new StringItem(liste[i]);
			ItemEvent e = new ItemEvent (c);
			events.addElement(e);
		}
		events.addElement(new SignalEvent(END));
		System.out.println("<" + this.getName() + "> hat Ereignisfolge erzeugt");
	}
	
	public StringGenerator (String[] liste){
		this("string generator station", liste);
	}
	
	public void EigenschaftenDialog(MtjFrame frame){
		this.setRectColor(Color.blue);
		presenter.update(presenter.getGraphics());
		
		folge=new Vector();
		dlg = new Dialog(frame, "Eigenschaften", true);
		dlg.setLayout(new GridLayout(7,1));
		Label l1 = new Label("Name:");
		l1.setBackground(Color.lightGray);
		dlg.add(l1);
		txtName = new TextField(this.name);
		dlg.add(txtName);
		Label l2 = new Label("String-Folge");
		l2.setBackground(Color.lightGray);
		dlg.add(l2);
		txtZeichen = new TextField();
		dlg.add(txtZeichen);
		Button bHinzu = new Button("Hinzufuegen");
		bHinzu.addActionListener(dHandler);
		dlg.add(bHinzu);
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
			if (e.getActionCommand()=="Hinzufuegen"){
				String eintrag = txtZeichen.getText();
				if (eintrag.length() > 0){
					StringItem c = new StringItem(eintrag);
					ItemEvent ie = new ItemEvent (c);
					folge.addElement(ie);
					txtZeichen.setText("");
					if (textWindow==false){
						stringAnzeige = new Dialog(dlg, "String-Folge", false);
						stringAnzeige.setLayout(new GridLayout(1,1));
						txtAnzeige = new TextArea(eintrag);
						txtAnzeige.setEditable(false);
						stringAnzeige.add(txtAnzeige);
						stringAnzeige.setSize(new Dimension(180,180));
						stringAnzeige.setLocation( (int)(dlg.getLocationOnScreen()).getX() + 180, (int) (dlg.getLocationOnScreen()).getY() );
						stringAnzeige.setResizable(false);
						stringAnzeige.show();
						textWindow=true;
					}
					else{
						txtAnzeige.append("\n" + eintrag);
					}
				}
			}
			
			if (e.getActionCommand()=="OK"){
				String name = txtName.getText();
				if (name.length() > 0){
					StringGenerator.this.setString(name);
					StringGenerator.this.updateLines();
				}
				if (folge.size()>0){
					folge.add(0, new SignalEvent(START));
					folge.addElement(new SignalEvent(END));
					StringGenerator.this.events = folge;
				}
				try{
					stringAnzeige.setVisible(false);
				}
				catch (Exception exc){}
				dlg.setVisible(false);
				StringGenerator.this.changeToOldColor();
				presenter.update(presenter.getGraphics());
				textWindow=false;
			}
			
			if (e.getActionCommand()=="Abbrechen"){
				try{
					stringAnzeige.setVisible(false);
				}
				catch (Exception exc){}
				dlg.setVisible(false);
				StringGenerator.this.changeToOldColor();
				presenter.update(presenter.getGraphics());	
				textWindow=false;
			}
		}
	}
}