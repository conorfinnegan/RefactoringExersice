import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.text.MaskFormatter;
import java.util.ArrayList; 

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Menu extends JFrame{
	
	private static ArrayList<Customer> customerList = new ArrayList<Customer>();
    public int position = 0;
	private String password;
	public Customer customer = null;
	public CustomerAccount acc = new CustomerAccount();
	static JFrame f;
	JFrame f1;
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField;
	JLabel customerIDLabel, passwordLabel;
	JTextField customerIDTextField, passwordTextField;
	Container content;
	Customer e;
	JPanel panel2;
	JButton add;
	String 	PPS,firstName,surname,DOB,CustomerID;
	
	public static void main(String[] args)
	{
		Menu driver = new Menu();
		driver.menuStart();
	}
	
	public void menuStart()
	{
		   /*The menuStart method asks the user if they are a new customer, an existing customer or an admin. It will then start the create customer process
		  if they are a new customer, or will ask them to log in if they are an existing customer or admin.*/
		
			
		
			
			f = new JFrame("User Type");
			f.setSize(400, 300);
			f.setLocation(200, 200);
			f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) { System.exit(0); }
			});

			JPanel userTypePanel = new JPanel();
			final ButtonGroup userType = new ButtonGroup();
			JRadioButton radioButton;
			userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
			radioButton.setActionCommand("Customer");
			userType.add(radioButton);
			
			userTypePanel.add(radioButton = new JRadioButton("Administrator"));
			radioButton.setActionCommand("Administrator");
			userType.add(radioButton);
			
			userTypePanel.add(radioButton = new JRadioButton("New Customer"));
			radioButton.setActionCommand("New Customer");
			userType.add(radioButton);

			JPanel continuePanel = new JPanel();
			JButton continueButton = new JButton("Continue");
			continuePanel.add(continueButton);

			Container content = f.getContentPane();
			content.setLayout(new GridLayout(2, 1));
			content.add(userTypePanel);
			content.add(continuePanel);



			continueButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					String user = userType.getSelection().getActionCommand(  );
					
					//if user selects NEW CUSTOMER--------------------------------------------------------------------------------------
					if(user.equals("New Customer"))
					{
						f.dispose();		
						f1 = new JFrame("Create New Customer");
						f1.setSize(400, 300);
						f1.setLocation(200, 200);
						f1.addWindowListener(new WindowAdapter() {
							public void windowClosing(WindowEvent we) { System.exit(0); }
						});
							Container content = f1.getContentPane();
							content.setLayout(new BorderLayout());
							
							firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
							surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
							pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
							dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
							firstNameTextField = new JTextField(20);
							surnameTextField = new JTextField(20);
							pPSTextField = new JTextField(20);
							dOBTextField = new JTextField(20);
							JPanel panel = new JPanel(new GridLayout(6, 2));
							panel.add(firstNameLabel);
							panel.add(firstNameTextField);
							panel.add(surnameLabel);
							panel.add(surnameTextField);
							panel.add(pPPSLabel);
							panel.add(pPSTextField);
							panel.add(dOBLabel);
							panel.add(dOBTextField);
								
							panel2 = new JPanel();
							add = new JButton("Add");
							
							 add.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
							
									
						PPS = pPSTextField.getText();
						firstName = firstNameTextField.getText();
						surname = surnameTextField.getText();
						DOB = dOBTextField.getText();
						password = "";
					
						CustomerID = "ID"+PPS ;

						add.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								f1.dispose();
								
								boolean loop = true;
								while(loop){
								 password = JOptionPane.showInputDialog(f, "Enter 7 character Password;");
								
								 if(password.length() != 7)//Making sure password is 7 characters
								    {
								    	JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long", JOptionPane.OK_OPTION);
								    }
								 else
								 {
									 loop = false;
								 }
								}
	
							    ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();
							    
								Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password, accounts);
											
								customerList.add(customer);
								
								System.out.println(customerList);
									
								JOptionPane.showMessageDialog(f, "CustomerID = " + CustomerID +"\n Password = " + password  ,"Customer created.",  JOptionPane.INFORMATION_MESSAGE);
								menuStart();
								f.dispose();
								menuStart();
							}
						});	
								}
							});						
							JButton cancel = new JButton("Cancel");					
							cancel.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									f1.dispose();
									menuStart();
								}
							});	
							
							panel2.add(add);
							panel2.add(cancel);
							
							content.add(panel, BorderLayout.CENTER);
							content.add(panel2, BorderLayout.SOUTH);
					
							f1.setVisible(true);		
						
					}
					
					
					//------------------------------------------------------------------------------------------------------------------
					
					//if user select ADMIN----------------------------------------------------------------------------------------------
					if(user.equals("Administrator")	)
					{
						boolean loop = true, loop2 = true;
						boolean cont = false;
					    while(loop)
					    {
					    Object adminUsername = JOptionPane.showInputDialog(f, "Enter Administrator Username:");

					    if(!adminUsername.equals("admin"))//search admin list for admin with matching admin username
					    {
					    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?", JOptionPane.YES_NO_OPTION);
					    	if (reply == JOptionPane.YES_OPTION) {
					    		loop = true;
					    	}
					    	else if(reply == JOptionPane.NO_OPTION)
					    	{
					    		f1.dispose();
					    		loop = false;
					    		loop2 = false;
					    		menuStart();
					    	}
					    }
					    else
					    {
					    	loop = false;
					    }				    
					    }
					    
					    while(loop2)
					    {
					    	Object adminPassword = JOptionPane.showInputDialog(f, "Enter Administrator Password;");
					    	
					    	   if(!adminPassword.equals("admin11"))//search admin list for admin with matching admin password
							    {
							    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?", JOptionPane.YES_NO_OPTION);
							    	if (reply == JOptionPane.YES_OPTION) {
							    		
							    	}
							    	else if(reply == JOptionPane.NO_OPTION){
							    		f1.dispose();
							    		loop2 = false;
							    		menuStart();
							    	}
							    }
					    	   else
					    	   {
					    		   loop2 =false;
					    		   cont = true;
					    	   }
					    }
					    	
					    if(cont)
					    {
						f1.dispose();
					    	loop = false;
					    admin();					    
					    }					    
					}
					//----------------------------------------------------------------------------------------------------------------
					
					
					
					//if user selects CUSTOMER ---------------------------------------------------------------------------------------- 
					if(user.equals("Customer")	)
					{
						boolean loop = true, loop2 = true;
						boolean cont = false;
						boolean found = false;
						Customer customer = null;
					    while(loop)
					    {
					    Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");
					    
					    for (Customer aCustomer: customerList){
					    	
					    	if(aCustomer.getCustomerID().equals(customerID))//search customer list for matching customer ID
					    	{
					    		found = true;
					    		customer = aCustomer;
					    		System.out.println("This is the customer" + customer);
					    	}					    	
					    }
					    
					    if(found == false)
					    {
					    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
					    	if (reply == JOptionPane.YES_OPTION) {
					    		loop = true;
					    	}
					    	else if(reply == JOptionPane.NO_OPTION)
					    	{
					    		f.dispose();
					    		loop = false;
					    		loop2 = false;
					    		menuStart();
					    	}
					    }
					    else
					    {
					    	loop = false;
					    }
					   
					    }
					    
					    while(loop2)
					    {
					    	Object customerPassword = JOptionPane.showInputDialog(f, "Enter Customer Password;");
					    	
					    	   if(!customer.getPassword().equals(customerPassword))//check if custoemr password is correct
							    {
							    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?", JOptionPane.YES_NO_OPTION);
							    	if (reply == JOptionPane.YES_OPTION) {
							    		
							    	}
							    	else if(reply == JOptionPane.NO_OPTION){
							    		f.dispose();
							    		loop2 = false;
							    		menuStart();
							    	}
							    }
					    	   else
					    	   {
					    		   loop2 =false;
					    		   cont = true;
					    	   }
					    }
					    	
					    if(cont)
					    {
						f.dispose();
					    	loop = false;
					    	customer(customer);				    
					    }				    
					}
					//-----------------------------------------------------------------------------------------------------------------------
				}
			});f.setVisible(true);	
	}
	

	
	public void admin()
	{
		Admin admin = new Admin();
		Buttons butt = new Buttons();
		
		dispose();
		
		f = new JFrame("Administrator Menu");
		f.setSize(400, 400);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});          
		f.setVisible(true);
		
		JPanel deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteCustomer = new JButton("Delete Customer");	
		deleteCustomer.setPreferredSize(new Dimension(250, 20));
		deleteCustomerPanel.add(deleteCustomer);
		
		JPanel deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteAccount = new JButton("Delete Account");
		deleteAccount.setPreferredSize(new Dimension(250, 20));	
		deleteAccountPanel.add(deleteAccount);
		
		JPanel bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton bankChargesButton = new JButton("Apply Bank Charges");
		bankChargesButton.setPreferredSize(new Dimension(250, 20));	
		bankChargesPanel.add(bankChargesButton);
		
		JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton interestButton = new JButton("Apply Interest");
		interestPanel.add(interestButton);
		interestButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton editCustomerButton = new JButton("Edit existing Customer");
		editCustomerPanel.add(editCustomerButton);
		editCustomerButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton navigateButton = new JButton("Navigate Customer Collection");
		navigatePanel.add(navigateButton);
		navigateButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton summaryButton = new JButton("Display Summary Of All Accounts");
		summaryPanel.add(summaryButton);
		summaryButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton accountButton = new JButton("Add an Account to a Customer");
		accountPanel.add(accountButton);
		accountButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton returnButton = new JButton("Exit Admin Menu");
		returnPanel.add(returnButton);

		JLabel label1 = new JLabel("Please select an option");
		
		content = f.getContentPane();
		content.setLayout(new GridLayout(9, 1));
		content.add(label1);
		content.add(accountPanel);
		content.add(bankChargesPanel);
		content.add(interestPanel);
		content.add(editCustomerPanel);
		content.add(navigatePanel);
		content.add(summaryPanel);	
		content.add(deleteCustomerPanel);
		content.add(returnPanel);
		
		
		bankChargesButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {	
				admin.bankCharges();		    
			}		
	     });
		
		interestButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				admin.interest();
			}	
	     });
		
		editCustomerButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				admin.editCustomer();
				}
	     });
		
		summaryButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				admin.summary();
			}
	     });
		
		navigateButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				admin.navigate();	
			}
		});
		
		accountButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {	
				admin.createAccount();
			}
	     });		

		deleteCustomer.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				admin.deleteCustomer();
			}
	     });		
		
		deleteAccount.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				admin.deleteAccount();
			}
	     });		
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				butt.returnButton();			
			}
	     });		
	}
	
	public void customer(Customer e)
	{	
		Buttons butt = new Buttons();
		CustomerMethods customer = new CustomerMethods();
		
		f = new JFrame("Customer Menu");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});          
		f.setVisible(true);

		
		if(e.getAccounts().size() == 0){
			JOptionPane.showMessageDialog(f, "This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. " ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
			f.dispose();				
			menuStart();
		}
		else
		{
		JPanel buttonPanel = new JPanel();
		JPanel boxPanel = new JPanel();
		JPanel labelPanel = new JPanel();
		
		JLabel label = new JLabel("Select Account:");
		labelPanel.add(label);
		
		JButton returnButton = new JButton("Return");
		buttonPanel.add(returnButton);
		JButton continueButton = new JButton("Continue");
		buttonPanel.add(continueButton);
		
		JComboBox<String> box = new JComboBox<String>();
	    for (int i =0; i < e.getAccounts().size(); i++)
	    {
	     box.addItem(e.getAccounts().get(i).getNumber());
	    }
		
	    
	   
	    for(int i = 0; i<e.getAccounts().size(); i++)
	    {
	    	if(e.getAccounts().get(i).getNumber() == box.getSelectedItem() )
	    	{
	    		acc = e.getAccounts().get(i);
	    	}
	    }
	    
		boxPanel.add(box);
		content = f.getContentPane();
		content.setLayout(new GridLayout(3, 1));
		content.add(labelPanel);
		content.add(boxPanel);
		content.add(buttonPanel);
		
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				butt.returnButton();
			}		
	     });
		
		continueButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				
		f.dispose();
		
		f = new JFrame("Customer Menu");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});          
		f.setVisible(true);
		
		JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton statementButton = new JButton("Display Bank Statement");
		statementButton.setPreferredSize(new Dimension(250, 20));
		
		statementPanel.add(statementButton);
		
		JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton lodgementButton = new JButton("Lodge money into account");
		lodgementPanel.add(lodgementButton);
		lodgementButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton withdrawButton = new JButton("Withdraw money from account");
		withdrawalPanel.add(withdrawButton);
		withdrawButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton returnButton = new JButton("Exit Customer Menu");
		returnPanel.add(returnButton);

		JLabel label1 = new JLabel("Please select an option");
		
		content = f.getContentPane();
		content.setLayout(new GridLayout(5, 1));
		content.add(label1);
		content.add(statementPanel);
		content.add(lodgementPanel);
		content.add(withdrawalPanel);
		content.add(returnPanel);
		
		statementButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				customer.statement();
			}
	     });
		
		lodgementButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				customer.lodgement();
			}	
	     });
		
		withdrawButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				customer.withdraw();
			}	
	     });
		
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				butt.returnButton();			
			}
	     });		}		
	     });
	}
		
	}
	
	public static ArrayList<Customer> returnArray(){
		return(customerList);
	}

	
	public void CustomerListEmpty(){
		JOptionPane.showMessageDialog(f, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
		f.dispose();
		admin();
	}
	
	public void CustomerAccountsEmpty(){
		JOptionPane.showMessageDialog(f, "This customer has no accounts! \n The admin must add acounts to this customer."   ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
		f.dispose();
		admin();
	}
	
	public void getArray(String response){
		if(customerList.isEmpty()){
			response = "true";
		}
		else{
			response = "false";
		}
	}
	
	public static boolean isNumeric(String str)  // a method that tests if a string is numeric
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}

