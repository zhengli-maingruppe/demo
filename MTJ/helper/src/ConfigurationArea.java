import java.awt.*;
import java.awt.event.*;

public class ConfigurationArea extends Canvas implements RectRegion.Values{
public MtjRectangleSet rectSet;
private Label xLabel, yLabel, modeLabel;
private Component presenter;
private int lastX, lastY;
private boolean inDrag=false;
private boolean inDrawLine=false;
private PopupMenu pm, pm2;
private MenuItem pm2DelSenders, pm2GenEvent;
private MtjRectangle dragRect;
private ConnectionLine drawLine;
public ActionListener alPopup = new PopupHandler();

	public ConfigurationArea(Label xLabel, Label yLabel, Label modeLabel, Component presenter){
		rectSet = new MtjRectangleSet(this);
		this.xLabel = xLabel;
		this.yLabel = yLabel;
		this.modeLabel = modeLabel;
		this.presenter = presenter;
		
		this.addMouseMotionListener(new MouseMotionHandler());
		this.addMouseListener(new MouseHandler());
		
		//popup-Menue für freie Flaeche
		pm = new PopupMenu();
		Menu pmGenStation = new Menu("Generatorstationen");
		MenuItem pmGenString = new MenuItem("String-Generator");
		MenuItem pmGenChar = new MenuItem("Char-Generator");
		pmGenString.setActionCommand("pmGenString");
		pmGenChar.setActionCommand("pmGenChar");
		pmGenString.addActionListener(alPopup);
		pmGenChar.addActionListener(alPopup);
		pmGenStation.add(pmGenString);
		pmGenStation.add(pmGenChar);
		pm.add(pmGenStation);
		
		Menu pmProcStation = new Menu("Arbeitsstationen");
		MenuItem pmProcCounter = new MenuItem("Item-Counter-Station");
		MenuItem pmProcProtocol = new MenuItem("String-Protocol-Station");
		MenuItem pmProcUCF = new MenuItem("UCFilter-Station");
		MenuItem pmProcLCF = new MenuItem("LCFilter-Station");
		MenuItem pmProcDGF = new MenuItem("DGFilter-Station");
		MenuItem pmProcConcat = new MenuItem("String-Concat-Station");
		MenuItem pmProcToWord = new MenuItem("String-to-Word-Station");
		MenuItem pmProcToChar = new MenuItem("String-to-Char-Station");
		MenuItem pmProcFileReader = new MenuItem("File-Reader-Station");
		pmProcStation.add(pmProcCounter);
		pmProcStation.add(pmProcProtocol);
		pmProcStation.add(pmProcUCF);
		pmProcStation.add(pmProcLCF);
		pmProcStation.add(pmProcDGF);
		pmProcStation.add(pmProcConcat);
		pmProcStation.add(pmProcToWord);
		pmProcStation.add(pmProcToChar);
		pmProcStation.add(pmProcFileReader);
		pmProcCounter.setActionCommand("pmProcCounter");
		pmProcProtocol.setActionCommand("pmProcProtocol");
		pmProcUCF.setActionCommand("pmProcUCF");
		pmProcLCF.setActionCommand("pmProcLCF");
		pmProcDGF.setActionCommand("pmProcDGF");
		pmProcConcat.setActionCommand("pmProcConcat");
		pmProcToWord.setActionCommand("pmProcToWord");
		pmProcToChar.setActionCommand("pmProcToChar");
		pmProcFileReader.setActionCommand("pmProcFileReader");
		pmProcCounter.addActionListener(alPopup);
		pmProcProtocol.addActionListener(alPopup);
		pmProcUCF.addActionListener(alPopup);
		pmProcLCF.addActionListener(alPopup);
		pmProcDGF.addActionListener(alPopup);
		pmProcConcat.addActionListener(alPopup);
		pmProcToWord.addActionListener(alPopup);
		pmProcToChar.addActionListener(alPopup);
		pmProcFileReader.addActionListener(alPopup);
		
		pm.add(pmProcStation);
		MenuItem pmExit = new MenuItem("Exit");
		pmExit.setActionCommand("pmExit");
		pmExit.addActionListener(alPopup);
		pm.add(pmExit);
		this.add(pm);
		
		//popup-Menue für Stationen
		pm2 = new PopupMenu();
		MenuItem pm2Del = new MenuItem("Station loeschen");
		pm2Del.setActionCommand("pm2Del");
		pm2Del.addActionListener(alPopup);
		pm2.add(pm2Del);
		MenuItem pm2DelListeners = new MenuItem("Release from Listeners");
		pm2DelListeners.setActionCommand("pm2DelListeners");
		pm2DelListeners.addActionListener(alPopup);
		pm2.add(pm2DelListeners);
		pm2DelSenders = new MenuItem("Release from Senders");
		pm2DelSenders.setActionCommand("pm2DelSenders");
		pm2DelSenders.addActionListener(alPopup);
		pm2.add(pm2DelSenders);
		pm2GenEvent= new MenuItem("Generate Events");
		pm2GenEvent.setActionCommand("pm2GenEvent");
		pm2GenEvent.addActionListener(alPopup);
		pm2.add(pm2GenEvent);
		MenuItem pm2ChangeName = new MenuItem("Eigenschaften");
		pm2ChangeName.setActionCommand("pm2ChangeName");
		pm2ChangeName.addActionListener(alPopup);
		pm2.add(pm2ChangeName);
		MenuItem pm2Exit = new MenuItem("Exit");
		pm2Exit.setActionCommand("pmExit");
		pm2Exit.addActionListener(alPopup);
		pm2.add(pm2Exit);
		this.add(pm2);
	}
	
