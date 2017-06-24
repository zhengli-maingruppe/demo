import java.util.*;
import java.awt.*;

public class MtjRectangleSet{
public Vector rectSet = new Vector();
private Component presenter;

	public MtjRectangleSet(Component c){
		this.presenter = c;
	}
	
	public void addRect(MtjRectangle rect, int nx, int ny){
		rectSet.addElement(rect);
		rect.x = nx;
		rect.y = ny;
		rect.setPresenter(this.presenter);
		rect.draw(presenter.getGraphics());
	}
	
	public void removeRect(MtjRectangle rect){
		if (rectSet.contains(rect)){
			rectSet.removeElement(rect);
			presenter.update(presenter.getGraphics());
		}
	}
	
	public MtjRectangle getRect(int x, int y){
		for (int i=0; i<rectSet.size(); i++){
			MtjRectangle r = (MtjRectangle) rectSet.elementAt(i);
			if (r.contains(x,y)){
				return r;
			}
		}
		return null;
	}
	
	public void draw(Graphics g){
		for (int i=0; i<rectSet.size(); i++){
			((MtjRectangle) rectSet.elementAt(i)).draw(g);
		}
	}
}