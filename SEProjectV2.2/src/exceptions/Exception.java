package exceptions;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Exceptions Interface, used by all Classes which throw Exceptions
 * @author jonas
 *
 */
@SuppressWarnings("serial")
public class Exception extends Frame implements ActionListener {

	Button okay;
	
	public Exception(String problem) {
		super ("Fehler"); // set title
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		GridBagLayout myGBL = new GridBagLayout ();
		setLayout(myGBL);
		
		GridBagConstraints myGBC = new GridBagConstraints();
		setVisible (true);
		
		// generally
		myGBC.fill = GridBagConstraints.HORIZONTAL;
		myGBC.insets = new Insets(5,5,5,5) ; // distance to line: 5 above, 5 left, 5 below, 5 right 
		
		// button okay
		myGBC.gridx = 3;
		myGBC.gridy = 3;
		myGBC.gridwidth = 1; // width of the element
		myGBC.gridheight = 1; // height of the element
		okay = new Button ("Okay");
		okay.setBackground(Color.green);
		okay.addActionListener(this);
		myGBL.setConstraints(okay, myGBC);
		add(okay);
		
		myGBC.gridx = 2;
		myGBC.gridy = 1;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label problemMessage = new Label ("            "+problem+"            ");
		myGBL.setConstraints(problemMessage, myGBC);
		add(problemMessage);
		

		
		pack();
	}
	
	  /**
	   * 
	   * implements the Button to dispose the Window
	   * 
		 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.okay )
		{
			this.dispose();
		}
		
	}
}
