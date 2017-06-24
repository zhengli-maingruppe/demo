import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class StringProtocolStation extends TransparentStation{
private boolean doFile=true, doStdout=false, doSignals=false;
private boolean doProtocol = false, fileError = false;
private BufferedWriter datei = null;
private static int lfdNr = 0;
private String dateiName;

private Dialog dlg;
private TextField txtName;
private ActionListener dHandler = new DialogHandler();
private Checkbox box1, box2 , box3;

	public StringProtocolStation(MtjFrame frame, String name, boolean doFile, boolean doStdout, boolean doSignals){
		super(frame, name);
		this.doFile = doFile;
		this.doStdout = doStdout;
		this.doSignals = doSignals;
		sendItemKind=StringItem.class;
		recItemKind=StringItem.class;
	}
	
	public StringProtocolStation(MtjFrame frame, boolean doFile, boolean doStdout, boolean doSignals){
		super(frame, "string protocol station");
		this.doFile = doFile;
		this.doStdout = doStdout;
		this.doSignals = doSignals;
		sendItemKind=StringItem.class;
		recItemKind=StringItem.class;
	}

	public StringProtocolStation(MtjFrame frame, String name){
		super(frame, name);
		sendItemKind=StringItem.class;
		recItemKind=StringItem.class;
	}
	
	public StringProtocolStation(MtjFrame frame){
		super(frame, "string protocol station");
		sendItemKind=StringItem.class;
		recItemKind=StringItem.class;
	}
	
	private void makeFile(){
		fileError = false;
		dateiName = this.toString() + "_" + lfdNr + ".txt";
		try{
			datei = new BufferedWriter(new FileWriter(dateiName));
		}
		catch (IOException exc){
			System.out.println("Protokollierung in Datei " + dateiName + " fehlgeschlagen! :-(");
			fileError = true;
		}
		lfdNr++;
	}
	
	private void writeFile(String text){
		try{
			datei.write(text);
			datei.newLine();
		}
		catch (IOException exc){
			System.out.println("Protokollierung in Datei " + dateiName + " fehlgeschlagen! :-(");
			fileError = true;
			closeFile();
		}
	}
	
	private void closeFile(){
		try{
			datei.close();
		}
		catch (IOException exc){
			System.out.println("Beim Schließen der Datei " + dateiName + " trat ein Fehler auf! :-(");
		}
	}
	
	protected void processSignal(Signal signal){
		if (signal==START && doProtocol==false){
			makeFile();
			doProtocol=true;
		}
		
		if (doProtocol && doSignals){
			if (doStdout){
				System.out.println(">> " + signal);
				if (!output.isShowing()){
					output.showWindow();
				}
				output.println(">> " + signal);
			}
			if (doFile && fileError==false){
				String text=">> " + signal;
				writeFile(text);
			}
		}
		
		if (signal==END && doProtocol){
			doProtocol=false;
			closeFile();
		}
	}
	
	protected void processItem(Item item){
		if (doProtocol){
			if (doStdout){
				System.out.println("Item: " + item);
				if (!output.isShowing()){
					output.showWindow();
				}
				output.println("Item: " + item);
			}
			if (doFile && fileError==false){
				String text = "" + item;
				writeFile(text);
			}
		}
	}
	
	public void EigenschaftenDialog(MtjFrame frame){
		this.setRectColor(Color.blue);
		presenter.update(presenter.getGraphics());
		
		dlg = new Dialog(frame, "Eigenschaften", true);
		dlg.setLayout(new GridLayout(7,1));
		Label l1 = new Label("Name:");
		l1.setBackground(Color.lightGray);
		dlg.add(l1);
		txtName = new TextField(this.name);
		dlg.add(txtName);
		box1 = new Checkbox("In Datei protokollieren");
		box2 = new Checkbox("In neues Fenster ausgeben");
		box3 = new Checkbox("Signale protokollieren");
		dlg.add(box1);
		dlg.add(box2);
		dlg.add(box3);
		box1.setState(doFile);
		box2.setState(doStdout);
		box3.setState(doSignals);
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
					StringProtocolStation.this.setString(name);
					StringProtocolStation.this.updateLines();
				}
				dlg.setVisible(false);
				StringProtocolStation.this.changeToOldColor();
				presenter.update(presenter.getGraphics());
				doFile=box1.getState();
				doStdout=box2.getState();
				doSignals=box3.getState();
			}
			
			if (e.getActionCommand()=="Abbrechen"){
				dlg.setVisible(false);
				StringProtocolStation.this.changeToOldColor();
				presenter.update(presenter.getGraphics());	
			}
		}
	}
}