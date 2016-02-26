import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	ArrayList<Customer> customerList = Menu.returnArray();
	Menu menu = new Menu();
	ButtonMenu butt = new ButtonMenu();
	CustomerAccount acc = Menu.returnAcc();

	public void bankCharges() {

		boolean loop = true;
		boolean found = false;

		if (customerList.isEmpty()) {
			CustomerListEmpty();
		} else {
			while (loop) {
				Object customerID = JOptionPane.showInputDialog(Menu.f,
						"Customer ID of Customer You Wish to Apply Charges to:");

				for (Customer aCustomer : customerList) {
					if (aCustomer.getCustomerID().equals(customerID)) {
						found = true;
						menu.customer = aCustomer;
						loop = false;
					}
				}
				if (found == false) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						loop = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						loop = false;
						butt.returnAdmin();
					}
				} else {
					Menu.f.dispose();
					Menu.f = new JFrame("Administrator Menu");
					Menu.f.setSize(400, 300);
					Menu.f.setLocation(200, 200);
					Menu.f.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					Menu.f.setVisible(true);

					JComboBox<String> box = new JComboBox<String>();
					for (int i = 0; i < menu.customer.getAccounts().size(); i++) {
						box.addItem(menu.customer.getAccounts().get(i).getNumber());
					}
					box.getSelectedItem();

					JPanel boxPanel = new JPanel();
					boxPanel.add(box);

					JPanel buttonPanel = new JPanel();
					JButton continueButton = new JButton("Apply Charge");
					JButton returnButton = new JButton("Return");
					buttonPanel.add(continueButton);
					buttonPanel.add(returnButton);
					Container content = Menu.f.getContentPane();
					content.setLayout(new GridLayout(2, 1));

					content.add(boxPanel);
					content.add(buttonPanel);

					if (menu.customer.getAccounts().isEmpty()) {
						CustomerAccountsEmpty();
					} else {
						for (int i = 0; i < menu.customer.getAccounts().size(); i++) {
							if (menu.customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
								acc = menu.customer.getAccounts().get(i);
							}
						}
						continueButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								String euro = "\u20ac";
								if (acc instanceof CustomerDepositAccount) {
									JOptionPane.showMessageDialog(Menu.f, "25" + euro + " deposit account fee aplied.",
											"", JOptionPane.INFORMATION_MESSAGE);
									acc.setBalance(acc.getBalance() - 25);
									JOptionPane.showMessageDialog(Menu.f, "New balance = " + acc.getBalance(),
											"Success!", JOptionPane.INFORMATION_MESSAGE);
								}
								if (acc instanceof CustomerCurrentAccount) {
									JOptionPane.showMessageDialog(Menu.f, "15" + euro + " current account fee aplied.",
											"", JOptionPane.INFORMATION_MESSAGE);
									acc.setBalance(acc.getBalance() - 25);
									JOptionPane.showMessageDialog(Menu.f, "New balance = " + acc.getBalance(),
											"Success!", JOptionPane.INFORMATION_MESSAGE);
								}
								butt.returnAdmin();
							}
						});
						returnButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								butt.returnButton();
							}
						});
					}
				}
			}
		}
	}

	public void interest() {
		boolean loop = true;
		boolean found = false;

		Menu menu = new Menu();

		if (customerList.isEmpty()) {
			CustomerListEmpty();
		} else {
			while (loop) {
				Object customerID = JOptionPane.showInputDialog(Menu.f,
						"Customer ID of Customer You Wish to Apply Interest to:");

				for (Customer aCustomer : customerList) {
					if (aCustomer.getCustomerID().equals(customerID)) {
						found = true;
						menu.customer = aCustomer;
						loop = false;
					}
				}
				if (found == false) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						loop = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						loop = false;
						butt.returnAdmin();
					}
				} else {
					Menu.f.dispose();
					Menu.f = new JFrame("Administrator Menu");
					Menu.f.setSize(400, 300);
					Menu.f.setLocation(200, 200);
					Menu.f.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					Menu.f.setVisible(true);

					JComboBox<String> box = new JComboBox<String>();
					for (int i = 0; i < menu.customer.getAccounts().size(); i++) {
						box.addItem(menu.customer.getAccounts().get(i).getNumber());
					}
					box.getSelectedItem();

					JPanel boxPanel = new JPanel();

					JLabel label = new JLabel("Select an account to apply interest to:");
					boxPanel.add(label);
					boxPanel.add(box);
					JPanel buttonPanel = new JPanel();
					JButton continueButton = new JButton("Apply Interest");
					JButton returnButton = new JButton("Return");
					buttonPanel.add(continueButton);
					buttonPanel.add(returnButton);
					Container content = Menu.f.getContentPane();
					content.setLayout(new GridLayout(2, 1));

					content.add(boxPanel);
					content.add(buttonPanel);

					if (menu.customer.getAccounts().isEmpty()) {
						CustomerAccountsEmpty();
					} else {

						for (int i = 0; i < menu.customer.getAccounts().size(); i++) {
							if (menu.customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
								acc = menu.customer.getAccounts().get(i);
							}
						}

						continueButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								String euro = "\u20ac";
								double interest = 0;
								boolean loop = true;

								while (loop) {
									String interestString = JOptionPane.showInputDialog(Menu.f,
											"Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'"); // numeric.
									if (isNumeric(interestString)) {
										interest = Double.parseDouble(interestString);
										loop = false;
										acc.setBalance(acc.getBalance() + (acc.getBalance() * (interest / 100)));
										JOptionPane.showMessageDialog(
												Menu.f, interest + "% interest applied. \n new balance = "
														+ acc.getBalance() + euro,
												"Success!", JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(Menu.f, "You must enter a numerical value!",
												"Oops!", JOptionPane.INFORMATION_MESSAGE);
									}
								}
								butt.returnAdmin();
							}
						});

						returnButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								butt.returnButton();
							}
						});

					}
				}
			}
		}
	}

	public void editCustomer() {
		boolean loop = true;
		boolean found = false;

		if (customerList.isEmpty()) {
			CustomerListEmpty();
		} else {
			while (loop) {
				Object customerID = JOptionPane.showInputDialog(Menu.f, "Enter Customer ID:");
				for (Customer aCustomer : customerList) {
					if (aCustomer.getCustomerID().equals(customerID)) {
						found = true;
						menu.customer = aCustomer;
					}
				}
				if (found == false) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						loop = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						Menu.f.dispose();
						loop = false;
						menu.admin();
					}
				} else {
					loop = false;
				}
			}
			Menu.f.dispose();

			Menu.f.dispose();
			Menu.f = new JFrame("Administrator Menu");
			Menu.f.setSize(400, 300);
			Menu.f.setLocation(200, 200);
			Menu.f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			});
			menu.firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
			menu.surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
			menu.pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
			menu.dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
			menu.customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
			menu.passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
			menu.firstNameTextField = new JTextField(20);
			menu.surnameTextField = new JTextField(20);
			menu.pPSTextField = new JTextField(20);
			menu.dOBTextField = new JTextField(20);
			menu.customerIDTextField = new JTextField(20);
			menu.passwordTextField = new JTextField(20);

			JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JPanel cancelPanel = new JPanel();

			textPanel.add(menu.firstNameLabel);
			textPanel.add(menu.firstNameTextField);
			textPanel.add(menu.surnameLabel);
			textPanel.add(menu.surnameTextField);
			textPanel.add(menu.pPPSLabel);
			textPanel.add(menu.pPSTextField);
			textPanel.add(menu.dOBLabel);
			textPanel.add(menu.dOBTextField);
			textPanel.add(menu.customerIDLabel);
			textPanel.add(menu.customerIDTextField);
			textPanel.add(menu.passwordLabel);
			textPanel.add(menu.passwordTextField);

			menu.firstNameTextField.setText(menu.customer.getFirstName());
			menu.surnameTextField.setText(menu.customer.getSurname());
			menu.pPSTextField.setText(menu.customer.getPPS());
			menu.dOBTextField.setText(menu.customer.getDOB());
			menu.customerIDTextField.setText(menu.customer.getCustomerID());
			menu.passwordTextField.setText(menu.customer.getPassword());

			JButton saveButton = new JButton("Save");
			JButton cancelButton = new JButton("Exit");

			cancelPanel.add(cancelButton, BorderLayout.SOUTH);
			cancelPanel.add(saveButton, BorderLayout.SOUTH);
			Container content = Menu.f.getContentPane();
			content.setLayout(new GridLayout(2, 1));
			content.add(textPanel, BorderLayout.NORTH);
			content.add(cancelPanel, BorderLayout.SOUTH);

			Menu.f.setContentPane(content);
			Menu.f.setSize(340, 350);
			Menu.f.setLocation(200, 200);
			Menu.f.setVisible(true);
			Menu.f.setResizable(false);

			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					menu.customer.setFirstName(menu.firstNameTextField.getText());
					menu.customer.setSurname(menu.surnameTextField.getText());
					menu.customer.setPPS(menu.pPSTextField.getText());
					menu.customer.setDOB(menu.dOBTextField.getText());
					menu.customer.setCustomerID(menu.customerIDTextField.getText());
					menu.customer.setPassword(menu.passwordTextField.getText());

					JOptionPane.showMessageDialog(null, "Changes Saved.");
				}
			});

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					butt.returnAdmin();
				}
			});
		}
	}

	public void summary() {
		Menu.f.dispose();

		Menu.f = new JFrame("Summary of Transactions");
		Menu.f.setSize(400, 700);
		Menu.f.setLocation(200, 200);
		Menu.f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		Menu.f.setVisible(true);

		JLabel label1 = new JLabel("Summary of all transactions: ");

		JPanel returnPanel = new JPanel();
		JButton returnButton = new JButton("Return");
		returnPanel.add(returnButton);

		JPanel textPanel = new JPanel();

		textPanel.setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea(40, 20);
		textArea.setEditable(false);
		textPanel.add(label1, BorderLayout.NORTH);
		textPanel.add(textArea, BorderLayout.CENTER);
		textPanel.add(returnButton, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane(textArea);
		textPanel.add(scrollPane);
		for (int a = 0; a < customerList.size(); a++) {
			for (int b = 0; b < customerList.get(a).getAccounts().size(); b++) {
				acc = customerList.get(a).getAccounts().get(b);
				for (int c = 0; c < customerList.get(a).getAccounts().get(b).getTransactionList().size(); c++) {
					textArea.append(acc.getTransactionList().get(c).toString());
				}
			}
		}
		textPanel.add(textArea);

		Container content = Menu.f.getContentPane();
		content.setLayout(new GridLayout(1, 1));
		content.add(textPanel);

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				butt.returnAdmin();
			}
		});
	}

	public void navigate() {
		Menu.f.dispose();
		int newPosition = menu.position;

		if (customerList.isEmpty()) {
			CustomerListEmpty();
		} else {
			JButton first, previous, next, last, cancel;
			JPanel gridPanel, buttonPanel, cancelPanel;

			Container content = getContentPane();

			content.setLayout(new BorderLayout());

			buttonPanel = new JPanel();
			gridPanel = new JPanel(new GridLayout(8, 2));
			cancelPanel = new JPanel();

			menu.firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
			menu.surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
			menu.pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
			menu.dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
			menu.customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
			menu.passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
			menu.firstNameTextField = new JTextField(20);
			menu.surnameTextField = new JTextField(20);
			menu.pPSTextField = new JTextField(20);
			menu.dOBTextField = new JTextField(20);
			menu.customerIDTextField = new JTextField(20);
			menu.passwordTextField = new JTextField(20);

			first = new JButton("First");
			previous = new JButton("Previous");
			next = new JButton("Next");
			last = new JButton("Last");
			cancel = new JButton("Cancel");

			menu.firstNameTextField.setText(customerList.get(0).getFirstName());
			menu.surnameTextField.setText(customerList.get(0).getSurname());
			menu.pPSTextField.setText(customerList.get(0).getPPS());
			menu.dOBTextField.setText(customerList.get(0).getDOB());
			menu.customerIDTextField.setText(customerList.get(0).getCustomerID());
			menu.passwordTextField.setText(customerList.get(0).getPassword());

			menu.firstNameTextField.setEditable(false);
			menu.surnameTextField.setEditable(false);
			menu.pPSTextField.setEditable(false);
			menu.dOBTextField.setEditable(false);
			menu.customerIDTextField.setEditable(false);
			menu.passwordTextField.setEditable(false);

			gridPanel.add(menu.firstNameLabel);
			gridPanel.add(menu.firstNameTextField);
			gridPanel.add(menu.surnameLabel);
			gridPanel.add(menu.surnameTextField);
			gridPanel.add(menu.pPPSLabel);
			gridPanel.add(menu.pPSTextField);
			gridPanel.add(menu.dOBLabel);
			gridPanel.add(menu.dOBTextField);
			gridPanel.add(menu.customerIDLabel);
			gridPanel.add(menu.customerIDTextField);
			gridPanel.add(menu.passwordLabel);
			gridPanel.add(menu.passwordTextField);

			buttonPanel.add(first);
			buttonPanel.add(previous);
			buttonPanel.add(next);
			buttonPanel.add(last);

			cancelPanel.add(cancel);

			content.add(gridPanel, BorderLayout.NORTH);
			content.add(buttonPanel, BorderLayout.CENTER);
			content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);
			first.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					menu.position = 0;
					menu.firstNameTextField.setText(customerList.get(0).getFirstName());
					menu.surnameTextField.setText(customerList.get(0).getSurname());
					menu.pPSTextField.setText(customerList.get(0).getPPS());
					menu.dOBTextField.setText(customerList.get(0).getDOB());
					menu.customerIDTextField.setText(customerList.get(0).getCustomerID());
					menu.passwordTextField.setText(customerList.get(0).getPassword());
				}
			});

			previous.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					menu.position = newPosition - 1;

					menu.firstNameTextField.setText(customerList.get(newPosition).getFirstName());
					menu.surnameTextField.setText(customerList.get(newPosition).getSurname());
					menu.pPSTextField.setText(customerList.get(newPosition).getPPS());
					menu.dOBTextField.setText(customerList.get(newPosition).getDOB());
					menu.customerIDTextField.setText(customerList.get(newPosition).getCustomerID());
					menu.passwordTextField.setText(customerList.get(newPosition).getPassword());
				}
			});

			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					menu.position = menu.position + 1;

					menu.firstNameTextField.setText(customerList.get(menu.position).getFirstName());
					menu.surnameTextField.setText(customerList.get(menu.position).getSurname());
					menu.pPSTextField.setText(customerList.get(menu.position).getPPS());
					menu.dOBTextField.setText(customerList.get(menu.position).getDOB());
					menu.customerIDTextField.setText(customerList.get(menu.position).getCustomerID());
					menu.passwordTextField.setText(customerList.get(menu.position).getPassword());
				}
			});

			last.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					menu.position = customerList.size() - 1;

					menu.firstNameTextField.setText(customerList.get(menu.position).getFirstName());
					menu.surnameTextField.setText(customerList.get(menu.position).getSurname());
					menu.pPSTextField.setText(customerList.get(menu.position).getPPS());
					menu.dOBTextField.setText(customerList.get(menu.position).getDOB());
					menu.customerIDTextField.setText(customerList.get(menu.position).getCustomerID());
					menu.passwordTextField.setText(customerList.get(menu.position).getPassword());
				}
			});

			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					butt.returnAdmin();
				}
			});

			setContentPane(content);
			setSize(400, 300);
			setVisible(true);
		}
	}

	public void createAccount() {
		Menu.f.dispose();

		if (customerList.isEmpty()) {
			CustomerListEmpty();
		} else {
			boolean loop = true;
			boolean found = false;

			while (loop) {
				Object customerID = JOptionPane.showInputDialog(Menu.f,
						"Customer ID of Customer You Wish to Add an Account to:");
				for (Customer aCustomer : customerList) {
					if (aCustomer.getCustomerID().equals(customerID)) {
						found = true;
						menu.customer = aCustomer;
					}
				}
				if (found == false) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						loop = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						Menu.f.dispose();
						loop = false;
						menu.admin();
					}
				} else {
					loop = false;
					String[] choices = { "Current Account", "Deposit Account" };
					String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
							"Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);
					if (account.equals("Current Account")) {
						boolean valid = true;
						double balance = 0;
						String number = String.valueOf("C" + (customerList.indexOf(menu.customer) + 1) * 10
								+ (menu.customer.getAccounts().size() + 1));
						ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
						int randomPIN = (int) (Math.random() * 9000) + 1000;
						String pin = String.valueOf(randomPIN);

						ATMCard atm = new ATMCard(randomPIN, valid);

						CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance,
								transactionList);

						menu.customer.getAccounts().add(current);
						JOptionPane.showMessageDialog(Menu.f, "Account number = " + number + "\n PIN = " + pin,
								"Account created.", JOptionPane.INFORMATION_MESSAGE);

						Menu.f.dispose();
						menu.admin();
					}
					if (account.equals("Deposit Account")) {

						double balance = 0, interest = 0;
						String number = String.valueOf("D" + (customerList.indexOf(menu.customer) + 1) * 10
								+ (menu.customer.getAccounts().size() + 1));
						ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();

						CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance,
								transactionList);

						menu.customer.getAccounts().add(deposit);
						JOptionPane.showMessageDialog(Menu.f, "Account number = " + number, "Account created.",
								JOptionPane.INFORMATION_MESSAGE);

						Menu.f.dispose();
						menu.admin();
					}
				}
			}
		}
	}

	public void deleteCustomer() {
		boolean found = true;

		if (customerList.isEmpty()) {
			CustomerListEmpty();
		} else {
			Object customerID = JOptionPane.showInputDialog(Menu.f, "Customer ID of Customer You Wish to Delete:");
			for (Customer aCustomer : customerList) {
				if (aCustomer.getCustomerID().equals(customerID)) {
					found = true;
					menu.customer = aCustomer;
				}
			}
			if (found == false) {
				int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
				} else if (reply == JOptionPane.NO_OPTION) {
					Menu.f.dispose();
					menu.admin();
				}
			} else {
				if (menu.customer.getAccounts().size() > 0) {
					JOptionPane.showMessageDialog(Menu.f,
							"This customer has accounts. \n You must delete a customer's accounts before deleting a customer ",
							"Oops!", JOptionPane.INFORMATION_MESSAGE);
				} else {
					customerList.remove(menu.customer);
					JOptionPane.showMessageDialog(Menu.f, "Customer Deleted ", "Success.",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	public void deleteAccount() {
		boolean found = true;
		{
			Object customerID = JOptionPane.showInputDialog(Menu.f,
					"Customer ID of Customer from which you wish to delete an account");
			for (Customer aCustomer : customerList) {
				if (aCustomer.getCustomerID().equals(customerID)) {
					found = true;
					menu.customer = aCustomer;
				}
			}
			if (found == false) {
				int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
				} else if (reply == JOptionPane.NO_OPTION) {
					Menu.f.dispose();
					menu.admin();
				}
			}
		}
	}

	public void CustomerListEmpty() {
		JOptionPane.showMessageDialog(Menu.f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
		butt.returnAdmin();
	}

	public void CustomerAccountsEmpty() {
		JOptionPane.showMessageDialog(Menu.f,
				"This customer has no accounts! \n The admin must add acounts to this customer.", "Oops!",
				JOptionPane.INFORMATION_MESSAGE);
		butt.returnAdmin();
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
