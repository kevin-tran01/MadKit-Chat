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

import madkit.kernel.*;
import madkit.kernel.Madkit.BooleanOption;
import madkit.kernel.Madkit.LevelOption;
import madkit.kernel.Madkit.Option;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

/**
 * SuperClass permettant de communiquer avec tous les autres utilisateur du chat
 * Madkit.
 *
 * @author Bistour/Clerix
 * @version 1.9
 */
@SuppressWarnings("serial")
public class ChatAgent extends Agent {

	/**
	 * Constante correspondant au canal de discussion d'accueil
	 */
	private static final String HOME = "Home";
	/**
	 * Constante correspondant au r���le chatter
	 */
	private static final String CHATTEROLE = "CHATTER";
	/**
	 * Constante correspondant au r���le administrateur
	 */
	private static final String ADMINROLE = "ADMIN";
	/**
	 * Constante correspondant au nom de la communaut��� dans laquelle ���volue le
	 * chatter
	 */
	private static final String COMUNITY = "Tchat";
	/**
	 * Constante correspondant ��� la commande permettant d'envoyer un MP
	 */
	private static final String WHISP = "/WHISP";
	/**
	 * Constante correspondant ��� la commande permettant de supprimer un
	 * utilisateur d'une liste d'utilisateur
	 */
	private static final String REMOVECHATTER = "/REMOVECHATTER";
	/**
	 * Constante correspondant ��� la commande permettant de renommer un
	 * utilisateur d'une liste d'utilisateur
	 */
	private static final String RENAMECHATTER = "/RENAMECHATTER";
	/**
	 * Constante correspondant ��� la commande permettant d'envoyer ses
	 * informations afin qu'elles soient partag���es avec les autres utilisateurs
	 */
	private static final String SENDCHATTER = "/SENDCHATTER";
	/**
	 * Constante correspondant ��� la commande permettant de r���cup���rer une liste
	 * d'utilisateurs
	 */
	private static final String REQUESTNICK = "/REQUESTNICK";
	/**
	 * Constante correspondant ��� la commande permettant de r���cup���rer la liste de tous les nicknames utilisés
	 *
	 */
	private static final String GETNICKLIST = "/GETNICKLIST";
	/**
	 * Constante correspondant ��� la commande permettant de devenir
	 * administrateur d'un canal de discussion
	 */
	private static final String GETADMIN = "/GETADMIN";
	/**
	 * Constante correspondant ��� la commande permettant de quitter un canal de
	 * discussion
	 */
	private static final String LEAVE = "/LEAVE";
	/**
	 * Constante correspondant ��� la commande permettant de bannir quelqu'un du
	 * canal de discussion
	 */
	private static final String BAN = "/BAN";
	/**
	 * Constante correspondant ��� la commande permettant de r���cup���rer une liste
	 * noir
	 */
	private static final String REQUESTBLACK = "/REQUESTBLACK";
	/**
	 * Constante correspondant ��� la commande permettant d'expulser quelqu'un
	 * d'un canal de discussion
	 */
	private static final String KICK = "/KICK";
	/**
	 * Constante correspondant ��� la commande permettant de savoir si l'on est
	 * seul dans un canal de discussion
	 */
	private static final String ALONE = "/ALONE";
	/**
	 * Constante correspondant ��� la commande permettant de renommer un sujet
	 * dans un canal de discussion
	 */
	private static final String RENAME = "/RENAME";
	/**
	 * Constante correspondant ��� la commande permettant d'obtenir la liste de
	 * tous les canaux de discussion
	 */
	private static final String GETCHANNEL = "/GETCHANNEL";
	/**
	 * Constante correspondant ��� la commande permettant mettre ��� jour la liste
	 * de ses canaux de discussion
	 */
	private static final String MAJCHANNEL = "/MAJCHANNEL";

