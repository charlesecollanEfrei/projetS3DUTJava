package filter_pattern;

import java.util.ArrayList;
import presentation.Presentation;

public class CriteriaDateNotInit implements CriteriaDate {
	
	public ArrayList<Presentation> meetCriteria(ArrayList<?> list) {

		ArrayList<Presentation> exEvent = new ArrayList<Presentation>(); 
		for (Object event : list) {
			if(event.getClass().getName().toLowerCase().equals("presentation.presentation")){
				exEvent.add((Presentation) event);
			}
		}
		
		ArrayList<Presentation> examDateNotSettled = new ArrayList<Presentation>(); 

		for (Presentation event : exEvent) {
			if(event.getDate() == null){
				examDateNotSettled.add(event);
			}
		}
		return examDateNotSettled;
	}

}