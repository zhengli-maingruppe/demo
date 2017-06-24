package mtj.level1;

public class Aufgabe02 {

	public static void main(String[] args){
		 GenStation g = new GenStation(); 
		 ProcStation w1 = new ProcStation("Station 1"); 
		 ProcStation w2 = new ProcStation("Station 2"); 
		 g.addEventListener(w1); 
		 w1.addEventListener(w2); 
		 
		 g.generateEvent();
	}
}