	// Attributs
	/**
	 * Informations relatives ��� l'utilisateur
	 *
	 * @see madkit.chat.Chatter
	 */
	private Chatter chatter;
	/**
	 * Liste de tous les channels ���xistants sur le chat
	 */
	private HashMap<String, Channel> listeChannel;
	/**
	 * Liste des listes noires de tous les canal de discussion
	 */
	private HashMap<String, HashMap<KernelAddress, String>> blackList;
	/**
	 * Liste des nicknames déjà utilisés
	 */
	private ArrayList<String> nickList;

	/**
	 * Interface graphique
	 */
	private ChatFrame window;

	/**
	 * Ajoute un canal de discussion ��� la liste des canaux de discussion
	 *
	 * @param c
	 *            Le canal ��� ajouter
	 */
	public void addChannel(Channel c) {
		this.listeChannel.put(c.getName(), c);
	}

	/**
	 * Retourne une hashmap contenant les combinaisons Nom/Channel de tous les
	 * canaux ���xistants
	 *
	 * @return listeChannel liste des canaux de discussion
	 */
	public HashMap<String, Channel> getListeChannel() {
		return listeChannel;
	}

	/**
	 * Remplace la liste des canaux de discussions par une liste actualis��� plus
	 * r���cement
	 *
	 * @param listeChannel
	 *            the listeChannel to set
	 */
	public void setListeChannel(HashMap<String, Channel> listeChannel) {
		this.listeChannel = listeChannel;
	}
	/**
	 * Retourne la BlackList
	 *
	 * @return blacklist
	 */
	public HashMap<String, HashMap<KernelAddress, String>> getBlackList() {
		return blackList;
	}

	/**
	 * Modifie la blackList par une blackList pass���e en param���tre
	 *
	 * @param blackList Nouvelle blacklist
	 */
	public void setBlackList(
			HashMap<String, HashMap<KernelAddress, String>> blackList) {
		this.blackList = blackList;
	}

	/**
	 * @return nicklist la liste de tous les nicknames utilisés
	 */
	public ArrayList<String> getNickList() {
		return nickList;
	}

	/**
	 * Retourne l'objet Chatter repr���sentant les informations de l'utilisateur
	 *
	 * @return chatter
	 */
	public Chatter getChatter() {
		return chatter;
	}

	/**
	 * Retourne la r���f���rence de l'interface graphique
	 *
	 * @return fenetre
	 */
	public ChatFrame getChat() {
		return window;
	}

	/**
	 * Premi���re m���thode ex���cut��� par l'agent. Cette m���thode initialise tous les
	 * param���tres du chat, ainsi que son interface graphique.
	 *
	 * @see madkit.kernel.AbstractAgent#activate()
	 */
	public void activate() {

		// Construction des attributs du chatter
		getLogger().setLevel(Level.FINEST);
		chatter = new Chatter();
		listeChannel = new HashMap<>();
		blackList = new HashMap<>();
		this.nickList= new ArrayList<>();

		// Demande de peudonyme et initialisation du Chatter
		String s = JOptionPane.showInputDialog(null, "Nickname : ", "Chat",
				JOptionPane.QUESTION_MESSAGE);
		chatter.setNickname(s);
		chatter.setRole(CHATTEROLE);

		// Initialisation de l'interface graphique
		window = new ChatFrame(this);
		window.setVisible(true);


		// Acces au salon d'accueil
		createChannel(HOME, "Salon d'accueil");
	}

	/**
	 * M���thode principale de l'agent L'agent attend l'arriv��� d'un nouveau
	 * message et le traite et cela jusqu'��� ce qu'il d���cide de quitter le chat
	 *
	 * @see madkit.kernel.Agent#live()
	 */
	public void live() {

		while (true) {
			Message m = waitNextMessage();
			traiterMessage(m);
		}
	}

	/**
	 * M���thode ex���cut��� ��� la fermeture de l'agent Fermeture de l'interface
	 * graphique.
	 *
	 * @see madkit.kernel.AbstractAgent#end()
	 */
	public void end() {
		window.dispose();
		this.killAgent(this);
	}

