package filter_pattern;

import java.util.ArrayList;
import presentation.Person;
import presentation.Person.PersonFunction;

public class CriteriaPersonJury implements CriteriaPerson{

	@Override
	public ArrayList<Person> meetCriteria(ArrayList<?> list) {
		// TODO Auto-generated method stub
		
		ArrayList<Person> pers = new ArrayList<Person>(); 
		for (Object event : list) {
			if(event.getClass().getName().toLowerCase().equals("presentation.person")){
				pers.add((Person) event);
			}
		}
		
		ArrayList<Person> juryPersons = new ArrayList<Person>(); 
	      
	      for (Person person : pers) {
	         if(person.getFunction() == PersonFunction.JURY){
	            juryPersons.add(person);
	         }
	      }
	      return juryPersons;
	}

}
