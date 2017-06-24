import java.awt.*;
import java.awt.event.*;

public class MtjFrame extends Frame{

public ConfigurationArea flaeche;
public ActionListener alExit = new ExitHandler();
public Label l1, l2, l3, l4, l5, l6;

	public MtjFrame(String titel){
		super(titel);
		this.addWindowListener( new WindowAdapter() {
			public void windowClosing( WindowEvent e ) {
				dispose();
				System.exit( 0 );
			}
		} );
		this.setSize(new Dimension(400,400));
		this.setLocation(new Point(50,50));
		this.setLayout(new BorderLayout());
		
		//Menueleiste erstellen
		MenuBar mb = new MenuBar();
		Menu mFile = new Menu("File");
		MenuItem mFile_Exit = new MenuItem("Exit");
		mFile_Exit.setActionCommand("Exit");
		mFile_Exit.addActionListener(alExit);
		mFile.add(mFile_Exit);
		mb.add(mFile);
		this.setMenuBar(mb);
		
		//Koordinatenanzeige erstellen
		Panel p = new Panel(new GridLayout(1,8));
		p.setBackground(Color.pink);
		l1 = new Label("x-pos:"); l2 = new Label("-");
		l3 = new Label("y-pos:"); l4 = new Label("-");
		l5 = new Label("Mode:"); l6 = new Label("Normal");
		p.add(l1); p.add(l2); p.add(l3); p.add(l4); p.add(l5); p.add(l6);
		
		this.add(p, BorderLayout.NORTH);
		
		//Zeichenflaeche erstellen
		flaeche = new ConfigurationArea(l2, l4, l6, this);
		this.add(flaeche, BorderLayout.CENTER);
		
		//Exit-Button erstellen
		Button bExit = new Button("Exit");
		bExit.addActionListener(alExit);
		this.add(bExit, BorderLayout.SOUTH);
	}

}