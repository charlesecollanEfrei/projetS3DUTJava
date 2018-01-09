package session;

public interface IMessageLog {
	
	void setMessage(String message);
	String getMessage();
	void addListener(IMessageLogListener listener);
	IMessageLogListener[] getpplicationLogListeners();

}