	public void paint(Graphics g){
		rectSet.draw(g);
	}
	
	
	//Inner Class
	//Mausbewegung
	class MouseMotionHandler implements MouseMotionListener{	
		public void mouseMoved(MouseEvent e){
			xLabel.setText("" + e.getX());
			yLabel.setText("" + e.getY());
		}
		
		public void mouseDragged(MouseEvent e){
			xLabel.setText("" + e.getX());
			yLabel.setText("" + e.getY());
			if (inDrag){
				rectSet.removeRect(dragRect);
				rectSet.addRect(dragRect, e.getX() - dragRect.width/2 , e.getY() - dragRect.height/2);
				dragRect.updateLines();
			}
			else{
				if (inDrawLine){
					drawLine.moveEnd(e.getX(), e.getY());
					update(getGraphics());
				}
			}	
		}
	}
	
	//Maustasten
	class MouseHandler extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			lastX=e.getX(); lastY=e.getY();
			int modif = e.getModifiers();
			if ((modif & InputEvent.BUTTON1_MASK) == 0){
				MtjRectangle r=rectSet.getRect(lastX, lastY);
				if (r!=null){
					pm2DelSenders.setEnabled(true);
					pm2GenEvent.setEnabled(false);
					if (r instanceof GenStation){
						pm2DelSenders.setEnabled(false);
						pm2GenEvent.setEnabled(true);
					}
					pm2.show(ConfigurationArea.this, lastX, lastY);
				}
				else{
					pm.show(ConfigurationArea.this, lastX, lastY);
				}
			}
		}
		
		public void mousePressed(MouseEvent e){
			lastX=e.getX(); lastY=e.getY();
			int modif = e.getModifiers();
			if ((modif & InputEvent.BUTTON1_MASK) != 0){
				dragRect = rectSet.getRect(lastX, lastY);
				if ( dragRect!=null){
					if (dragRect.getRegion(lastX, lastY)==CENTER){
						inDrag=true;
						modeLabel.setText("Drag");
					}
					if (dragRect.getRegion(lastX, lastY)==RIGHT){
						inDrawLine=true;
						modeLabel.setText("Draw");
						drawLine=new ConnectionLine(dragRect.x + dragRect.width, dragRect.y + dragRect.height/2, dragRect.x + dragRect.width, dragRect.y + dragRect.height/2);
						dragRect.addLineOut(drawLine);
					}
				}
			}
		}
		
		public void mouseReleased(MouseEvent e){
			if (inDrawLine){
				MtjRectangle lineTargetRect = rectSet.getRect(e.getX(), e.getY());
				if (lineTargetRect!=null && lineTargetRect!=dragRect){
					try{
						((EventSender) dragRect).addEventListener(((EventListener) lineTargetRect));
						lineTargetRect.addLineIn(drawLine);
						drawLine.moveEnd(lineTargetRect.x, lineTargetRect.y + lineTargetRect.height/2);
					}
					catch (MtjException m){
						drawLine.setLineColor(Color.red);
						update(getGraphics());
						ErrorDialog f = new ErrorDialog((MtjFrame) presenter, m.Sender, m.Listener);
						dragRect.removeLineOut(drawLine);
					}
					catch(Exception m){
						drawLine.setLineColor(Color.red);
						update(getGraphics());
						ErrorDialog f = new ErrorDialog((MtjFrame) presenter, "Einer Generatorstation kann kein", "Sender hinzugefuegt werden!");
						dragRect.removeLineOut(drawLine);
					}
				}
				else{
					dragRect.removeLineOut(drawLine);
				}
			}
			update(getGraphics());
			drawLine = null;
			dragRect = null;
			inDrawLine = false;
			inDrag=false;
			modeLabel.setText("Normal");
		}
	}
	
	//Popup-Menue
	class PopupHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand() == "pmGenString") {
				rectSet.addRect(new StringGenerator(new String[] {"Item.java"}), lastX, lastY);
			}
			if (e.getActionCommand() == "pmGenChar") {
				rectSet.addRect(new CharGenerator(""), lastX, lastY);
			}
			if (e.getActionCommand() == "pmProcCounter") {
				rectSet.addRect(new ItemCounterStation((MtjFrame) presenter), lastX, lastY);
			}
			if (e.getActionCommand() == "pmProcProtocol") {
				rectSet.addRect(new StringProtocolStation((MtjFrame) presenter), lastX, lastY);
			}
			if (e.getActionCommand() == "pmProcUCF") {
				rectSet.addRect(new UCFilterStation(), lastX, lastY);
			}
			if (e.getActionCommand() == "pmProcLCF") {
				rectSet.addRect(new LCFilterStation(), lastX, lastY);
			}
			if (e.getActionCommand() == "pmProcDGF") {
				rectSet.addRect(new DGFilterStation(), lastX, lastY);
			}
			if (e.getActionCommand() == "pmProcConcat") {
				rectSet.addRect(new StringConcatStation(80), lastX, lastY);
			}
			if (e.getActionCommand() == "pmProcToWord") {
				rectSet.addRect(new StringToWordStation(), lastX, lastY);
			}
			if (e.getActionCommand() == "pmProcToChar") {
				rectSet.addRect(new StringToCharStation(), lastX, lastY);
			}
			if (e.getActionCommand() == "pmProcFileReader") {
				rectSet.addRect(new FileReaderStation(), lastX, lastY);
			}
			
			//Popup-Menue 2 (Auf Rechteck)
			if (e.getActionCommand() == "pm2Del") {
				MtjRectangle r = rectSet.getRect(lastX, lastY);
				if ( r != null){
					rectSet.removeRect(r);
					r.removeAllLinesIn(rectSet.rectSet);
					r.removeAllLinesOut(rectSet.rectSet);
					((Station)r).releaseFromListeners();
					if (r instanceof ProcStation){
						((ProcStation)r).releaseFromSenders();
					}
				}
			}
			if (e.getActionCommand() == "pm2DelListeners"){
				MtjRectangle r = rectSet.getRect(lastX, lastY);
				if ( r != null){
					r.removeAllLinesOut(rectSet.rectSet);
					((Station)r).releaseFromListeners();
				}
			}
			if (e.getActionCommand() == "pm2GenEvent"){
				MtjRectangle r = rectSet.getRect(lastX, lastY);
				if ( r != null){
					((GenStation)r).generateEvents();
				}
			}
			if (e.getActionCommand() == "pm2DelSenders"){
				MtjRectangle r = rectSet.getRect(lastX, lastY);
				if ( r != null){
					try{
						r.removeAllLinesIn(rectSet.rectSet);
						((ProcStation)r).releaseFromSenders();
					}
					catch (Exception m){
					}
				}
			}
			if (e.getActionCommand() == "pm2ChangeName"){
				MtjRectangle r = rectSet.getRect(lastX, lastY);
				if ( r != null){
					((Station) r).EigenschaftenDialog((MtjFrame) presenter);
					//PopupDialog f = new PopupDialog((MtjFrame) presenter, r, ConfigurationArea.this);
				}
			}
			if (e.getActionCommand() == "pmExit"){
				System.exit(0);
			}
			update(getGraphics());
		}
	}
	
}