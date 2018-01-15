package code;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class UserWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	//login
	JLabel labelUsername = new JLabel("Enter username: ");
    JLabel labelPassword = new JLabel("Enter password: ");
    JTextField textUsername = new JTextField(20);
    JPasswordField fieldPassword = new JPasswordField(20);
    JButton buttonLogin = new JButton("submit");
    JPanel newPanel =new JPanel(new GridBagLayout());
    
    //main menu
	JComboBox<String> websiteBox;
	GridBagConstraints constraints = new GridBagConstraints();
	JButton buttonGetPassword = new JButton("Get Account Info");
	JLabel websiteUsername = new JLabel("Username:");
	JLabel websitePassword = new JLabel("Password:");
	JButton buttonNewPassword = new JButton("New Website");
	
	
	//New Password
	JLabel newSiteLabel = new JLabel("Website: ");
	JTextField newSiteName = new JTextField(10);
	JLabel newUserLabel = new JLabel("Username: ");
	JTextField newUser = new JTextField(10);
	JLabel newPasswordLabel = new JLabel("Password: ");
	JPasswordField newPassword = new JPasswordField(10);
	JLabel newPasswordLabel2 = new JLabel("Verify: ");
	JPasswordField newPassword2 = new JPasswordField(10);		
	JButton buttonAddPassword = new JButton("Add");
	
	
	thehandler handler = new thehandler();
	
	
    
    public UserWindow() {
        super("Password Protector");
        // create a new panel with GridBagLayout manager
          
        
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
        
        
        buttonLogin.addActionListener(handler); 
        
    }
    
	void mainMenu(String username, String password){
	
		newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Main menu"));
		labelUsername.setVisible(false);
		labelPassword.setVisible(false);
		textUsername.setVisible(false);
		fieldPassword.setVisible(false);
		buttonLogin.setVisible(false);
		
		ArrayList<String> sites = new ArrayList<String>();
		try {
			sites = Main.getWebsites(username, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] siteList = sites.toArray(new String[sites.size()]);
		websiteBox = new JComboBox<>(siteList);
		
		constraints.gridx = 0;
	    constraints.gridy = 0;
	    newPanel.add(websiteBox, constraints);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 1;
	    newPanel.add(buttonGetPassword, constraints);
		
        buttonGetPassword.addActionListener(handler); 
        
        constraints.gridx = 0;
	    constraints.gridy = 2;
	    constraints.gridwidth = 10;
        constraints.anchor = GridBagConstraints.CENTER;
	    newPanel.add(buttonNewPassword, constraints);
	    
	    buttonNewPassword.addActionListener(handler);
        
		
	}  
	
	void addWebsite(String username,String password){
		websiteBox.setVisible(false);
		buttonGetPassword.setVisible(false);
		buttonNewPassword.setVisible(false);
		
		// add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;     
        newPanel.add(newSiteLabel, constraints);
 
        constraints.gridx = 3;
        newPanel.add(newSiteName, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1;     
        newPanel.add(newUserLabel, constraints);
         
        constraints.gridx = 3;
        newPanel.add(newUser, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 2;     
        newPanel.add(newPasswordLabel, constraints);
         
        constraints.gridx = 3;
        newPanel.add(newPassword, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonAddPassword, constraints);
		
		
		
	}
    
    
    private class thehandler implements ActionListener{
    	
    	String username;
    	String password;
    	
        public void actionPerformed(ActionEvent event){
        
  
            if(event.getSource()==buttonLogin){
            	try {
            		username = textUsername.getText();
            		password = fieldPassword.getText();
					if(Main.getConnection(username, password)!=null){
						mainMenu(username, password);
					}
            		
					
				} catch (Exception e) {
					System.out.println(e);
				}
 
            }
            
            if(event.getSource()==buttonGetPassword){
            	
            	System.out.println(password);
            	
            	String siteUserName ="Error";
            	String sitePassword="Error";
            	String site = (String)websiteBox.getSelectedItem();
            	ArrayList<String> userData= new ArrayList<String>();
            	
            	
            	try{
            		
            		userData = Main.getUserData(username, password, site);
            		siteUserName = userData.get(0);
            		sitePassword = userData.get(1);
            	}catch(Exception e){System.out.println(e);}
            	
            	String string = "Website: \t" + site + "\nUsername: \t" +siteUserName+ "\nPassword: \t" +sitePassword; 
            	
            	JOptionPane.showMessageDialog(null, string);

            	
            }
            
           if(event.getSource()==buttonNewPassword){
        	   addWebsite(username, password);
        	   
           } 
 
        }
    } 	
}
