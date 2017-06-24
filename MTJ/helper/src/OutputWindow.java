import java.awt.*;
import java.awt.event.*;

public class OutputWindow extends Dialog{
private MtjFrame presenter;
private MtjRectangle owner;
private TextArea text;
private Button bClose, bClear;
private ActionListener alClose = new ButtonHandler();
private Panel p;

	public OutputWindow(String titel, MtjFrame frame, MtjRectangle owner){
		super(frame, titel, false);
		presenter = frame;
		this.owner = owner;
		setLayout(new BorderLayout());
		setSize(new Dimension(260,180));
		text = new TextArea();
		text.setEditable(false);
		add(text, BorderLayout.CENTER);
		p = new Panel(new GridLayout(1,2));
		add(p, BorderLayout.SOUTH);
		
		bClear = new Button("Loeschen");
		bClear.addActionListener(alClose);
		p.add(bClear);
		bClose = new Button("Schliessen");
		bClose.addActionListener(alClose);
		p.add(bClose);
	}
	
	public void showWindow(){
		this.setLocation((int) owner.getLocation().getX() + (int) presenter.getLocation().getX(), (int) owner.getLocation().getY() + (int) presenter.getLocation().getY());
		this.show();
		owner.setRectColor(Color.green.darker());
	}
	
	public void setName(String title){
		setTitle(title);
	}
	
	public void println(String output){
		text.append(">" + output + "\n");
	}
	
	class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand() == "Schliessen") {
				OutputWindow.this.setVisible(false);
				text.setText("");
				owner.setRectColor(Color.black);
				owner.draw(owner.presenter.getGraphics());
			}
			if (e.getActionCommand() == "Loeschen") {
				text.setText("");
			}
		}
	}
}