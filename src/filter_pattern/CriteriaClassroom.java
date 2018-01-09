package filter_pattern;

import java.util.ArrayList;
import presentation.Classroom;

public interface CriteriaClassroom extends Criteria {
	public ArrayList<Classroom> meetCriteria(ArrayList<?> element);
}
