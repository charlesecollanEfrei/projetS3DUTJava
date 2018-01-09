package presentation;

import java.util.ArrayList;

public class Agenda {
	
	private ArrayList<Presentation> planning;
	
	public Agenda(){
		this.planning = new ArrayList<Presentation>();
	}
	
	public Agenda(Presentation presentation) {
		this.planning = new ArrayList<Presentation>();
		this.planning.add(presentation);
	}
	
	public Agenda(ArrayList<Presentation> presentation){
		this.planning = presentation;
	}
	
	public ArrayList<Presentation> getPlanning(){
		return this.planning;
	}
	
	public void addPresentation(Presentation presentation){
		this.planning.add(presentation);
	}
	
	public boolean supprimerPresentation(Presentation presentation){
		
		if (this.planning.contains(presentation)) {
			int indice = this.planning.indexOf(presentation);
			if (indice != -1){
				this.planning.remove(indice);
				return true;
			}
		}
		return false;
	}

}
