package presentation;

import session.Session;

public class Person {
	
	public enum PersonFunction{
		NONE("none"),
		JURY("jury"),
		STUDENT("student");
		
		private String personFunction;
		
		PersonFunction(String personFunction) {
			this.personFunction = personFunction;
		}
		
		public String toString() {
			return Session.instance().getString(personFunction);
		}		
	}
	
	protected PersonFunction personFunction;
	protected String firstname;
	protected String lastname;
	protected String email;
	protected String phone;
	
	/**
	 * Basic constructor for Person objects
	 */
	public Person() {
		personFunction = PersonFunction.NONE;
	}
	
	/**
	 * Constructor for Person objects with all needed parameters
	 * @param personFunction
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param phone
	 */
	public Person(PersonFunction personFunction, String firstname, String lastname, String email, String phone) {
		this.personFunction = personFunction;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;		
	}
	
	public void setFunction(PersonFunction function) {
		this.personFunction = function;
	}
	public PersonFunction getFunction() {
		return personFunction;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getFirstname() {
		return firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setMail(String email) {
		this.email = email;
	}
	public String getMail() {
		return email;
	}
	public void setTel(String phone) {
		this.phone = phone;
	}
	public String getTel() {
		return phone;
	}

}
