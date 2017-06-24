import java.awt.event.*;
import java.awt.*;

public class CharGenerator extends GenStation{
private Dialog dlg;
private TextField txtName, txtZeichen;
private ActionListener dHandler = new DialogHandler();


	public CharGenerator (String name, String zeichen){
		super(name);
		sendItemKind=CharItem.class;
		events.addElement(new SignalEvent(START));
		for (int i=0; i<zeichen.length(); i++){
			CharItem c = new CharItem(zeichen.charAt(i));
			ItemEvent e = new ItemEvent (c);
			events.addElement(e);
		}
		events.addElement(new SignalEvent(END));
		System.out.println("<" + this.getName() + "> hat Ereignisfolge mit <" + zeichen + "> erzeugt");
	}
	
	public CharGenerator (String zeichen){
		this("character generator station", zeichen);
	}
	
	public void EigenschaftenDialog(MtjFrame frame){
		this.setRectColor(Color.blue);
		presenter.update(presenter.getGraphics());
		
		dlg = new Dialog(frame, "Eigenschaften", true);
		dlg.setLayout(new GridLayout(6,1));
		Label l1 = new Label("Name:");
		l1.setBackground(Color.lightGray);
		dlg.add(l1);
		txtName = new TextField(this.name);
		dlg.add(txtName);
		Label l2 = new Label("Zeichenfolge");
		l2.setBackground(Color.lightGray);
		dlg.add(l2);
		txtZeichen = new TextField();
		dlg.add(txtZeichen);
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
					CharGenerator.this.setString(name);
					CharGenerator.this.updateLines();
				}
				
				String zeichen = txtZeichen.getText();
				if (zeichen.length() > 0){
					events.removeAllElements();
					events.addElement(new SignalEvent(START));
					for (int i=0; i<zeichen.length(); i++){
						CharItem c = new CharItem(zeichen.charAt(i));
						ItemEvent el = new ItemEvent (c);
						events.addElement(el);
					}
					events.addElement(new SignalEvent(END));
				}
			}
			presenter.update(presenter.getGraphics());
			dlg.setVisible(false);
			CharGenerator.this.changeToOldColor();
		}
	}
}