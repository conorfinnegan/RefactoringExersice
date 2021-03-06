import java.util.ArrayList;
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CustomerMethods extends JFrame {

	ArrayList<Customer> customerList = Menu.returnArray();
	CustomerAccount acc = Menu.returnAcc();

	public void statement() {
		Menu menu = new Menu();
		Menu.f.dispose();
		Menu.f = new JFrame("Customer Menu");
		Menu.f.setSize(400, 600);
		Menu.f.setLocation(200, 200);
		Menu.f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		Menu.f.setVisible(true);

		JLabel label1 = new JLabel("Summary of account transactions: ");

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

		for (int i = 0; i < acc.getTransactionList().size(); i++) {
			textArea.append(acc.getTransactionList().get(i).toString());
		}

		textPanel.add(textArea);

		Container content = Menu.f.getContentPane();
		content.setLayout(new GridLayout(1, 1));
		content.add(textPanel);

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Menu.f.dispose();
				menu.customer(menu.e);
			}
		});
	}

	@SuppressWarnings("unchecked")
	public void lodgement() {
		Menu menu = new Menu();

		boolean loop = true;
		boolean on = true;
		double balance = 0;

		if (acc instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
			loop = true;

			while (loop) {
				if (count == 0) {
					JOptionPane.showMessageDialog(Menu.f, "Pin entered incorrectly 3 times. ATM card locked.", "Pin",
							JOptionPane.INFORMATION_MESSAGE);
					((CustomerCurrentAccount) acc).getAtm().setValid(false);
					menu.customer(menu.e);
					loop = false;
					on = false;
				}
				String Pin = JOptionPane.showInputDialog(Menu.f, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);
				if (on) {
					if (checkPin == i) {
						loop = false;
						JOptionPane.showMessageDialog(Menu.f, "Pin entry successful", "Pin",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						count--;
						JOptionPane.showMessageDialog(Menu.f, "Incorrect pin. " + count + " attempts remaining.", "Pin",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}

		}
		if (on == true) {
			String balanceTest = JOptionPane.showInputDialog(Menu.f, "Enter amount you wish to lodge:");
			if (isNumeric(balanceTest)) {
				balance = Double.parseDouble(balanceTest);
				loop = false;
			} else {
				JOptionPane.showMessageDialog(Menu.f, "You must enter a numerical value!", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
			}

			String euro = "\u20ac";
			acc.setBalance(acc.getBalance() + balance);
			Date date = new Date();
			String date2 = date.toString();
			String type = "Lodgement";
			double amount = balance;

			AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			acc.getTransactionList().add(transaction);

			JOptionPane.showMessageDialog(Menu.f, balance + euro + " added do you account!", "Lodgement",
					JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(Menu.f, "New balance = " + acc.getBalance() + euro, "Lodgement",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@SuppressWarnings("unchecked")
	public void withdraw() {
		Menu menu = new Menu();
		boolean loop = true;
		boolean on = true;
		double withdraw = 0;
		if (acc instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
			loop = true;
			while (loop) {
				if (count == 0) {
					JOptionPane.showMessageDialog(Menu.f, "Pin entered incorrectly 3 times. ATM card locked.", "Pin",
							JOptionPane.INFORMATION_MESSAGE);
					((CustomerCurrentAccount) acc).getAtm().setValid(false);
					menu.customer(menu.e);
					loop = false;
					on = false;
				}
				String Pin = JOptionPane.showInputDialog(Menu.f, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);
				if (on) {
					if (checkPin == i) {
						loop = false;
						JOptionPane.showMessageDialog(Menu.f, "Pin entry successful", "Pin",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						count--;
						JOptionPane.showMessageDialog(Menu.f, "Incorrect pin. " + count + " attempts remaining.", "Pin",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
		if (on == true) {
			String balanceTest = JOptionPane.showInputDialog(Menu.f, "Enter amount you wish to withdraw (max 500):");
			if (isNumeric(balanceTest)) {
				withdraw = Double.parseDouble(balanceTest);
				loop = false;

			} else {
				JOptionPane.showMessageDialog(Menu.f, "You must enter a numerical value!", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if (withdraw > 500) {
				JOptionPane.showMessageDialog(Menu.f, "500 is the maximum you can withdraw at a time.", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}
			if (withdraw > acc.getBalance()) {
				JOptionPane.showMessageDialog(Menu.f, "Insufficient funds.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}
			String euro = "\u20ac";
			acc.setBalance(acc.getBalance() - withdraw);
			Date date = new Date();
			String date2 = date.toString();

			String type = "Withdraw";
			double amount = withdraw;

			AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			System.out.println(transaction);
			acc.getTransactionList().add(transaction);

			JOptionPane.showMessageDialog(Menu.f, withdraw + euro + " withdrawn.", "Withdraw",
					JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(Menu.f, "New balance = " + acc.getBalance() + euro, "Withdraw",
					JOptionPane.INFORMATION_MESSAGE);
		}
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
