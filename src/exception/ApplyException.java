package exception;

import session.Session;

/**
 * This class handle the Exception of the program
 * Please note that the program isn't complete yet, so this class can't show his full potential
 * @author Charles Ecollan, Quentin Bresson, Hubert Mouginot, Thomas Tournoux and Eric Martelot
 *
 */
public class ApplyException extends Exception {
	
	public ApplyException() {
		super();
		Session session = Session.instance();
		session.getExceptionLogger().severe("empty");
	}
	public ApplyException(ApplyException e) {
		super (e);
		Session session = Session.instance();
		session.getExceptionLogger().severe(e.toString());
	}
	public ApplyException(String message) {
		super(message);
		Session session = Session.instance();
		session.getExceptionLogger().severe(message);
	}

}
