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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

/**
 * Classe permettant de cr�er l'interface graphique du chat
 *
 * @author Bistour/Clerix
 * @version 1.9
 */
@SuppressWarnings("serial")
public class ChatFrame extends JFrame implements ActionListener {

	/**
	 * Agent du chat
	 *
	 * @see madkit.chat.ChatAgent
	 */
	private ChatAgent agent;
	/**
	 * Barre d'onglets Channel
	 */
	private JTabbedPane onglet;
	/**
	 * Barre d'outil permettant de cr�er/rejoindre un channel, voir la liste des
	 * channels, envoyer des messages priv�s
	 */
	private JToolBar toolbar;

	/**
	 * Retourne la r�f�rence de l'agent
	 *
	 * @return agent
	 * @see madkit.chat.ChatAgent
	 */
	public ChatAgent getAgent() {
		return agent;
	}

	/**
	 * Constructeur ChatFrame
	 *
	 * Initialise l'interface graphique du chat en cr�ant la barre d'outils et
	 * la barre d'onglets.
	 *
	 */
	public ChatFrame(ChatAgent a) {

		super("Chat Madkit V 5.3.1");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640, 500);
		this.setResizable(false);

		agent = a;
		onglet = new JTabbedPane();
		toolbar = new JToolBar();

		JButton addChannel = new JButton();
		addChannel.setBackground(Color.white);
		addChannel.setIcon(new ImageIcon(( getClass() .getResource( "/ajouter.jpg"))));
		addChannel.setSize(20, 20);
		addChannel.addActionListener(e -> {
			ChannelDialog askInfo = new ChannelDialog(null,
					"Cr�er un channel", true, 0);
			ChannelInfo info = askInfo.showDialog();
			if (!info.getName().isEmpty()) {
				agent.createChannel(info.getName(), info.getTopic(),
						info.getPassword());
			} else {
				System.out.println("Le nom de channel est null");
			}
		});

		JButton joinChannel = new JButton();
		joinChannel.setBackground(Color.white);
		joinChannel.setIcon(new ImageIcon(( getClass() .getResource( "/join.jpg"))));
		joinChannel.setSize(20, 20);
		joinChannel.addActionListener(

				e -> {
					ChannelDialog askInfo = new ChannelDialog(null,
							"Rejoindre un channel", true, 1);
					ChannelInfo info = askInfo.showDialog();
					agent.joinChannel(info.getName(), info.getPassword());
				});

		JButton MP = new JButton();
		MP.setBackground(Color.white);
		MP.setIcon(new ImageIcon(( getClass() .getResource( "/mp.jpg"))));

		MP.addActionListener(e -> {
			String destinataire = JOptionPane.showInputDialog(null,
					"Destinataire : ", "tchat",
					JOptionPane.QUESTION_MESSAGE);

			System.out.println(agent.getListeChannel().toString());

			Chatter c = new Chatter(destinataire, agent.getListeChannel()
					.get("Home").getListeChatter().get(destinataire)
					.getAdrChatter(), "CHATTER");
			agent.launchMpPanel(c);
			agent.pingMp(c);
		});

		JButton listeChannel = new JButton();
		listeChannel.setBackground(Color.white);
		listeChannel.setIcon(new ImageIcon(( getClass() .getResource( "/chan.jpg"))));
		listeChannel.setSize(50, 50);
		listeChannel.addActionListener(e -> {
			String title = "Liste des channels disponibles";
			JOptionPane.showMessageDialog(null,
					agent.affChannel(), title,
					JOptionPane.INFORMATION_MESSAGE);

		});

		toolbar.add(addChannel);
		toolbar.add(joinChannel);
		toolbar.add(MP);
		toolbar.add(listeChannel);
		toolbar.setRollover(true);

		getContentPane().add(onglet, BorderLayout.CENTER);
		getContentPane().add(toolbar, BorderLayout.PAGE_START);
	}

	/**
	 * Ajoute � la barre d'onglet un onglet correspondant � un canal de
	 * discussion
	 *
	 * @param s
	 *            Le nom du canal de discussion
	 * @param droit
	 *            Droit d'administrateur ou de chatter
	 */
	public void addOngletChan(String s, boolean droit) {
		ChannelPanel c = new ChannelPanel(s, agent);
		c.setDroit(droit);
		onglet.addTab(s, c);
	}

	/**
	 * Retourne la r�f�rence de la barre d'onglet
	 *
	 * @return onglet la barre d'onglet
	 */
	public JTabbedPane getOnglet() {
		return onglet;
	}

	public void actionPerformed(ActionEvent e) {
	}

	/**
	 * Ajoute � la barre d'onglet un onglet correspondant � une conversation
	 * priv�e avec un chatter d�sign� dest
	 *
	 */
	public void addOngletMP(Chatter dest, ChatAgent chatAgent) {
		onglet.addTab(dest.getNickname(), new MpPanel(dest, agent));
	}

}