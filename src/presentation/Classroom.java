package presentation;

public class Classroom {

	protected String classRoomNumber;

	/**
	 * Basic constructor for the Classroom class : set the name of the room as "not affected"
	 */
	public Classroom() {
		classRoomNumber = "not affected";
	}
	
	/**
	 * Classroom constructor with the number of the classroom as parameter
	 * @param classRoomNumber
	 */
	public Classroom(String classRoomNumber) {
		this.classRoomNumber = classRoomNumber;
	}

	/**
	 * Setter for the classRoomNumber attribute
	 * @param number
	 */
	public void setClassroomNumber(String number) {
		this.classRoomNumber = number;
	}
	
	/**
	 * Getter for the classRoomNumber attribute
	 * @return classRoomNumber
	 */
	public String getClassRoomNumber() {
		return classRoomNumber;
	}

}
