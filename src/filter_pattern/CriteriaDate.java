
package filter_pattern;

import java.util.ArrayList;
import presentation.Presentation;

public interface CriteriaDate extends Criteria {
	public ArrayList<Presentation> meetCriteria(ArrayList<?> dateEvent);
}
