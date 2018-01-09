package filter_pattern;

import java.util.ArrayList;
import presentation.Presentation;

public class CriteriaDateInit implements CriteriaDate{

	public ArrayList<Presentation> meetCriteria(ArrayList<?> list) {

		ArrayList<Presentation> exEvent = new ArrayList<Presentation>(); 
		for (Object event : list) {
			if(event.getClass().getName().toLowerCase().equals("presentation.presentation")){
				exEvent.add((Presentation) event);
			}
		}
		
		ArrayList<Presentation> examDateSettled = new ArrayList<Presentation>(); 

		for (Presentation event : exEvent) {
			if(!(event.getDate() == null)){
				examDateSettled.add(event);
			}
		}
		return examDateSettled;
	}

}
