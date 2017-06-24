import java.awt.*;

public class ConnectionLine{
private int x1, x2, y1, y2;
private Color color = Color.black;
private Component presenter = null;
	
	public ConnectionLine(int x1, int y1, int x2, int y2){
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
	}
	
	public void moveStart(int x1, int y1){
		this.x1 = x1; this.y1 = y1;
	}
	
	public void moveEnd(int x2, int y2){
		this.x2 = x2; this.y2 = y2;
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(Color.black);
	}
	
	public void setPresenter (Component c){
		this.presenter = c;
	}
	
	public void setLineColor(Color c){
		this.color = c;
	}

}