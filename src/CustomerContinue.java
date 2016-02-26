import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomerContinue {

	public void continueCust() {

		Menu menu = new Menu();
		CustomerMethods custMeth = new CustomerMethods();
		ButtonMenu butt = new ButtonMenu();

		Menu.f.dispose();

		Menu.f = new JFrame("Customer Menu");
		Menu.f.setSize(400, 300);
		Menu.f.setLocation(200, 200);
		Menu.f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		Menu.f.setVisible(true);

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

		menu.content = Menu.f.getContentPane();
		menu.content.setLayout(new GridLayout(5, 1));
		menu.content.add(label1);
		menu.content.add(statementPanel);
		menu.content.add(lodgementPanel);
		menu.content.add(withdrawalPanel);
		menu.content.add(returnPanel);

		statementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				custMeth.statement();
			}
		});

		lodgementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				custMeth.lodgement();
			}
		});

		withdrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				custMeth.withdraw();
			}
		});

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				butt.returnCustomer();
			}
		});
	}
}
