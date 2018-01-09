package filter_pattern;

import java.util.ArrayList;

/**
 * Basic class of the filter pattern. The method of this class is implemented by every single criteria
 * @author Charles Ecollan, Quentin Bresson, Hubert Mouginot
 */
public interface Criteria {
	public ArrayList<?> meetCriteria(ArrayList<?> element);
}
