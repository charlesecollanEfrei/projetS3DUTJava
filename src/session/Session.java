package session;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class respect the design pattern Singleton. Only one object Session can be created
 * @author Charles Ecollan, Quentin Bresson, Hubert Mouginot
 */
public class Session {

	protected ResourceBundle resourceBundle;
	protected Locale locale;
	
	protected Logger sessionGuiLogger;
	protected Logger sessionExceptionLogger;
	
	private String[] days;
	private String[] months;

	private  static Session session = null;
	
	/**
	 * Basic constructor for Session's object
	 * Set the basic language at English
	 */
	private Session() {
		/* La langue est défini comme anglaise par défaut */
		Locale.setDefault(Locale.US);
		locale = Locale.getDefault();
		resourceBundle = ResourceBundle.getBundle("resources.resource", this.locale);
		
		sessionGuiLogger = Logger.getLogger("MyGUILogger");
		sessionGuiLogger.setLevel(Level.ALL);
		
		sessionExceptionLogger = Logger.getLogger("MyExceptionLogger");
		sessionExceptionLogger.setLevel(Level.SEVERE);
		
		days = new String[7];
		days[0] = getString("monday"); days[1] =  getString("tuesday"); days[2] =  getString("wednesday");
		days[3] = getString("thursday");   days[4] =  getString("friday"); days[5] =  getString("saturday");
		days[6] = getString("sunday"); 
		
		months = new String[12];
		months[0] = getString("january"); months[1] =  getString("february"); months[2] =  getString("march");
		months[3] = getString("april");   months[4] =  getString("may");      months[5] =  getString("june");
		months[6] = getString("july"); months[7] =  getString("august"); months[8] =  getString("september");
		months[9] = getString("october"); months[10] =  getString("november"); months[11] =  getString("december");
		
	}
	
	/**
	 * Property of Singleton's class. Return the instance of Session or create one if she doesn't exist yet
	 * @return
	 */
	public static Session instance() {
		if (session == null) {			
			session = new Session();
		}
		return session;
	}
	
	public Logger getGUILogger() {
		return sessionGuiLogger;
	}
	public Logger getExceptionLogger() {
		return sessionExceptionLogger;
	}
	
	/**
	 * Change the locale, and therefore change the language of the application
	 * @param locale
	 */
	public void setLocale(Locale locale){
		this.locale = locale;
		Locale.setDefault(this.locale);
		resourceBundle= ResourceBundle.getBundle("resources.resource", this.locale);
	}
	
	public String getString(String key) {
		return resourceBundle.getString(key);
	}
	
	public String[] getDays() {
		return days;
	}
	public String[] getMonths() {
		return months;
	}
	
}
