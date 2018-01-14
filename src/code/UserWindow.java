package code;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	JLabel labelUsername = new JLabel("Enter username: ");
    JLabel labelPassword = new JLabel("Enter password: ");
    JTextField textUsername = new JTextField(20);
    JPasswordField fieldPassword = new JPasswordField(20);
    JButton buttonLogin = new JButton("submit");
    JPanel newPanel;
	JComboBox<String> websites;
    
    public UserWindow() {
        super("Password Protector");
         
        // create a new panel with GridBagLayout manager
        newPanel = new JPanel(new GridBagLayout());
         
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
         
        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;     
        newPanel.add(labelUsername, constraints);
 
        constraints.gridx = 1;
        newPanel.add(textUsername, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1;     
        newPanel.add(labelPassword, constraints);
         
        constraints.gridx = 1;
        newPanel.add(fieldPassword, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonLogin, constraints);
        
        
        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login Panel"));
         
        // add the panel to this frame
        add(newPanel);
         
        pack();
        setLocationRelativeTo(null);
        
        thehandler handler = new thehandler();
        buttonLogin.addActionListener(handler); 
        
    }
    
	void mainMenu(){
	
		newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Main menu"));
		
		
		
	}  
    
    
    private class thehandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            
  
            if(event.getSource()==buttonLogin){
            	mainMenu();
            	 
            }
        }
    } 	
}
