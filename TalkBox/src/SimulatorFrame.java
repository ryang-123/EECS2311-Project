import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.event.ActionListener;

import javax.swing.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SimulatorFrame extends JFrame {

	/**
	
	 * 
	
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 
	 * 
	 * A visual representation of simulation app
	 * 
	 * 
	 * 
	 */

	// ------------ Fields ---------------------

	JButton[] pics; // An array of buttons. Size initialized by the desired amount of buttons

	JPanel panel; // The used panel

	// -----------------------------------------

	public SimulatorFrame(ActionListener l, int n) {

		super("FrameDemo");

		initializePanel(l, n);

		// frame.getContentPane().setBackground(Color.cyan);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setPreferredSize(new Dimension(1100, 300));

		pack();

		setLocationRelativeTo(null);

		setVisible(true);

	}

	/**
	 * 
	 * 
	 * 
	 * @param n
	 * 
	 * 
	 * 
	 *          The number of buttons to be declared
	 * 
	 * 
	 * 
	 * @param l
	 * 
	 * 
	 * 
	 *          The action listener
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 *          A method that initialized the panel and provide the first view for
	 * 
	 *          the client
	 * 
	 * 
	 * 
	 *          Associate the action listener l to the buttons --- GridLayout ?
	 * 
	 *          -----
	 * 
	 * 
	 * 
	 * @param n The number of buttons to be declared
	 * 
	 * @param l The action listener
	 * 
	 * 
	 * 
	 *          A method that initialized the panel and provide the first view for
	 * 
	 *          the client Associate the action listener l to the buttons ---
	 * 
	 *          GridLayout ? ----- >>>>>>> branch 'master' of
	 * 
	 *          https://github.com/ryang-123/EECS2311-Project
	 * 
	 */

	private void initializePanel(ActionListener l, int n) {

		pics = new JButton[n];

		panel = new JPanel(new GridLayout(1, n, 1, 1));

		for (int i = 0; i < pics.length; i++) {

			pics[i] = new JButton("");

//			pics[i].setBackground(Color.BLUE);

			pics[i].setVerticalTextPosition(SwingConstants.TOP);

			pics[i].setHorizontalTextPosition(SwingConstants.CENTER);

			pics[i].setVisible(true);

			pics[i].addActionListener(l);

			panel.add(pics[i]);

		}

		this.add(panel);

	}

	/**
	 * 
	 * 
	 * 
	 * @param buttonName
	 * 
	 * 
	 * 
	 *                   the name of the desired button
	 * 
	 * 
	 * 
	 * @param image    
	 * 
	 * 					 The URL or link to the image
	 * 
	 * @param indexOfButton
	 * 
	 * 					 The index of the intended button
	 * 
	 * 
	 * 
	 * @throws IndexOutOfBoundsException if {@code i < 0 || i >= n }.
	 * 
	 * 
	 * 
	 *                                   A method that helps the user create desired
	 * 
	 *                                   button with complete freedom while also
	 * 
	 *                                   updating and changing the text associated
	 * 
	 *                                   with specific buttons.
	 * 
	 */

	public void SetButton(String buttonName, String image, int indexOfButton) throws IndexOutOfBoundsException {

		try {


			ImageIcon icon = new ImageIcon(image);

			pics[indexOfButton].setText(buttonName);
			System.out.println("here");

			pics[indexOfButton].setIcon(icon);
			pics[indexOfButton].addActionListener(new SimulationListener(4));
			
			
		} catch (IndexOutOfBoundsException e) {

			System.out.println("The index you have entered is invalid");

		}

	}

	/**
	 * 
	 * 
	 * 
	 * play the sound based on the pressed button
	 * 
	 * 
	 * 
	 */

	public static void panelUpdate(JButton b, String buttonName) {

	}

	/**
	 * 
	 * 
	 * 
	 * A main method to test the view of the simulator app
	 * 
	 * 
	 * 
	 */

	public static void main(String[] args) {
		//This needs to be changed to the number of buttons that will be on the simulator 
		SimulatorFrame s = new SimulatorFrame(null, 4);

		//Deserializing the file for use here
		
		/*
		ConfigurationAppFrame ConfAppFram = null;
	      try {
	         FileInputStream fileIn = new FileInputStream("/ConfigurationAppFrame.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         ConfAppFram = (ConfigurationAppFrame) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return;
	      } catch (ClassNotFoundException c) {
	         System.out.println("ConfigurationAppFrame class not found");
	         c.printStackTrace();
	         return;
	      }
		
		//This needs to be changed to the number of buttons that will be on the simulator 
		SimulatorFrame s = new SimulatorFrame(null, 4);
		//SimulatorFrame s = new SimulatorFrame(null, image_location.length);
		
		//need to store both the text for the button and the image?
		
		//image_location needs to be changed to the appropriate array
		String[] image_location = new String[50];
		for(int i = 0; i < image_location.length; i++) {
			//If we just want the file name can either clip the string
			//or can use regex to grab it
			s.SetButton("Button: " + i, image_location[i], i);
		}
		*/
		
		
		s.SetButton("Happy", "C:\\Users\\ryann\\git\\EECS2311-Project\\EECS2311-Project\\TalkBox\\src\\Happy.jpg", 0);

		s.SetButton("Sad", "C:\\Users\\ryann\\git\\EECS2311-Project\\EECS2311-Project\\TalkBox\\src\\Sad.jpg",

				1);

		s.SetButton("Angry","C:\\Users\\ryann\\git\\EECS2311-Project\\EECS2311-Project\\TalkBox\\src\\Angry.jpg", 2);


		s.SetButton("Perplexed","C:\\Users\\ryann\\git\\EECS2311-Project\\EECS2311-Project\\TalkBox\\src\\Perplexed.jpg", 3);

	


	}
}