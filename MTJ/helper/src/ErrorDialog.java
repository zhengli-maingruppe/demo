import java.awt.*;
import java.awt.event.*;

public class ErrorDialog extends Dialog{
private Label messageSender=new Label();
private Label messageListener=new Label();
private Button b1 = new Button("OK");
private ActionListener alOK = new ButtonHandler();
	
	public ErrorDialog(MtjFrame frame, String Sender, String Listener){
		super(frame, "MtjException", true);
		messageSender.setText(Sender);
		messageListener.setText(Listener);
		this.setLayout(new GridLayout(3,1));
		this.setSize(new Dimension(300,95));
		this.setResizable(false);
		this.setLocation(frame.getLocationOnScreen());
		this.add(messageSender);
		this.add(messageListener);
		b1.addActionListener(alOK);
		this.add(b1);
		this.show();
	}
				
	class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setVisible(false);
		}
	}
}