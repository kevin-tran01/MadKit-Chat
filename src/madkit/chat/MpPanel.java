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

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Classe permettant de créer l'interface graphique inh�rente � la communication
 * entre deux utilisateurs par message priv�s.
 *
 * @author Bistour/Clerix
 * @version 1.9
 */
@SuppressWarnings("serial")
public class MpPanel extends JPanel {

	/**
	 * Personne � laquelle on adresse des messages priv�s
	 */
	private Chatter destinataire;
	private JPanel bottom;
	/**
	 * Zone d'affichage des messages
	 */
	private JTextArea conversation;
	/**
	 * Bouton d'envoi de message
	 */
	private JButton boutonEnvoyer;
	/**
	 * Champ de saisie de texte
	 */
	private JTextField saisie;
	private ChatAgent cAgent;
	/**
	 * Champ indiquant si l'on se trouve seul dans cette fen�tre de discussion
	 */
	private boolean seul;

	/**
	 * Modifie la valeur du champ seul
	 *
	 * @param val
	 *            nouvelle valeur
	 */
	public void setSeul(boolean val) {
		seul = val;
	}

	/**
	 * Retourne la r�f�rence du JPanel bottom
	 *
	 * @return the bottom
	 */
	public JPanel getBottom() {
		return bottom;
	}

	/**
	 * Retourne la r�f�rence du bouton envoyer
	 *
	 * @return the boutonEnvoyer
	 */
	public JButton getBoutonEnvoyer() {
		return boutonEnvoyer;
	}

	/**
	 * Retourne la r�f�rence du camp de saisie de texte
	 *
	 * @return the saisie
	 */
	public JTextField getSaisie() {
		return saisie;
	}

	/**
	 * Constructeur MpPanel
	 *
	 * @param dest
	 *            Nom de la personne � laquelle on s'adresse
	 * @param a
	 *            Agent personnel qui permet de r�aliser les communications avec
	 *            les autres utilisateurs.
	 */
	public MpPanel(Chatter dest, ChatAgent a) {
		seul = false;
		cAgent = a;
		destinataire = dest;
		JPanel centre = new JPanel();
		bottom = new JPanel();

		// Initialisation de la zone de conversation
		conversation = new JTextArea();
		conversation.setPreferredSize(new Dimension(350, 300));
		conversation.setEditable(false);
		conversation.setMargin(new Insets(5, 5, 5, 5));
		conversation.setText("Welcome to the private channel with : "
				+ dest.getNickname() + "\n");
		// Initialisation des boutons
		boutonEnvoyer = new JButton();
		boutonEnvoyer.setMnemonic(KeyEvent.VK_ENTER);
		boutonEnvoyer.addActionListener(e -> {
			String message = saisie.getText();
			if (!seul) {
				if (message.compareTo("/LEAVE") == 0) {
					cAgent.leaveMp(cAgent.getChatter().getNickname()
									+ " quitte le channel la conversation priv�e",
							destinataire, seul);
				} else {
					cAgent.sendMessage(destinataire.getAdrChatter(),
							new ChatMessage(message, cAgent.getChatter()
									.getNickname(), cAgent.getChatter()
									.getNickname()));
					afficher("moi : " + message + "\n");
				}
			} else {
				if (message.compareTo("/LEAVE") == 0) {
					cAgent.leaveMp(cAgent.getChatter().getNickname()
									+ " quitte le channel la conversation priv�e",
							destinataire, seul);
				}
				afficher("INFO : Vous �tes tout seul sur le chanel \n");
			}
			saisie.setText("");
		});
		boutonEnvoyer.setText("Envoyer");
		boutonEnvoyer.setMargin(new Insets(2, 2, 2, 2));
		// Initialisation du champ de saisie
		saisie = new JTextField(25);
		// ajout des 3 composants au panel.
		centre.add(conversation);
		bottom.add(saisie);
		bottom.add(boutonEnvoyer);
		this.add(centre);
		this.add(bottom);
	}

	/**
	 * M�thode qui permet d'afficher un message dans la fen�tre de discussion du
	 * canal de discussion
	 *
	 * @param message Le message � afficher
	 */
	public void afficher(String message) {
		this.conversation.append(message);
	}

	/**
	 * Retourne la r�f�rence du composant permettant l'affichage des messages.
	 *
	 * @return the conversation
	 */
	public JTextArea getConversation() {
		return conversation;
	}

}
