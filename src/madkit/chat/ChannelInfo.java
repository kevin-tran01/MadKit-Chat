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

/**
 * This class helps us getting the informations of a channel when it's created
 * or when someone tries to join it
 *
 * @author Bistour/Clerix
 * @version 1.9
 */
public class ChannelInfo {

	private String name, password, topic;

	/**
	 * null Constructor
	 */
	public ChannelInfo() {
		name = null;
		password = null;
		topic = null;
	}

	/**
	 * Constructor with value
	 */
	public ChannelInfo(String name, String password, String topic) {
		this.name = name;
		this.password = password;
		this.topic = topic;
	}

	/**
	 * channel name Getter
	 */
	public String getName() {
		return name;
	}

	/**
	 * password Getter
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * topic Getter
	 */
	public String getTopic() {
		return topic;
	}
}
