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

import madkit.kernel.AgentAddress;

/**
 *
 * Class representing a user by his Name, his adress on the network, and his role
 *
 *
 * @author Bistour/Clerix
 * @version 1.9
 */
public class Chatter implements Serializable{

	/**
	 * Angent's adress on the network
	 */
	private AgentAddress adrChatter;
	/**
	 * User's name on the chat
	 */
	private String nickname;
	/**
	 * User's role
	 */
	private String role;

	/**
	 * Chatter's builder
	 *
	 * @param nickname2
	 *            User's name on the chat
	 * @param adrChatter2
	 *            Angent's adress on the network
	 * @param role2
	 *            User's role
	 */
	public Chatter(String nickname2, AgentAddress adrChatter2, String role2) {
		adrChatter = adrChatter2;
		nickname = nickname2;
		role = role2;
	}



	/**
	 * Empty builder
	 */
	public Chatter() {
		adrChatter = null;
		nickname = null;
		role = null;
	}


	/**
	 * Return chatter's adress on the network
	 *
	 * @return adrChatter
	 */
	public AgentAddress getAdrChatter() {
		return adrChatter;
	}

	/**
	 * Changing chatter's adress by his new one
	 *
	 * @param a
	 *            New adress
	 */
	public void setAdrChatter(AgentAddress a) {
		this.adrChatter = a;
	}

	/**
	 * Return chatter's name
	 * @return
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Update the user's name by a new user name
	 *
	 * @param n User's name
	 */
	public void setNickname(String n) {
		this.nickname = n;
	}

	/**
	 * Return chatter's role
	 *
	 * @return Chatter's role
	 */
	public String getRole() {
		return this.role;
	}

	/**
	 * Changes chater's role
	 *
	 * @param r New role
	 */
	public void setRole(String r) {
		this.role = r;
	}

	/**
	 * Transforms the user into a clone user
	 *
	 * @param clone
	 *            Chatter to copy
	 */
	public void setChatter(Chatter clone) {
		adrChatter = clone.adrChatter;
		nickname = clone.nickname;
		role = clone.role;
	}
}
