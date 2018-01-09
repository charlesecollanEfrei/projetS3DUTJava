package filter_pattern;

import java.util.ArrayList;
import presentation.Classroom;

public class CriteriaClassroomNotInit implements CriteriaClassroom {
	
	public ArrayList<Classroom> meetCriteria(ArrayList<?> list) {
		
		ArrayList<Classroom> classroom = new ArrayList<Classroom>(); 
		for (Object event : list) {
			if(event.getClass().getName().toLowerCase().equals("presentation.classroom")){
				classroom.add((Classroom) event);
			}
		}
		
		ArrayList<Classroom> classInit = new ArrayList<Classroom>(); 

		for (Classroom event : classroom) {
			if(event.getClassRoomNumber().toLowerCase().equals("not affected")){
				classInit.add(event);
			}
		}
		return classInit;
		
	}

}
