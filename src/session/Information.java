package session;

public class Information extends MessageLog {

	@Override
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message = message;
		Session.instance().getGUILogger().info(this.message);
		super.fireMessage("[INFO]", this.message);
	}

}
