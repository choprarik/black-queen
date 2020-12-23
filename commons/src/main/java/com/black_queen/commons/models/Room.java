/**
 * 
 */
package com.black_queen.commons.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

/**
 * @author rakshit.chopra
 *
 */
@Document(collection = "room")
public class Room {
	
	@Id
	private String id;
	@NonNull
	private String hostId;
	private List<String> members = new ArrayList<String>(6);
	private String status;
	private String gameId;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the hostId
	 */
	public String getHostId() {
		return hostId;
	}
	/**
	 * @param hostId the hostId to set
	 */
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	/**
	 * @return the members
	 */
	public List<String> getMembers() {
		return members;
	}
	/**
	 * @param members the members to set
	 */
	public void setMembers(List<String> members) {
		this.members = members;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the gameId
	 */
	public String getGameId() {
		return gameId;
	}
	/**
	 * @param gameId the gameId to set
	 */
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

}
