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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class initializes the windows when we create or we try to join a channel
 *
 * @author Bistour/Clerix
 * @version 1.9
 */

@SuppressWarnings("serial")
public class ChannelDialog extends JDialog {





	private JLabel name, password, topic;

	private JTextField nameT, passwordT, topicT;
	/**
	 * @see madkit.chat.ChannelInfo
	 */
	private ChannelInfo info = new ChannelInfo();

	/**
	 * ChannelDialog Constructor
	 *
	 *	If choice = 0, then it will initialize the window dedicated to the creation of a channel
	 *  If choice = 1, it will initialize the window of when you want to join a channel
	 */
	public ChannelDialog(JFrame parent, String title, boolean modal, int choice) {
		super(parent, title, modal);
		if (choice == 0) {
			this.init();
		} else {
			this.initJoin();
		}
	}

	/**
	 * Show the window.
	 * This method also gives the informations of the channel.
	 */
	public ChannelInfo showDialog() {
		this.setVisible(true);
		return this.info;
	}

	/**
	 * method which initialize the "Put a name:" panel.
	 */
	private JPanel addPannelName(){
		JPanel panNom = new JPanel();
		panNom.setPreferredSize(new Dimension(175, 75));
		nameT = new JTextField();
		nameT.setPreferredSize(new Dimension(100, 25));
		panNom.setBorder(BorderFactory.createTitledBorder("Channel name"));
		name = new JLabel("Put a name :");
		panNom.add(name);
		panNom.add(nameT);
		return panNom;
	}

	/**
	 * method which initialize the "Put a password :" panel.
	 */
	private JPanel addPannelPassword(){
		JPanel panPass = new JPanel();
		panPass.setPreferredSize(new Dimension(175, 75));
		passwordT = new JTextField();
		passwordT.setPreferredSize(new Dimension(100, 25));
		panPass.setBorder(BorderFactory.createTitledBorder("Password"));
		password = new JLabel("Put a password :");
		panPass.add(password);
		panPass.add(passwordT);
		return panPass;
	}

	/**
	 * method which initialize the "Put a topic :" panel.
	 */
	private JPanel addPannelTopic(){
		JPanel panTopic = new JPanel();
		panTopic.setPreferredSize(new Dimension(175, 75));
		topicT = new JTextField();
		topicT.setPreferredSize(new Dimension(100, 25));
		panTopic.setBorder(BorderFactory.createTitledBorder("Topic"));
		topic = new JLabel("Put a topic :");
		panTopic.add(topic);
		panTopic.add(topicT);
		return panTopic;
	}

	/**
	 * method which initialize the ok button.
	 * It also associates the button with the the action : Initialize a ChannelInfo with the name, and password
	 * and a topic because we're creating a channel.
	 */
	private JButton addOKButtonTopic(){
		JButton okButton = new JButton("OK");

		okButton.addActionListener(arg0 -> {
			System.out.println("hello"+ passwordT.getText()+"hello");
			info = new ChannelInfo(nameT.getText(), passwordT.getText(), topicT
					.getText());
			setVisible(false);
		});
		return okButton;
	}

	/**
	 * method which initialize the ok button.
	 * It also associates the button with the the action : Initialize a ChannelInfo with the name, and password
	 * and no topic because we're joining a channel here.
	 */
	private JButton addOKButton(){
		JButton okButton = new JButton("OK");
		okButton.addActionListener(arg0 -> {
			info = new ChannelInfo(nameT.getText(), passwordT.getText(), null);
			setVisible(false);
		});
		return okButton;
	}

	/**
	 * method which initialize the cancel button.
	 * It also associates the button with the the action : Closing the window.
	 */
	private JButton addCancelButton(){
		JButton cancelButton = new JButton("Cancel");

		cancelButton.addActionListener(arg0 -> setVisible(false));
		return cancelButton;
	}


	/**
	 * This method initialize the window when you try to join a channel.
	 *
	 * Creation of 2 textfields : name, password.
	 * Creation of 2 buttons : Ok, Cancel.
	 *
	 * The Ok button confirm the informations and create a ChannelInfo.
	 *
	 * @see madkit.chat.ChannelInfo
	 */
	private void initJoin() {

		this.setResizable(false);
		this.setSize(400, 155);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panNom = addPannelName();
		JPanel panPass = addPannelPassword();

		JPanel content = new JPanel();

		content.add(panNom);
		content.add(panPass);

		JPanel control = new JPanel();

		JButton okBouton = addOKButton();
		JButton cancelButton = addCancelButton();

		control.add(okBouton);
		control.add(cancelButton);

		this.getContentPane().add(content, BorderLayout.NORTH);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}

	/**
	 * This method initialize the window when you create a channel.
	 *
	 * Creation of 3 textfields : name, password, topic.
	 * Creation of 2 buttons : Ok, Cancel.
	 *
	 * The Ok button confirm the informations and create a ChannelInfo.
	 *
	 * @see madkit.chat.ChannelInfo
	 */
	private void init() {

		this.setResizable(false);
		this.setSize(575, 155);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panNom = addPannelName();
		JPanel panPass = addPannelPassword();
		JPanel panTopic = addPannelTopic();

		JPanel content = new JPanel();
		content.add(panNom);
		content.add(panPass);
		content.add(panTopic);

		JPanel control = new JPanel();

		JButton okButton = addOKButtonTopic();
		JButton cancelButton = addCancelButton();

		control.add(okButton);
		control.add(cancelButton);

		this.getContentPane().add(content, BorderLayout.NORTH);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}

}