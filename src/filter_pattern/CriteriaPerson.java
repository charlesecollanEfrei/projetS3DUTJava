package filter_pattern;

import java.util.ArrayList;
import presentation.Person;

public interface CriteriaPerson extends Criteria {
	public ArrayList<Person> meetCriteria(ArrayList<?> persons);
}
