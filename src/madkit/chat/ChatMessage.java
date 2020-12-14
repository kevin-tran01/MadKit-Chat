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

import madkit.kernel.Message;

/**
 * Classe mod?lisant un ChatMessage. Ce type de message est utilis? pour r?alis?
 * toutes les communications entre les diff?rents utilisateurs du chat
 * lorsqu'ils s'expriment. Il englobe le contenu r?dig? par l'utilisateur et le
 * rend lisible des autres utilisateurs.
 *
 * @author Max
 * @version 1.9
 */
@SuppressWarnings("serial")
public class ChatMessage extends Message {

	/**
	 * Le message r?dig? par l'utilisateur
	 */
	private String message;
	/**
	 * Le nom de l'utilisateur qui a ?crit ce message
	 */
	private String name;
	/**
	 * Le nom du canal de discussion dans lequel le message doit ?tre envoy?
	 */
	private String channel;

	/**
	 * Retourne le contenu du message que l'utilisateur souhaite envoyer
	 *
	 * @return message Le message ? envoyer
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Retourne le nom de l'utilisateur qui a ?crit le message
	 *
	 * @return name Le nom de l'utilisateur qui a ?crit le message
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retourn le nom du canal de discussion dans lequel doit ?tre envoy? le
	 * message.
	 *
	 * @return channel le nom du canal de discussion.
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * Constructeur ChatMessage
	 *
	 * @param s
	 *            Contenu du message ? envoyer
	 * @param name
	 *            Nom de l'utilisateur qui a r?dig? ce message
	 * @param channel
	 *            Nom du canal de discussion dans lequel le message doit ?tre
	 *            envoy?
	 */
	public ChatMessage(String s, String name, String channel) {
		this.message = s;
		this.name = name;
		this.channel = channel;
	}

}
