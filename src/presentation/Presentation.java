package presentation;

import java.util.ArrayList;
import java.util.Date;

public class Presentation {
	
	private Date dateSoutenance;
	private Person student;
	private ArrayList<Person> jury;
	private Classroom classroom;
	private ArrayList<Document> documents;
	
	/**
	 * Basic constructor for Presentation objects
	 */
	public Presentation() {
		this.student = new Person();
		this.jury = new ArrayList<Person>();
		this.classroom = new Classroom();
		this.documents = new ArrayList<Document>();
		this.dateSoutenance = null;
	}
	
	/**
	 * Constructor for Presentation objects with all needed attribute
	 * @param date
	 * @param person
	 * @param jury
	 * @param classRoom
	 * @param document
	 */
	public Presentation(Date date, Person person, ArrayList<Person> jury, Classroom classRoom, ArrayList<Document> document) {
		this.dateSoutenance = date;
		this.student = person;
		this.jury = jury;
		this.classroom = classRoom;
		this.documents = document;
	}
	
	public Date getDate() {
		return this.dateSoutenance;
	}

	public Person getStudent() {
		return this.student;
	}
	public ArrayList<Person> getJury() {
		return this.jury;
	}
	public Classroom getClassroom() {
		return this.classroom;
	}
	public ArrayList<Document> getDocuments() {
		return this.documents;
	}
	public void addJury(Person unePersonne) {
		this.jury.add(unePersonne);
	}
	public void changeDate(Date uneDate) {
		this.dateSoutenance = uneDate;
	}
	public void changeClassroom(Classroom classr){
		this.classroom = classr;
	}
	public void changeJury(ArrayList<Person> member){
		this.jury = member;
	}
	
	/**
	 * Remove one Person from the jury and add another one
	 * @param member
	 * @param oldMember
	 * @return
	 */
	public boolean changeOnePersonInJury(Person member, Person oldMember){
		if (this.jury.contains(oldMember)) {
			int indice = this.jury.indexOf(oldMember);
			if (indice != -1){
				this.jury.remove(indice);
				this.jury.add(member);
				return true;
			}
		}
		return false;
	}
	public void changeStudent(Person student){
		this.student = student;
	}

}