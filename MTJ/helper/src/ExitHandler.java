import java.awt.event.*;

public class ExitHandler implements ActionListener{
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand() == "Exit") {
			System.exit(0);
		}
	}
}