import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StartMenuContinue extends JFrame{
	
	ArrayList<Customer> customerList = Menu.returnArray();
	CustomerAccount acc = Menu.returnAcc();

	public void newCustomer(){
		ButtonMenu butt = new ButtonMenu();
		Menu menu = new Menu();
		
		Menu.f.dispose();
		Menu.f1 = new JFrame("Create New Customer");
		Menu.f1.setSize(400, 300);
		Menu.f1.setLocation(200, 200);
		Menu.f1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		Container content = Menu.f1.getContentPane();
		content.setLayout(new BorderLayout());

		menu.firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
		menu.surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
		menu.pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
		menu.dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
		menu.firstNameTextField = new JTextField(20);
		menu.surnameTextField = new JTextField(20);
		menu.pPSTextField = new JTextField(20);
		menu.dOBTextField = new JTextField(20);
		JPanel panel = new JPanel(new GridLayout(6, 2));
		panel.add(menu.firstNameLabel);
		panel.add(menu.firstNameTextField);
		panel.add(menu.surnameLabel);
		panel.add(menu.surnameTextField);
		panel.add(menu.pPPSLabel);
		panel.add(menu.pPSTextField);
		panel.add(menu.dOBLabel);
		panel.add(menu.dOBTextField);

		menu.panel2 = new JPanel();
		menu.add = new JButton("Add");

		menu.add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				menu.PPS = menu.pPSTextField.getText();
				menu.firstName = menu.firstNameTextField.getText();
				menu.surname = menu.surnameTextField.getText();
				menu.DOB = menu.dOBTextField.getText();
				menu.setPassword("");

				menu.CustomerID = "ID" + menu.PPS;

				menu.add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Menu.f1.dispose();

						boolean loop = true;
						while (loop) {
							String password = JOptionPane.showInputDialog(Menu.f, "Enter 7 character Password;");
							menu.setPassword(password);

							if (password.length() != 7) {
								JOptionPane.showMessageDialog(null, null,
										"Password must be 7 charatcers long", JOptionPane.OK_OPTION);
							} else {
								loop = false;
							}
						}

						ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount>();

						Customer customer = new Customer(menu.PPS, menu.surname, menu.firstName, menu.DOB, menu.CustomerID, menu.getPassword(),
								accounts);

						customerList.add(customer);

						System.out.println(customerList);

						JOptionPane.showMessageDialog(Menu.f,
								"CustomerID = " + menu.CustomerID + "\n Password = " + menu.getPassword(),
								"Customer created.", JOptionPane.INFORMATION_MESSAGE);
						menu.menuStart();
						Menu.f.dispose();
						menu.menuStart();
					}
				});
			}
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				butt.returnButton();
			}
		});

		menu.panel2.add(menu.add);
		menu.panel2.add(cancel);

		content.add(panel, BorderLayout.CENTER);
		content.add(menu.panel2, BorderLayout.SOUTH);

		Menu.f1.setVisible(true);

	}
	
	public void administrator(){
		
		ButtonMenu butt = new ButtonMenu();
		Menu menu = new Menu();
		
		boolean loop = true, 
				loop2 = true;
		boolean cont = false;
		while (loop) {
			Object adminUsername = JOptionPane.showInputDialog(Menu.f, "Enter Administrator Username:");
			if (!adminUsername.equals("admin")) {
				int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					loop = true;
				} else if (reply == JOptionPane.NO_OPTION) {
					loop = false;
					loop2 = false;
					butt.returnButton();
				}
			} else {
				loop = false;
			}
		}
		while (loop2) {
			Object adminPassword = JOptionPane.showInputDialog(Menu.f, "Enter Administrator Password;");

			if (!adminPassword.equals("admin11")) {
				int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.NO_OPTION) {
					loop2 = false;
					butt.returnButton();
				}
			} else {
				loop2 = false;
				cont = true;
			}
		}

		if (cont) {
			loop = false;
			butt.returnAdmin();

		}
	}
	
	public void existingCustomer(){
		
		ButtonMenu butt = new ButtonMenu();
		Menu menu = new Menu();
		
		boolean loop = true, loop2 = true;
		boolean cont = false;
		boolean found = false;
		Customer customer = null;
		while (loop) {
			Object customerID = JOptionPane.showInputDialog(Menu.f, "Enter Customer ID:");

			for (Customer aCustomer : customerList) {

				if (aCustomer.getCustomerID().equals(customerID)) {
					found = true;
					customer = aCustomer;
				}
			}
			if (found == false) {
				int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					loop = true;
				} else if (reply == JOptionPane.NO_OPTION) {
					loop = false;
					loop2 = false;
					butt.returnButton();
				}
			} else {
				loop = false;
			}
		}
		while (loop2) {
			Object customerPassword = JOptionPane.showInputDialog(Menu.f, "Enter Customer Password;");

			if (!customer.getPassword().equals(customerPassword)) {
				int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.NO_OPTION) {
					loop2 = false;
					butt.returnButton();
				}
			} else {
				loop2 = false;
				cont = true;
			}
		}
		if (cont) {
			Menu.f.dispose();
			loop = false;
			menu.customer(customer);
		}
	}
}
