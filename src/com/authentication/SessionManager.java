
// this is a singleton pattern, It is responsible for creating sessions
// when user logs in using OAuth then this class creates a session and stores that session token and user object in a hashmap
// The application can use this to validate the session by using sessions HashMap
// When user logs out then his session will be deleted.

package com.authentication;
import java.util.*;
import com.userregistration.User;



public class SessionManager {
	private static SessionManager instance;
	
	public Map<String, User> getSessions() {
		return sessions;
	}

	public void setSessions(Map<String, User> sessions) {
		this.sessions = sessions;
	}
	

	private Map<String, User> sessions = new HashMap<>();
	
	
	private SessionManager() {};
	
	public static SessionManager startSession() {
		if(instance==null) {
			instance =new SessionManager();
		}
		
		return instance;
	}
	
	public void endSession() {
		instance = null;
	}
	
	public void createSession(String token, User user) {
		sessions.put(token, user);
	}
	
	public boolean isValidSession(String token) {
		return sessions.containsKey(token);
	}
	
	public static boolean isSessionActive() {
		if(instance ==null)return false;
		else return true;
	}
}