package session;

import java.util.ArrayList;

public abstract class MessageLog implements IMessageLog {

	protected String message;
	protected ArrayList<IMessageLogListener> listeners;

	public MessageLog() {
		message = null;
		listeners = new ArrayList<IMessageLogListener>();
	}

	public abstract void setMessage(String message);

	public void addListener(IMessageLogListener l) {

		this.listeners.add(l);

	}

	public String getMessage() {

		return this.message;

	}
	
	public IMessageLogListener[] getpplicationLogListeners() {

		int i = 0;
		IMessageLogListener[] tab = new IMessageLogListener[(this.listeners).size()];
		for (IMessageLogListener ia : this.listeners) {
			
			tab[i] = ia;
			i++;
			
		}
		
		return tab;

	}


	/** Listener action */
	protected void fireMessage(String level, String message) {
		for (IMessageLogListener listener_i : listeners) {
			listener_i.newMessage(level, message);
		}
	}

	
}