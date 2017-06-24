import java.io.*;

public class FileReaderStation extends ItemSplitStation{
	public FileReaderStation(String name, boolean doSignals){
		super(name, doSignals);
		startSignal = new SignalEvent(FILE_START);
		endSignal = new SignalEvent(FILE_END);
		sendItemKind = StringItem.class;
		recItemKind = StringItem.class;
	}
	
	public FileReaderStation(boolean doSignals){
		super("File-Reader-Station", doSignals);
		startSignal = new SignalEvent(FILE_START);
		endSignal = new SignalEvent(FILE_END);
		sendItemKind = StringItem.class;
		recItemKind = StringItem.class;
	}
	
	public FileReaderStation(){
		super("File-Reader-Station", true);
		startSignal = new SignalEvent(FILE_START);
		endSignal = new SignalEvent(FILE_END);
		sendItemKind = StringItem.class;
		recItemKind = StringItem.class;
	}
	
	protected void processSignal(Signal signal){
	}
	
	protected void processItem(Item item){
		boolean vorhanden = false;
		String zeile;
		String DateiName = ((StringItem)item).getValue();
		BufferedReader datei=null;
		try{
			datei = new BufferedReader(new FileReader(DateiName));
			vorhanden=true;
			zeile = datei.readLine();
			while (zeile!=null){
				StringItem z = new StringItem(zeile);
				ItemEvent e = new ItemEvent (z);
				sendEvent(e);
				zeile = datei.readLine();
			}
		}
		catch (FileNotFoundException exc){
			System.out.println("Die Datei " + DateiName + " ist nicht vorhanden oder beschaedigt! :-(");
		}
		catch (IOException exc){
			System.out.println("Beim Lesen aus der Datei " + DateiName + " trat ein Fehler auf! :-(");
		}
		if (vorhanden){
			try{
				datei.close();
			}
			catch (IOException exc){
				System.out.println("Beim Schlieﬂen der Datei " + DateiName + " trat ein Fehler auf! :-(");
			}
		}
	}
}