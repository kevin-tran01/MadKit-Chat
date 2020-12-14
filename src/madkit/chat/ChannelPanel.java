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
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

@SuppressWarnings("serial")
public class ChannelPanel extends JPanel{


	private static final String HOME = "Home";

	private static final String CHATTEROLE = "CHATTER";

	private static final String COMUNITY = "Tchat";


	private String ChannelName;

	private JTextField topic;
	private JPanel center;
	private JPanel bottom;

	private JTextArea conversation;

	private JTextArea users;

	private JButton leaveChannel;

	private JButton SendButton;


	private JButton KickButton;

	private JButton BanButton;

	private JButton ChangeAdminButton;

	private JButton ChangeTopicButton;

	private JButton ChangeNicknameButton;

	private JTextField writeHere;


	/**
	 * @see madkit.chat.ChatAgent
	 */
	private ChatAgent cAgent;


	/**
	 * Return the bottom of the visual interface
	 */
	public JPanel getBottom() {
		return bottom;
	}

	/**
	 * ChannelPanel Constructor
	 *
	 * This class initializes the visual interface of a channel.
	 *
	 * Ce constructeur permet d'initialiser l'interface li��� ��� l'affichage d'un
	 * canal de discussion. Elle cr������e : Une zone d'affichage pour le sujet Une
	 * zone d'affichage pour les messages Une zone d'affichage pour les
	 * utilisateurs pr���sents dans le canal de discussion Une zone de saisie +
	 * une barre d'outils contenant les fonctions li���es au canal de discussion
	 */
	public ChannelPanel(String name, ChatAgent a) {
		cAgent = a;
		ChannelName = name;
		center = new JPanel();
		bottom = new JPanel();
		JToolBar toolbar = new JToolBar();
		toolbar.setOrientation(SwingConstants.VERTICAL);

		// Initialize the channel name visual
		topic = new JTextField();
		topic.setPreferredSize(new Dimension(620, 25));
		topic.setEditable(false);
		topic.setFont(new Font("Arial",
				Font.PLAIN, 14));
		// Initialize the users list visual
		users = new JTextArea();
		users.setPreferredSize(new Dimension(100, 300));
		users.setEditable(false);
		users.setFont(new Font("Arial",
				Font.PLAIN, 14));
		users.setMargin(new Insets(5, 5, 5, 5));
		// Initialize conversation panel
		conversation = new JTextArea();
		conversation.setPreferredSize(new Dimension(450, 300));
		conversation.setEditable(false);
		conversation.setLineWrap(true);
		conversation.setFont(new Font("Arial",
				Font.PLAIN, 14));
		conversation.setMargin(new Insets(5, 5, 5, 5));
		conversation.setText("Welcome on the channel : " + name + "\n");
		// Buttons initialization

		SendButton = new JButton("Send");
		SendButton.setMnemonic(KeyEvent.VK_ENTER);
		SendButton.addActionListener(e -> {

			String message = writeHere.getText();

			// Associating commands
			if (message.charAt(0) == '/') {
				FonctionMessage command;
				StringTokenizer cutline = new StringTokenizer(message, " ");
				String instruction = cutline.nextToken();
				switch (instruction) {
					case ("/LEAVE") -> cAgent.leaveChannel(cAgent.getName()
							+ " leave the channel ! ", ChannelName);
					case ("/JOIN") -> {
						if (cutline.countTokens() == 2) {
							cAgent.joinChannel(cutline.nextToken());
						} else {
							cAgent.joinChannel(cutline.nextToken(), cutline.nextToken());
						}
					}
					case ("/CREATE") -> {
						if (cutline.countTokens() == 3) {
							cAgent.createChannel(cutline.nextToken(), cutline.nextToken());
						} else {
							cAgent.createChannel(cutline.nextToken(), cutline.nextToken(), cutline.nextToken());
						}
					}
					case ("/LETADMIN") -> {
						if (cAgent.getListeChannel().get(ChannelName)
								.getAdminName()
								.compareTo(cAgent.getChatter().getNickname()) == 0) {
							String name1 = cutline.nextToken();
							System.out.println(name1);
							cAgent.letAdmin(name1, ChannelName);
							setDroit(false);
						} else {
							afficher("WARNING: You're not able to do that. \n");
						}
					}
					case ("/KICK"), ("/BAN") -> {
						if ((cAgent.getListeChannel().get(ChannelName)
								.getAdminName()
								.compareTo(cAgent.getChatter().getNickname()) == 0)
								&& ChannelName.compareTo(HOME) != 0) {
							if (cutline.countTokens() == 2) {
								command = new FonctionMessage(instruction,
										cutline.nextToken(), ChannelName);
								cAgent.broadcastFonctionMessage(command,
										ChannelName);
							} else {
								afficher("INFO: This command need a parameter \n");
							}
						} else {
							afficher("INFO: This command is unavailable for you /on this channel. \n");
						}
					}
					default -> afficher("INFO: Invalid command. \n");
				}
			}

			// Simple message treatment
			else {
				cAgent.broadcastMessage(message, ChannelName);
				afficher("me : " + message + "\n");
			}
			writeHere.setText("");
		});

		KickButton = new JButton();
		KickButton.setToolTipText("Kick an user");
		KickButton.setBackground(Color.white);
		KickButton.setIcon(new ImageIcon(( getClass() .getResource( "/kick.jpg"))));
		KickButton.addActionListener(
				e -> {
					if (ChannelName.compareTo(HOME) != 0) {
						String s = JOptionPane.showInputDialog(null, "Nickname : ",
								ChannelName, JOptionPane.QUESTION_MESSAGE);
						cAgent.broadcastFonctionMessage(new FonctionMessage(
								"/KICK", s, ChannelName), ChannelName);
					} else {
						JOptionPane
								.showMessageDialog(
										null,
										"You can't kick someone from the homepage.",
										"Kick", JOptionPane.INFORMATION_MESSAGE);
					}
				});

		ChangeAdminButton = new JButton();
		ChangeAdminButton.setToolTipText("Design a new admin");
		ChangeAdminButton.setBackground(Color.white);
		ChangeAdminButton.setIcon(new ImageIcon( getClass() .getResource( "/admin.jpg")));
		ChangeAdminButton.addActionListener(

				e -> {
					String s = JOptionPane.showInputDialog(null,
							"New admin nickname : ", ChannelName,
							JOptionPane.QUESTION_MESSAGE);
					cAgent.letAdmin(s, ChannelName);
				});

		ChangeTopicButton = new JButton();
		ChangeTopicButton.setToolTipText("Rename the topic");
		ChangeTopicButton.setBackground(Color.white);
		ChangeTopicButton.setIcon(new
				ImageIcon(( getClass() .getResource( "/topic.jpg"))));
		ChangeTopicButton.addActionListener(e -> {
			String topic = JOptionPane.showInputDialog(null,
					"New topic : ", ChannelName,
					JOptionPane.QUESTION_MESSAGE);
			cAgent.broadcastMessage(COMUNITY, ChannelName, CHATTEROLE,
					new FonctionMessage("/RENAME", topic, ChannelName));
			refreshTopic(topic);
		});

		ChangeNicknameButton = new JButton();
		ChangeNicknameButton.setToolTipText("Change nickname");
		ChangeNicknameButton.setBackground(Color.white);
		ChangeNicknameButton.setIcon(new
				ImageIcon(( getClass() .getResource( "/rename.jpg"))));
		ChangeNicknameButton.addActionListener(e -> {
			String nick = JOptionPane.showInputDialog(null,
					"New nickname : ", ChannelName,
					JOptionPane.QUESTION_MESSAGE);

			if (cAgent.getNickList().contains(nick)){

				JOptionPane
						.showMessageDialog(
								null,
								"This nickname is already used \n" +
										"Please use another which is not in this list : \n" +
										cAgent.getNickList().toString(),
								"Info", JOptionPane.INFORMATION_MESSAGE);

				nick = JOptionPane.showInputDialog(null, "New nickname : ", "chat",
						JOptionPane.QUESTION_MESSAGE);


				while (cAgent.getNickList().contains(nick)){

					nick = JOptionPane.showInputDialog(null, "New nickname : ", "chat",
							JOptionPane.QUESTION_MESSAGE);
				}


			}
			cAgent.changeNick(nick);
		});

		leaveChannel = new JButton();
		leaveChannel.setToolTipText("Leave channel");
		leaveChannel.setBackground(Color.white);
		leaveChannel.setIcon(new ImageIcon(( getClass() .getResource( "/leave.jpg"))));
		leaveChannel.setSize(50, 50);
		leaveChannel.addActionListener(e -> cAgent.leaveChannel(cAgent.getChatter().getNickname()
				+ " left the channel !", ChannelName));

		BanButton = new JButton();
		BanButton.setToolTipText("Ban an user");
		BanButton.setBackground(Color.white);
		BanButton.setIcon(new ImageIcon( getClass() .getResource( "/ban.jpg")));
		BanButton.addActionListener(e -> {
			if (ChannelName.compareTo(HOME) != 0) {
				String ban = JOptionPane.showInputDialog(null,
						"Nickname : ", ChannelName,
						JOptionPane.QUESTION_MESSAGE);
				FonctionMessage commande = new FonctionMessage("/BAN", ban,
						ChannelName);
				cAgent.broadcastFonctionMessage(commande, ChannelName);
			} else {
				JOptionPane
						.showMessageDialog(
								null,
								"You can't ban someone from the homepage",
								"Ban", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Initializing textfield
		writeHere = new JTextField(25);
		writeHere.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent event) {
				if(event.getKeyCode() == 10){
					String message = writeHere.getText();

					// Commands treatings
					if (message.charAt(0) == '/') {
						FonctionMessage commande;
						StringTokenizer cutline = new StringTokenizer(message, " ");
						String instruction = cutline.nextToken();
						switch (instruction) {
							case ("/LEAVE") -> cAgent.leaveChannel(cAgent.getName()
									+ " leave the channel ! ", ChannelName);
							case ("/JOIN") -> cAgent.createChannel(cutline.nextToken(), null, null);
							case ("/LETADMIN") -> {
								if (cAgent.getListeChannel().get(ChannelName)
										.getAdminName()
										.compareTo(cAgent.getChatter().getNickname()) == 0) {
									String name = cutline.nextToken();
									System.out.println(name);
									cAgent.letAdmin(name, ChannelName);
									setDroit(false);
								} else {
									afficher("INFO: You can't use this command. \n");
								}
							}
							case ("/KICK"), ("/BAN") -> {
								if ((cAgent.getListeChannel().get(ChannelName)
										.getAdminName()
										.compareTo(cAgent.getChatter().getNickname()) == 0)
										&& ChannelName.compareTo(HOME) != 0) {
									if (cutline.countTokens() == 2) {
										commande = new FonctionMessage(instruction,
												cutline.nextToken(), ChannelName);
										cAgent.broadcastFonctionMessage(commande,
												ChannelName);
									} else {
										afficher("INFO: Cette commande necessite un argument. \n");
									}
								} else {
									afficher("INFO: Cette commande n'est pas disponible pour votre role /sur ce channel. \n");
								}
							}
							default -> afficher("INFO: Commande invalide. \n");
						}
					}

					// TRAITE LES SIMPLES MESSAGES
					else {
						cAgent.broadcastMessage(message, ChannelName);
						afficher("moi : " + message + "\n");
					}
					writeHere.setText("");
				}
			}

			@Override
			public void keyReleased(KeyEvent event) {
			}

			@Override
			public void keyTyped(KeyEvent event) {
			}
		});
		// ajout des 3 composants au panel.
		this.add(topic, BorderLayout.NORTH);
		toolbar.add(leaveChannel);
		toolbar.add(KickButton);
		toolbar.add(BanButton);
		toolbar.add(ChangeAdminButton);
		toolbar.add(ChangeTopicButton);
		toolbar.add(ChangeNicknameButton);
		center.add(toolbar, BorderLayout.EAST);
		center.add(conversation,BorderLayout.CENTER);
		center.add(users);
		bottom.add(writeHere);
		bottom.add(SendButton);
		this.add(center,BorderLayout.CENTER);
		this.add(bottom,BorderLayout.PAGE_END);
	}

	/**
	 * Met ��� jour l'affichage de la liste des utilisateurs pr���sents dans le
	 * canal de discussion
	 *
	 * @param map
	 *            La liste des utilisateurs
	 */
	public void majUsers(HashMap<String, Chatter> map) {
		this.users.setText("Utilisateurs : \n\n");
		for (Entry<String, Chatter> entry : map.entrySet()) {
			Chatter valeur = entry.getValue();
			this.users.append(valeur.getNickname() + "\n");
		}
	}

	/**
	 * Affiche les boutons en fonction des droits que poss���de l'utilisateur
	 */
	public void setDroit(boolean droit) {
		KickButton.setVisible(droit);
		BanButton.setVisible(droit);
		ChangeAdminButton.setVisible(droit);
		ChangeTopicButton.setVisible(droit);
	}

	/**
	 * Affiche un message dans la zone de conversation
	 *
	 * @param s
	 *            Le message ��� afficher
	 */
	public void afficher(String s) {
		this.conversation.append(s);
	}

	/**
	 * Retourne la r���f���rence de la zone de conversation
	 *
	 * @return the conversation
	 */
	public JTextArea getConversation() {
		return conversation;
	}

	/**
	 * Actualise le sujet du canal de discussion par le nouveau sujet pass��� en
	 * param���tre
	 *
	 * @param top
	 *            nouveau sujet
	 */
	public void refreshTopic(String top) {
		topic.setText(top);
	}
}
