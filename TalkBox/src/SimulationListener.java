import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SimulationListener implements ActionListener{
	/**
	 * Implement the Action listener of the pressed buttons/ check box in the configuration app
	 * */
	//------------------ Fields --------------------------
	SimulatorFrame simFrame;
	
	//----------------------------------------------------
	
	/**
	 * @param n
	 * 			The number of buttons in the panel
	 * A constructor that calls and initialized the configuration app frame with the current
	 * action listener
	 * */
	public SimulationListener (int n) {
		simFrame = new SimulatorFrame(this, n);
	}
	
	public SimulationListener(SimulatorFrame sf)
	{
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand() == "Happy")
		{
			// TODO Auto-generated method stub
			// open the sound file as a Java input stream
			System.out.println("Happy");
		    String file = "Users\\ryann\\git\\EECS2311-Project\\EECS2311-Project\\TalkBox\\src\\Battery.mp3";
		    String music = "Battery.mp3";
		    Media hit = new Media(new File(music).toURI().toString());
		    MediaPlayer mediaPlayer = new MediaPlayer(hit);
		    mediaPlayer.play();
		}
		
		
	}
	
	
	

	
}