	/**
	 * Lancement de l'application chat
	 */
	public static void main(String[] args) {
		// Parametrage pour le lancement de l'a
		String[] argss = {
				LevelOption.networkLogLevel.toString(),
				Level.FINE.toString(), BooleanOption.network.toString(),
//				LevelOption.kernelLogLevel.toString(),
//				Level.ALL.toString(), BooleanOption.network.toString(),
				Option.launchAgents.toString(),
				ChatAgent.class.getName() + ",true,1" };

		Madkit.main(argss);
	}

	/**
	 * Cette m���thode permet de traiter tous les messages re���us par l'agent. Ces
	 * messages sont divis���s en deux cat���gories : les ChatMessage et les
	 * FonctionMessage. Les ChatMessage sont des messages simples contenant
	 * uniquement une cha���ne de caract���re et le nom d'un canal de discussion ce
	 * qui permet de r���aliser l'affichage rapidement dans le canal donn���. Les
	 * FonctionMessage contiennent un actiontype qui d���termine la commande ���
	 * r���aliser, un objet d���pendant de la fonction ��� r���aliser et le nom du canal
	 * dans lequel r���alis��� cette commande.
	 *
	 * @param m
	 *            Le message ��� traiter
	 */
	@SuppressWarnings("unchecked")

	public void traiterMessage(Message m) {

		if (m instanceof ChatMessage) {
			String destinataire = ((ChatMessage) m).getName();
			String channel = ((ChatMessage) m).getChannel();
			String message = ((ChatMessage) m).getMessage();
			afficherChatmsg(channel, destinataire, message);
		}
		if (m instanceof FonctionMessage) {
			String action = ((FonctionMessage) m).getAction();
			String channel = ((FonctionMessage) m).getChannel();
			Object obj = ((FonctionMessage) m).getObject();
			switch (action) {
				case KICK -> {
					if (this.chatter.getNickname().compareTo(
							((FonctionMessage) m).getContent()) == 0) {
						getLogger().info((String) obj);
						this.kickChatter(channel);
					}
				}
				case BAN -> {
					if (this.chatter.getNickname().compareTo((String) obj) == 0) {
						if (!(this.getBlackList().containsKey(channel))) {
							this.blackList.put(channel, null);
						}
						this.getBlackList()
								.get(channel)
								.put(this.getKernelAddress(),
										this.chatter.getNickname());
						majBlack(channel);
						this.leaveChannel(chatter.getNickname() + " a ete banni !",
								channel);
					}
				}
				case REQUESTBLACK -> this.setBlackList((HashMap<String, HashMap<KernelAddress, String>>) obj);
				case LEAVE -> this.leaveChannel(chatter.getNickname()
						+ " a quitte le channel !", channel);
				case GETADMIN -> {
					leaveChannelKeep(channel);
					joinChannelKeep(channel, ADMINROLE);
					this.listeChannel.get(channel).setAdminName(
							this.chatter.getNickname());
					int index = window.getOnglet().indexOfTab(channel);
					ChannelPanel comp = (ChannelPanel) window.getOnglet()
							.getComponentAt(index);
					comp.setDroit(true);
				}
				case REQUESTNICK -> {
					this.listeChannel.get(channel).setListeChatter(
							(HashMap<String, Chatter>) obj);
					this.updateUsersInChan((HashMap<String, Chatter>) obj, channel);
				}
				case SENDCHATTER -> {
					this.majChatter(m, channel);
					sendMessage(((Chatter) obj).getAdrChatter(),
							new FonctionMessage(REQUESTBLACK, this.getBlackList(),
									null));
					getLogger().info("topic : "
							+ this.listeChannel.get(channel).getTopic());
					this.broadcastMessage(
							COMUNITY,
							channel,
							CHATTEROLE,
							new FonctionMessage("/RENAME", this.listeChannel.get(
									channel).getTopic(), channel));
				}
				case REMOVECHATTER -> this.majChatter(m, channel);
				case WHISP -> this.launchMpPanel(obj);
				case ALONE -> {
					afficherChatmsg(channel, "INFO", (String) obj);
					int index = window.getOnglet().indexOfTab(channel);
					Component comp = this.window.getOnglet().getComponentAt(index);
					if (comp instanceof MpPanel) {
						MpPanel panel = (MpPanel) comp;
						panel.setSeul(true);
					}
				}
				case MAJCHANNEL -> this.addChannel((Channel) obj);
				case GETCHANNEL -> sendReply(m, new FonctionMessage(MAJCHANNEL, this.listeChannel,
						HOME));
				case GETNICKLIST -> sendReply(m, new FonctionMessage(null, this.nickList,
						HOME));
				case RENAME -> renameTopic(channel, ((FonctionMessage) m).getContent());
			}
		}

	}

