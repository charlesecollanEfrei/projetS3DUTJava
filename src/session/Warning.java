package session;

public class Warning extends MessageLog {
	
	public Warning() {
		super();
	}
	
	@Override
	public void setMessage(String message) {
		this.message = message;
		Session.instance().getGUILogger().warning(this.message);
		super.fireMessage("[WARNING]", this.message);
	}

}
