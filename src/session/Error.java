package session;

/**
 * Handle the error message
 * @author Charles Ecollan, Quentin Bresson, Hubert Mouginot
 *
 */
public class Error extends MessageLog{

	/**
	 * Show an error message, depending of the parameter
	 * @param message
	 */
	@Override
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message = message;
		Session.instance().getGUILogger().severe(this.message);
		super.fireMessage("[ERROR]", this.message);
		
	}

}
