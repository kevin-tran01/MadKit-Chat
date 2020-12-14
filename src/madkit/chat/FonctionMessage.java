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

import madkit.message.ActMessage;

/**
 * Classe repr?sentant tous les messages non visible de l'utilisateur permettant
 * de g?rer la majorit? des fonctions pr?sentes sur le chat
 *
 * @author Bistour/Clerix
 * @version 1.9
 */
@SuppressWarnings("serial")
public class FonctionMessage extends ActMessage {

	/**
	 * Le nom du canal de discussion dans lequel la commande a lieu.
	 */
	private String channel;

	/**
	 * Constructeur FonctionMessage avec un objet.
	 *
	 * @param actiontype La commande ? r?aliser.
	 * @param o Objet utile ? la r?alisation de la commande, peut ?tre nul.
	 * @param chan Le nom du canal de discussion dans lequel la commande a lieu
	 *
	 * @see ActMessage
	 */
	public FonctionMessage(String actiontype, Object o, String chan) {
		super(actiontype, o);
		this.channel = chan;
	}

	/**
	 * Constructeur FonctionMessage avec une cha?ne de caract?re.
	 *
	 * @param actiontype La commande ? r?aliser.
	 * @param s String utile ? la r?alisation de la commande, peut ?tre nul. Par exemple utilis? pour le fonction RENAME
	 * @param chan Le nom du canal de discussion dans lequel la commande a lieu
	 *
	 * @see ActMessage
	 */
	public FonctionMessage(String actiontype, String s, String chan) {
		super(actiontype, s);
		this.channel = chan;
	}

	/**
	 * Retourne le nom du canal de discussion dans lequel la commande a lieu
	 *
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

}
