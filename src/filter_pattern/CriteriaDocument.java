package filter_pattern;

import java.util.ArrayList;
import presentation.Document;

public interface CriteriaDocument extends Criteria {
	public ArrayList<Document> meetCriteria(ArrayList<?> list);
}