	/**
	 * M���thode permettant de quitter le r���le d'administrateur et de l'attribu��� ���
	 * une autre personne
	 */
	public void letAdmin(String adminSuivant, String channel) {
		int index = window.getOnglet().indexOfTab(channel);
		ChannelPanel comp = (ChannelPanel) window.getOnglet().getComponentAt(
				index);
		comp.setDroit(false);

		sendMessage(
				this.listeChannel.get(channel).getListeChatter()
						.get(adminSuivant).getAdrChatter(),
				new FonctionMessage("/GETADMIN", this.getChatter()
						.getNickname(), channel));

		this.pause(1000);
		leaveChannelKeep(channel);
		this.pause(1000);
		joinChannelKeep(channel, CHATTEROLE);
	}

	/**
	 * Cr������ un onglet Message priv���
	 */

	public void launchMpPanel(Object obj) {
		window.addOngletMP((Chatter) obj, this);
	}


	/**
	 * Pr���viens un agent que l'on veut rentrer en communication avec lui
	 */
	public void pingMp(Chatter dest) {
		sendMessage(dest.getAdrChatter(), new FonctionMessage(WHISP,
				this.chatter, null));
	}

	/**
	 * Met ��� jour la liste des utilisateurs dans un canal de discussion
	 */
	private void updateUsersInChan(HashMap<String, Chatter> map, String channel) {
		int index = window.getOnglet().indexOfTab(channel);
		Component comp = window.getOnglet().getComponentAt(index);
		if (comp != null) {
			if (comp instanceof ChannelPanel) {
				ChannelPanel panel = (ChannelPanel) comp;
				panel.majUsers(map);
			}
		}
	}

	/**
	 * Cr���er un canal de discussion n'ayant pas de mot de passe
	 *
	 * @param nameChannel
	 *            Le nom du canal de discussion
	 * @param topic
	 *            Le sujet du canal de discussion
	 */
	public void createChannel(String nameChannel, String topic) {

		if (createGroupIfAbsent(COMUNITY, nameChannel, true)) {
			// Je rejoins le role ADMIN
			requestRole(COMUNITY, nameChannel, ADMINROLE);
			chatter.setAdrChatter(this.getAgentAddressIn(COMUNITY, nameChannel,
					ADMINROLE));
			chatter.setRole(ADMINROLE);

			// Je cr������ l'objet channel et je le diffuse ��� la communaut���
			Channel channel = new Channel(nameChannel, topic,
					chatter.getNickname(), null);
			broadcastFonctionMessage(new FonctionMessage(MAJCHANNEL, channel,
					nameChannel), HOME);
			this.addChannel(channel);
			HashMap<KernelAddress, String> liste = new HashMap<>();
			this.blackList.put(nameChannel, liste);

			this.nickList.add(this.chatter.getNickname());

			// je m'ajoute ��� listeChatter du channel
			Chatter c = new Chatter(chatter.getNickname(),
					chatter.getAdrChatter(), ADMINROLE);

			listeChannel.get(nameChannel).getListeChatter()
					.put(c.getNickname(), c);

			// Cr���ation de l'onglet
			window.addOngletChan(nameChannel, true);
			renameTopic(nameChannel, topic);

		}
		// Cas ou le groupe ���xiste d���j���
		else {
			joinChannel(nameChannel);
		}
	}

