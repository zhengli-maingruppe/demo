import java.awt.*;
import java.util.*;

public class MtjRectangle extends Rectangle implements RectRegion.Values{
protected static Font font = new Font("TimesNewRoman", Font.PLAIN, 12);
protected String name = "Uninitialisiert";
protected Component presenter = null;
protected Vector lineOut = new Vector();
protected Vector lineIn = new Vector();
protected Color color = Color.black;
protected Color oldColor = Color.black;


	public MtjRectangle(String text){
		this.name=text;
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		int hoehe = fm.getHeight();
		int breite = fm.stringWidth(name);
		width = 5 + breite + 5;
		height = 3 + hoehe + 3;
		g.drawRect(x, y, width, height);
		g.drawString(name, x + 5, y + 1 + hoehe);
		g.setColor(Color.black);
		for (int i=0; i<lineOut.size(); i++){
			((ConnectionLine) lineOut.elementAt(i)).draw(g);
		}
	}
	
	public void setString(String text){
		this.name = text;
	}
	
	public RectRegion getRegion(int mx, int my){
		if (this.contains(mx, my)){
			if (mx <= x + (width/3)){
				return LEFT;
			}
			if (mx >= x + 2*(width/3)){
				return RIGHT;
			}
			return CENTER;
		}
		return null;
	}
		
	public void setRectColor(Color c){
		this.oldColor = this.color;
		this.color = c;
	}
	
	public void changeToOldColor(){
		this.color = this.oldColor;
	}
	
	public void setPresenter (Component c){
		this.presenter = c;
	}
	
	public void addLineIn(ConnectionLine line){
		lineIn.addElement(line);
	}
	
	public void addLineOut(ConnectionLine line){
		lineOut.addElement(line);
	}
	
	public void removeLineIn(ConnectionLine line){
		if (lineIn.contains(line)){
			lineIn.removeElement(line);
		}
	}
	
	public void removeLineOut(ConnectionLine line){
		if (lineOut.contains(line)){
			lineOut.removeElement(line);
		}
	}
	
	public void removeAllLinesIn(Vector set){
		for (int i=lineIn.size()-1; i>=0; i--){
			ConnectionLine line = (ConnectionLine) lineIn.elementAt(i);
			lineIn.removeElement(line);
			for (int j=0; j<set.size();j++){
				((MtjRectangle) set.elementAt(j)).removeLineOut(line);
			}
		}
	}
	
	public void removeAllLinesOut(Vector set){
		for (int i=lineOut.size()-1; i>=0; i--){
			ConnectionLine line = (ConnectionLine) lineOut.elementAt(i);
			lineOut.removeElement(line);
			for (int j=0; j<set.size();j++){
				((MtjRectangle) set.elementAt(j)).removeLineIn(line);
			}
		}
	}
	
	public void updateLines(){
		for (int i=0; i<lineOut.size(); i++){
			((ConnectionLine) lineOut.elementAt(i)).moveStart(this.x + this.width, this.y + this.height/2);
		}
		for (int i=0; i<lineIn.size(); i++){
			((ConnectionLine) lineIn.elementAt(i)).moveEnd(this.x, this.y + this.height/2);
		}
	}
}