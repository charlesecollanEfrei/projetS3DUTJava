package session;

import java.util.ArrayList;
import presentation.Presentation;

public class Planning extends ArrayList<Presentation> {

	/**Initialise the planning of the current agenda
	 * 
	 */
	public Planning() {
		super();
	}

	/**
	 * add a presentation to the planning
	 * @param presentation
	 */
	public void addCheckedEvent(Presentation presentation) {
		this.add(presentation);
	}

}