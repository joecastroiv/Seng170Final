package WindowBuilder.views;
import WindowBuilder.common.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import WindowBuilder.common.LoginChecker;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class userLogin extends JFrame {

	private JPanel ctpMain;
	private JTextField txtLogin;
	private JButton btnLogin;
	private JPasswordField txtPass;
	private String secretPass = "pizza";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userLogin frame = new userLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public userLogin() {
		
		initComponents();
		createEvents();
		
	}
	
	private void initComponents() 
	{
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 200);
		ctpMain = new JPanel();
		ctpMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ctpMain);
		
		
		JLabel lblLogin = new JLabel("Enter Login: ");
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		
		JLabel lblPass = new JLabel("Enter Secret Pass: ");
		
		JTextPane txtpnHintPassIs = new JTextPane();
		txtpnHintPassIs.setBackground(SystemColor.menu);
		txtpnHintPassIs.setText("Hint: Pass is same as entering node");
		
		btnLogin = new JButton("Login");
		
		txtPass = new JPasswordField();
		GroupLayout gl_ctpMain = new GroupLayout(ctpMain);
		gl_ctpMain.setHorizontalGroup(
			gl_ctpMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ctpMain.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_ctpMain.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addGroup(gl_ctpMain.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblLogin)
								.addComponent(lblPass))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtPass)
								.addComponent(txtLogin))
							.addGap(58))
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addComponent(txtpnHintPassIs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(36))
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(105))))
		);
		gl_ctpMain.setVerticalGroup(
			gl_ctpMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ctpMain.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_ctpMain.createParallelGroup(Alignment.BASELINE, false)
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addGap(6)
							.addComponent(lblLogin))
						.addComponent(txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_ctpMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPass)
						.addComponent(txtPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addComponent(txtpnHintPassIs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		ctpMain.setLayout(gl_ctpMain);
		
	}
	
	private void createEvents() 
	{
		//Login button; check login with regex than see if typed login matches stored login
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
		
				String userLogin = txtLogin.getText();
				String pass = txtPass.getText();
				
				//regex Letters only for login capital or lowercase
				if(Pattern.matches("[a-zA-Z]+", userLogin))
				{
					System.out.println("Yes, string contains letters only");
				}
				else {
					JOptionPane.showMessageDialog(null, "Should only contain Letters, Invald Login!!!");
				}
							
				
				try 
				{
					if(LoginChecker.isMatchLogin(userLogin.trim()) && pass.trim().equals(secretPass)) 
					{
						driveErrorsTool driveErrors = new driveErrorsTool();
						driveErrors.setVisible(true);
					} 
					else 
					{
						JOptionPane.showMessageDialog(null, "Invalid Login, not a valid account!!!");
					}
				} catch (HeadlessException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
}
