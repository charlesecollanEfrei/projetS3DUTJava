package filter_pattern;

import java.util.ArrayList;

public class AndCriteria implements Criteria {
	
	private Criteria criteria;
	private Criteria otherCriteria;
	
	public AndCriteria(Criteria aCriteria, Criteria anotherCriteria) {
		
		this.criteria = aCriteria;
		this.otherCriteria = anotherCriteria;
		
	}

	@Override
	public ArrayList<?> meetCriteria(ArrayList<?> element) {
		// TODO Auto-generated method stub
		ArrayList<?> firstCriteriaPersons = criteria.meetCriteria(element);		
	    return otherCriteria.meetCriteria(firstCriteriaPersons);
	}

}
