package madkit.chat;

/*
 * Copyright 2012-2013 Bistour Guillaume - Clerix Maxime
 *
 * This file is part of MaDKit-Chat.
 *
 * MaDKit-tutorials is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaDKit-Chat is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MaDKit-Chat. If not, see <http://www.gnu.org/licenses/>.
 */

import java.io.Serializable;
import java.util.HashMap;

/**
 * Class modeling a channel
 *
 * @author Bistour/Clerix
 * @version 1.9
 */
public class Channel implements Serializable{

	/**
	 * Discussion channel's name
	 */
	private String name;
	/**
	 * Discussion channel's topic
	 */
	private String topic;
	/**
	 * Admin name's
	 */
	private String AdminName;
	/**
	 * Password to access this channel
	 */
	private String password;
	/**
	 * List of user in this channel
	 * Using hashmap to find an user faster using his name
	 */
	private HashMap<String, Chatter> listeChatter;

	/**
	 * Constructor without password
	 */
	public Channel(String name, String topic, String admin) {
		this.name = name;
		this.topic = topic;
		this.AdminName = admin;
		this.password = null;
		listeChatter = new HashMap<>();
	}

	/**
	 * Constructor with password
	 */
	public Channel(String name, String topic, String admin, String password) {
		this.name = name;
		this.topic = topic;
		this.AdminName = admin;
		this.password = password;
		listeChatter = new HashMap<>();
	}

	/**
	 * Return channel's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * Modify the actual topic by the one in parameter
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * Return admin's name
	 */
	public String getAdminName() {
		return AdminName;
	}

	/**
	 * Change the admin with the one in parameter
	 */
	public void setAdminName(String adminName) {
		AdminName = adminName;
	}

	/**
	 * Return the users list
	 */
	public HashMap<String, Chatter> getListeChatter() {
		return listeChatter;
	}

	/**
	 * Modify the users list with the one in parameter
	 */
	public void setListeChatter(HashMap<String, Chatter> listeChatter) {
		this.listeChatter = listeChatter;
	}

	/**
	 * Return password
	 */
	public String getPassword() {
		return password;
	}
}