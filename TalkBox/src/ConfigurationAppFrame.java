
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class ConfigurationAppFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * A visual representation of the ConfigurationApp
	 * */
	
	// ------------- Fields ------------------------
	public static int page = 0; 			// The 'page' the user is currently in.
	
	JPanel panel; 							// The first panel we will work with
	
	JLabel labelForTextField;					// The label for the check box
	
	JLabel title;		 				    // The title of the page
	JLabel labelForImage, labelForSound; 	// The labels for the sound and image buttons
	JLabel blankSpace = new JLabel(""); 	// Space holder
	
	JButton uploadImage;					// A button for the user to upload an image
	JButton uploadSound;	 				// A button for the user to upload a sound
	JButton pickImage;						// A button for the user to pick from out image store
	JButton pickSound;						// A button for the user to pick from our sound store
	JCheckBox checkToSelfUploadSound;		// A check box for the user to check if wants to upload his own sound
	JCheckBox checkToSelfUploadImage;		// A check box for the user to check if wants to upload his own image
	
	JButton next;							// A button to go to the next page
	JButton previous;						// A button to go to the previous page
	JButton exit;							// A button to exit
	
	String[] numbers = {"1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"}; // numbers used in combo box
	JComboBox<String> comboBox = new JComboBox<String>(numbers);  // The user puts the number of buttons he wants. Must be a natural number
	
	GroupLayout layout;
	Container container;					// Container
	
	// ---------------------------------------------
	
	
	/**
	 * @param l
	 * 	     the action listener
	 * 
	 * @param i
	 * 		 the item listener for the check boxes
	 * 
	 * A constructor that initialize JFrame and it's own configuration
	 * */
	public ConfigurationAppFrame (ActionListener l, ItemListener i){
		super("Configuration App Wizard");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);							
		
		this.setResizable(false);
		initializeComponents(l, i);
        initializePanel();	
        
       
	}
	
	/**
	 * @param l
	 * 			the action listener
	 * 
	 * @param i
	 * 			the item listener for the check boxes
	 * 
	 * A method that initialized the components
	 * Associate the action listener l to the buttons and check boxes.
	 * 
	 * */
	private void initializeComponents (ActionListener l, ItemListener i) {
		title = new JLabel();
		labelForTextField = new JLabel("");
		previous = new JButton("Previous");
		previous.addActionListener(l);
		next = new JButton("next");
		next.addActionListener(l);
		exit = new JButton("Exit");
		exit.addActionListener(l);
		
		// Initialized for later use:
		uploadImage = new JButton("Upload Image");
		uploadImage.addActionListener(l);
		uploadSound = new JButton("Upload Sound");
		uploadSound.addActionListener(l);
		pickImage = new JButton("Pick Image");
		pickImage.addActionListener(l);
		pickSound = new JButton("Pick Sound");
		pickSound.addActionListener(l);
		checkToSelfUploadSound = new JCheckBox("Upload sound yourself");
		checkToSelfUploadSound.addItemListener(i);
		checkToSelfUploadImage = new JCheckBox("Upload image yourself");
		checkToSelfUploadImage.addItemListener(i);
		
		panel = new JPanel();
		layout = new GroupLayout(panel);
		panel.setLayout(layout);
	}
	
	/**
	 * 
	 * A method that initialized the panel and provide the first view for the client
	 * 
	 * */
	private void initializePanel() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setSize(439, 118);
		
		// Automatic gap insertion
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
	    
		
	    // Initialize components
		title.setText("Welcome to the Configuration Wizard for your Talk Box");
		labelForTextField.setText("Please select the number of buttons you want to have: ");
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
	    
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(labelForTextField)
						.addComponent(exit))
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(comboBox)
						.addComponent(next)
		));
		
		 
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(blankSpace))
				.addGroup(layout.createParallelGroup()
						.addComponent(labelForTextField)
						.addComponent(comboBox))
				.addGroup(layout.createParallelGroup()
						.addComponent(exit)
						.addComponent(next)
		));
		
		this.add(panel);
	}
	
	/**
	 * Returns the integer value which represents the number of buttons selected
	 * */
	public int insideComboBox() {
		return this.comboBox.getSelectedIndex();
	}
	
	/**
	 * @param size
	 * 			The number of buttons the user works with 
	 * 
	 * Uses the field 'page', then increment 'page' for the next future page update 
	 * The method updated the view to go to the next page 
	 * */
	public void pressedNext (int size) {
		// If next goes to the last page - special setting
		if (page == size + 1)
			lastPage();
		// If next goes to the first initial setting - special settign
		else if (page == 0)
			firstPage();
		// Middle setting
		else {
			if (checkToSelfUploadSound.isSelected()) {
				checkToSelfUploadSound.setSelected(false);
				layout.replace(uploadSound, pickSound);
			}
			if (checkToSelfUploadImage.isSelected()) {
				checkToSelfUploadImage.setSelected(false);
				layout.replace(uploadImage, pickImage);
			}
		}
		
		page ++;
	}
	
	/**
	 *	@param size
	 *			The number of buttons the user works with
	 *
	 * Uses the field page to go back words, then decrement it
	 * The method updates the view to go to the previous page
	 * 
	 * */
	public void pressedPrevious (int size) {
		// If the current page is the last page, needs special setting
		if (page == size + 1) {
			System.out.println("1st if");
			firstPage();
		}
		// If the current page is the first initialized page
		else if (page == 1) {
			System.out.println("2nd if");
			refresh();
			initializePanel();
		}
		// Middle setting
		else {
			System.out.println("3rd if");
			if (checkToSelfUploadSound.isSelected()) {
				checkToSelfUploadSound.setSelected(false);
				layout.replace(uploadSound, pickSound);
			}
			if (checkToSelfUploadImage.isSelected()) {
				checkToSelfUploadImage.setSelected(false);
				layout.replace(uploadImage, pickImage);
			}
		}

		page --;
	}
	
	private void refresh () {
		System.out.println("represh");
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		layout = new GroupLayout(panel);
		panel.setLayout(layout);
	}
	
	/**
	 *  Sets the outline of the first page
	 * */
	private void firstPage () {
		title.setText("Please choose an image and sound:");
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		this.setSize(450, 150);
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
	    
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(checkToSelfUploadImage)
						.addComponent(pickImage)
						.addComponent(exit))
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(checkToSelfUploadSound)
						.addComponent(pickSound)
						.addGroup(layout.createSequentialGroup()
							.addComponent(previous)
							.addComponent(next)))
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, previous, next);
		 
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(blankSpace))
				.addGroup(layout.createParallelGroup()
						.addComponent(checkToSelfUploadImage)
						.addComponent(checkToSelfUploadSound))
				.addGroup(layout.createParallelGroup()
						.addComponent(pickImage)
						.addComponent(pickSound))
				.addGroup(layout.createParallelGroup()
						.addComponent(exit)
						.addGroup(layout.createParallelGroup()
								.addComponent(previous)
								.addComponent(next)))
		);
	
	}
	
	/**
	 *  Set up of the last page of the configuration wizard
	 * */
	private void lastPage () {
		title.setText("Your configuration is complete.");
		
		this.setSize(410, 90);
		
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(blankSpace)
						.addComponent(previous))
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(blankSpace)
						.addComponent(blankSpace))
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(blankSpace)
						.addComponent(exit))
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, previous, exit);
		 
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(title)
						.addComponent(blankSpace))
				.addGroup(layout.createParallelGroup()
						.addComponent(blankSpace)
						.addComponent(blankSpace)
						.addComponent(blankSpace))
				.addGroup(layout.createParallelGroup()
						.addComponent(previous)
						.addComponent(blankSpace)
						.addComponent(exit))
		);
		
	}
	
	/**
	 * Pressed on check box of upload image - it updates the look of the panel
	 * */
	public void uploadImageCheckBox () {
		if (checkToSelfUploadImage.isSelected()) 
			layout.replace(pickImage, uploadImage);
		else
			layout.replace(uploadImage, pickImage);
	}
	
	/**
	 * Pressed on check box of upload image - it updates the look of the panel
	 * */
	public void uploadSoundCheckBox () {
		if (checkToSelfUploadSound.isSelected()) 
			layout.replace(pickSound, uploadSound);
		else
			layout.replace(uploadSound, pickSound);
	}
	
	/**
	 * A main method to test the view of the configuration app
	 * */
/*	public static void main (String[] args) {
		
		Scanner scan = new Scanner(System.in);
		ConfigurationAppFrame conf = new ConfigurationAppFrame(null, null);
		conf.setVisible(true);
		conf.pack();
		int i = scan.nextInt();
		conf.pressedNext(4);
		i = scan.nextInt();
		conf.uploadImageCheckBox();
		i = scan.nextInt();
		conf.uploadSoundCheckBox();
		System.out.println(ConfigurationAppFrame.page);
		conf.pressedNext(4);
		i = scan.nextInt();
		System.out.println(ConfigurationAppFrame.page);
		conf.pressedNext(ConfigurationAppFrame.page);
		
		ConfigurationAppFrame.page = 3;
		
		System.out.println("page " + ConfigurationAppFrame.page);
		i = scan.nextInt();
		conf.pressedPrevious(2);
		
		ConfigurationAppFrame.page = 1;
		
		System.out.println("page " + ConfigurationAppFrame.page);
		i = scan.nextInt();
		conf.pressedPrevious(2);
		
		System.out.println("page " + ConfigurationAppFrame.page);
		i = scan.nextInt();
		//conf.pressedPrevious(2);
		
		scan.close();
	}*/
	
}

