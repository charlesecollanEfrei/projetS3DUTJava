package session;

import java.util.ArrayList;

public class Logs extends ArrayList<IMessageLog> {

	public Logs() {
	}

	public ArrayList<IMessageLog> getErrors() {
		ArrayList<IMessageLog> filteredLogs = new ArrayList<IMessageLog>();
		for (IMessageLog listener : this) {
			if(listener instanceof Error ) filteredLogs.add(listener);
		}
		return filteredLogs;
	}
	
	public ArrayList<IMessageLog> getWarnings() {
		ArrayList<IMessageLog> filteredLogs = new ArrayList<IMessageLog>();
		for (IMessageLog listener : this) {
			if(listener instanceof Warning ) filteredLogs.add(listener);
		}
		return filteredLogs;
	}
	
	public ArrayList<IMessageLog> getInfos() {
		ArrayList<IMessageLog> filteredLogs = new ArrayList<IMessageLog>();
		for (IMessageLog listener : this) {
			if(listener instanceof Information ) filteredLogs.add(listener);
		}
		return filteredLogs;
	}
}