	/**
	 * Cr���er un canal de discussion poss���dant un mot de passe
	 *
	 * @param nameChannel
	 *            Le nom du canal de discussion
	 * @param topic
	 *            Le sujet du canal de discussion
	 * @param password
	 *            Le mot de passe
	 */
	public void createChannel(String nameChannel, String topic, String password) {
		if (createGroupIfAbsent(COMUNITY, nameChannel, true)) {
			// Je rejoins le role admin
			requestRole(COMUNITY, nameChannel, ADMINROLE);
			chatter.setAdrChatter(this.getAgentAddressIn(COMUNITY, nameChannel,
					ADMINROLE));
			chatter.setRole(ADMINROLE);
			Channel channel = new Channel(nameChannel, topic,
					chatter.getNickname(), password);
			broadcastFonctionMessage(new FonctionMessage(MAJCHANNEL, channel,
					nameChannel), HOME);
			this.addChannel(channel);
			HashMap<KernelAddress, String> liste = new HashMap<>();
			this.blackList.put(nameChannel, liste);
			this.nickList.add(this.chatter.getNickname());
			Chatter c = new Chatter(chatter.getNickname(),
					chatter.getAdrChatter(), ADMINROLE);
			listeChannel.get(nameChannel).getListeChatter()
					.put(c.getNickname(), c);
			window.addOngletChan(nameChannel, true);
			renameTopic(nameChannel, topic);
		} else {
			joinChannel(nameChannel, password);
		}

	}

