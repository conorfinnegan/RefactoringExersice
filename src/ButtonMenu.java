import java.util.ArrayList;
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

public class ButtonMenu extends JFrame {



	public void returnButton() {
		Menu menu1 = new Menu();
		Menu.f.dispose();
		menu1.menuStart();
	}

	public void returnAdmin() {
		Menu menu1 = new Menu();
		Menu.f.dispose();
		menu1.admin();
	}

	public void returnCustomer() {
		Menu menu1 = new Menu();
		Menu.f.dispose();
		menu1.customer(menu1.e);
	}

}