	/**
	 * M���thode permettant de rejoindre un channel n'ayant pas de mot de passe
	 *
	 * @param nameChannel
	 *            Le nom du canal de discussion ��� rejoindre
	 */
	@SuppressWarnings("unchecked")
	public void joinChannel(String nameChannel) {

		boolean black = false;
		if (nameChannel.compareTo(HOME) != 0) {
			black = verifie(nameChannel);
		}

		if (!black) {
			requestRole(COMUNITY, nameChannel, CHATTEROLE);

			if (nameChannel.compareTo(HOME)==0){
				Message m = sendMessageAndWaitForReply(COMUNITY, HOME,
						ADMINROLE, new FonctionMessage(GETNICKLIST, null, HOME));

				Object obj = ((FonctionMessage) m).getObject();

				this.nickList= (ArrayList<String>) obj;

				if (this.nickList.contains(this.chatter.getNickname())){

					JOptionPane
							.showMessageDialog(
									null,
									"Le pseudonyme que vous avez choisi est déjà utilisé \n" +
											"Veuillez en trouver un ne faisant pas parti de la liste si dessous: \n" +
											this.nickList.toString(),
									"Info", JOptionPane.INFORMATION_MESSAGE);

					String nick = JOptionPane.showInputDialog(null, "Nouveau pseudonyme : ", "tchat",
							JOptionPane.QUESTION_MESSAGE);


					while (this.nickList.contains(nick)){

						nick = JOptionPane.showInputDialog(null, "Le pseudonyme n'est toujours pas valide : ", "tchat",
								JOptionPane.QUESTION_MESSAGE);
					}
					this.chatter.setNickname(nick);
				}
			}


			Message m = sendMessageAndWaitForReply(COMUNITY, HOME,
					ADMINROLE, new FonctionMessage(GETCHANNEL, null, HOME));

			Object obj = ((FonctionMessage) m).getObject();

			this.setListeChannel((HashMap<String, Channel>) obj);

			window.addOngletChan(nameChannel, false);
			chatter.setAdrChatter(this.getAgentAddressIn(COMUNITY,
					nameChannel, CHATTEROLE));
			chatter.setRole(CHATTEROLE);

			// J'annonce aux autres users que je suis dans le
			// channel
			broadcastMessage(this.getChatter().getNickname()
					+ " a rejoint le channel \n", nameChannel);

			// Je demande ��� l'admin la liste des autres users
			sendMessage(COMUNITY, nameChannel, ADMINROLE,
					new FonctionMessage(SENDCHATTER, chatter, nameChannel));
		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"Impossible de rejoindre ce channel, vous avez ���t���banni",
							"Info", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * M���thode permettant de rejoindre un channel ayant un mot de passe
	 *
	 * @param nameChannel
	 *            Le nom du canal de discussion ��� rejoindre
	 * @param password
	 *            Le mot de passe
	 */
	@SuppressWarnings("unchecked")
	public void joinChannel(String nameChannel, String password) {

		boolean black = false;
		if (nameChannel.compareTo(HOME) != 0) {
			black = verifie(nameChannel);
		}
		if (!black) {
			if (password.compareTo(listeChannel.get(nameChannel).getPassword()) == 0) {
				requestRole(COMUNITY, nameChannel, CHATTEROLE);

				if (nameChannel.compareTo(HOME)==0){
					Message m = sendMessageAndWaitForReply(COMUNITY, HOME,
							ADMINROLE, new FonctionMessage(GETNICKLIST, null, HOME));

					Object obj = ((FonctionMessage) m).getObject();

					this.nickList= (ArrayList<String>) obj;

					if (this.nickList.contains(this.chatter.getNickname())){

						JOptionPane
								.showMessageDialog(
										null,
										"Le pseudonyme que vous avez choisi est déjà utilisé \n" +
												"Veuillez en trouver un ne faisant pas parti de la liste si dessous: \n" +
												this.nickList.toString(),
										"Info", JOptionPane.INFORMATION_MESSAGE);

						String nick = JOptionPane.showInputDialog(null, "Nouveau pseudonyme : ", "tchat",
								JOptionPane.QUESTION_MESSAGE);


						while (this.nickList.contains(nick)){
							nick = JOptionPane.showInputDialog(null, "Le pseudonyme n'est toujours pas valide : ", "tchat",
									JOptionPane.QUESTION_MESSAGE);
						}
						this.chatter.setNickname(nick);
					}
				}

				window.addOngletChan(nameChannel, false);
				chatter.setAdrChatter(this.getAgentAddressIn(COMUNITY,
						nameChannel, CHATTEROLE));
				chatter.setRole(CHATTEROLE);

				// J'annonce aux autres users que je suis dans le
				// channel
				broadcastMessage(this.getChatter().getNickname()
						+ " a rejoint le channel \n", nameChannel);

				// Je demande ��� l'admin la liste des autres users
				sendMessage(COMUNITY, nameChannel, ADMINROLE,
						new FonctionMessage(SENDCHATTER, chatter,
								nameChannel));
			} else {
				JOptionPane.showMessageDialog(null,
						"Mot de passe incorrect", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"Impossible de rejoindre ce channel, vous avez ���t��� banni",
							"Info", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * M���thode permettant de rejoindre un canal de discussion sans fermer
	 * l'onglet.
	 *
	 * Cette m���thode est utilis���e lors du changement d'administrateur
	 */
	public void joinChannelKeep(String nameChannel, String role) {

		requestRole(COMUNITY, nameChannel, role);

		chatter.setAdrChatter(this.getAgentAddressIn(COMUNITY, nameChannel,
				role));
		chatter.setRole(role);

		// J'annonce aux autres users que je suis dans le
		// channel
		broadcastMessage(this.getChatter().getNickname()
				+ " a pris le role de " + role + " \n", nameChannel);

		// Je demande ��� l'admin la liste des autres users
		sendMessage(COMUNITY, nameChannel, ADMINROLE, new FonctionMessage(
				SENDCHATTER, chatter, nameChannel));

	}

	/**
	 * Permet de quitter un canal de discussion et de fermer l'onglet associ���.
	 *
	 */
	public void leaveChannel(String m, String channel) {

		if (channel.compareTo(HOME) == 0) {
			afficherChatmsg(HOME, "Attention",
					"Vous ne pouvez pas quitter ce channel");
		} else {

			// VERIFIE SI IL RESTE QUELQU'UN A PREVENIR DANS LE GROUPE
			if (this.listeChannel.get(channel).getListeChatter().size() > 1) {
				sendMessage(COMUNITY, channel, ADMINROLE, new FonctionMessage(
						REMOVECHATTER, chatter, channel));
				this.broadcastMessage(m, channel);

			}
			this.leaveGroup(COMUNITY, channel);
			window.getOnglet().remove(window.getOnglet().indexOfTab(channel));
			//this.listeChannel.remove(channel);
		}
	}

	/**
	 * Quitte un canal de discussion en gardant l'onglet associ��� ouvert
	 */
	public void leaveChannelKeep(String channel) {
		// VERIFIE SI IL RESTE QUELQU'UN A PREVENIR DANS LE GROUPE
		sendMessage(COMUNITY, channel, ADMINROLE, new FonctionMessage(
				REMOVECHATTER, chatter, channel));
		this.leaveGroup(COMUNITY, channel);
	}

	/**
	 * Met ��� jour la liste des utilisateurs pr���sent dans un canal de discussion
	 *
	 */
	public void majChatter(Message m, String channel) {

		Object o = ((FonctionMessage) m).getObject();
		AgentAddress ad = ((Chatter) o).getAdrChatter();
		String nick = ((Chatter) o).getNickname();
		String role = ((Chatter) o).getRole();

		Chatter c = new Chatter(nick, ad, role);
		this.pause(1000);
		if (((FonctionMessage) m).getAction().compareTo(SENDCHATTER) == 0) {
			listeChannel.get(channel).getListeChatter().put(c.getNickname(), c);
		}

		if (((FonctionMessage) m).getAction().compareTo(REMOVECHATTER) == 0) {

			listeChannel.get(channel).getListeChatter().remove(nick);

		}
		if (((FonctionMessage) m).getAction().compareTo(RENAMECHATTER) == 0) {
			listeChannel.get(channel).getListeChatter().get(nick).setChatter((Chatter) o);
		}

		broadcastMessage(COMUNITY, channel, CHATTEROLE, new FonctionMessage(
				REQUESTNICK, listeChannel.get(channel).getListeChatter(),
				channel));
		this.updateUsersInChan(listeChannel.get(channel).getListeChatter(),
				channel);
	}

	/**
	 * Met ��� jour la liste des utilisateurs pr���sent dans un canal de discussion
	 *
	 */
	public void majChatter(HashMap<String, Chatter> liste, String channel) {

		broadcastMessage(COMUNITY, channel, CHATTEROLE, new FonctionMessage(
				REQUESTNICK, listeChannel.get(channel).getListeChatter(),
				channel));
		broadcastMessage(COMUNITY, channel, ADMINROLE, new FonctionMessage(
				REQUESTNICK, listeChannel.get(channel).getListeChatter(),
				channel));

		this.updateUsersInChan(listeChannel.get(channel).getListeChatter(),
				channel);
	}

	/**
	 * Envoie un message ��� l'ensemble des utilisateurs pour leur permettre de
	 * mettre ��� jour leur blackList
	 *
	 */
	public void majBlack(String channel) {
		broadcastFonctionMessage(new FonctionMessage(REQUESTBLACK,
				getBlackList(), channel), HOME);
	}

	/**
	 * Permet de quitter un onglet de message priv��� en fermant l'onglet associ���
	 *
	 */
	public void leaveMp(String message, Chatter destinataire, boolean seul) {
		if (!seul) {
			sendMessage(destinataire.getAdrChatter(), new FonctionMessage(
					"/ALONE", message, this.getChatter().getNickname()));
		}
		window.getOnglet().remove(
				window.getOnglet().indexOfTab(destinataire.getNickname()));
	}

	/**
	 * Quitter un channel lorsque l'on est kick par l'administrateur
	 */
	public void kickChatter(String channel) {
		this.leaveChannel(this.chatter.getNickname() + " a ���t��� kick !", channel);
	}

	/**
	 * Permet l'affichage d'un message dans la fenetre de conversation du canal
	 * de discussion concern���
	 *
	 */
	public void afficherChatmsg(String channel, String chatter, String message) {
		int index = window.getOnglet().indexOfTab(channel);
		Component comp = window.getOnglet().getComponentAt(index);
		if (comp != null) {
			if (comp instanceof ChannelPanel) {
				ChannelPanel panel = (ChannelPanel) comp;
				panel.getConversation()
						.append(chatter + " : " + message + "\n");
			} else {
				if (comp instanceof MpPanel) {
					MpPanel panel = (MpPanel) comp;
					panel.getConversation().append(
							chatter + " : " + message + "\n");
				}
			}
		}
	}

	/**
	 * Envoie un message ��� tout le monde(tous les r���les compris) sauf ��� soi-m���me
	 *
	 */
	public void broadcastMessage(String message, String channel) {
		this.broadcastMessage(COMUNITY, channel, CHATTEROLE, new ChatMessage(
				message, chatter.getNickname(), channel));
		this.broadcastMessage(COMUNITY, channel, ADMINROLE, new ChatMessage(
				message, chatter.getNickname(), channel));
	}

	/**
	 * Envoie un message de type FonctionMessage ��� tout le monde hormi soi-m���me
	 *
	 */
	public void broadcastFonctionMessage(FonctionMessage message, String channel) {
		this.broadcastMessage(COMUNITY, channel, CHATTEROLE, message);
		this.broadcastMessage(COMUNITY, channel, ADMINROLE, message);
	}

	/**
	 * Retourne la liste des canaux de discussion ���xistants dans la communaut���
	 *
	 * @return liste La liste des canaux de discussion
	 */
	public String affChannel() {
		StringBuilder liste = new StringBuilder();
		for (String value : this.getExistingGroups(COMUNITY)) {
			liste.append(value).append(" \n");
		}
		return liste.toString();
	}

	/**
	 * Permet de renommer et mettre ��� jour le sujet d'un canal de discussion
	 *
	 */
	public void renameTopic(String channel, String topic) {
		getLogger().info(topic);
		this.listeChannel.get(channel).setTopic(topic);
		int index = window.getOnglet().indexOfTab(channel);
		ChannelPanel comp = (ChannelPanel) window.getOnglet().getComponentAt(
				index);
		comp.refreshTopic(topic);
	}

	/**
	 * V���rifie si l'utilisateur est banni dans un channel donn���
	 *
	 * @return boolean true s'il est banni
	 */
	public boolean verifie(String channel) {

		if ((this.getBlackList().size() == 1)) { // seulement le channel home,
			// donc pas possible qu'il y
			// ait de chatter bannies
			return false;
		} else {
			if (this.getBlackList().get(channel).isEmpty()) {
				return false;
			} else {
				return (this.getBlackList().get(channel).containsKey(this
						.getKernelAddress()));
			}
		}
	}


	public void changeNick(String nick){
		Set<String> channels = this.listeChannel.keySet();

		for (String channel : channels) {

			this.listeChannel.get(channel).getListeChatter().get(this.chatter.getNickname()).setNickname(nick);
			this.majChatter(this.listeChannel.get(channel).getListeChatter(), channel);
			this.updateUsersInChan(listeChannel.get(channel).getListeChatter(),
					channel);


		}

		this.chatter.setNickname(nick);

	}

}

