package kr.ac.gachon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.border.LineBorder;

public class SignUp extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JLabel lbWelcomeMsg;
	private JLabel lbLogin;

	private JTextField tfID;
	private JPasswordField pfPwd;
	private JPasswordField pfCnfPwd;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfPhone;
	private JTextField tfBirthdate;

	private JButton btnSignup;
	private JButton btnLogin;
	
	public static String curUserId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SignUp dialogSignup = new SignUp(null);

			UserInfo userInfo = dialogSignup.userInfo;

			if (userInfo != null) {
				System.out.println("Sign up success!");
				System.out.println("ID: " + userInfo.id);
				System.out.println("Name: " + userInfo.name);
				System.out.println("Email: " + userInfo.email);
				System.out.println("Phone: " + userInfo.phone);
			} else {
				System.out.println("Sign up canceled.");
			}

			dialogSignup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the Sign up dialog.
	 */
	public SignUp(JFrame parent) {
		super(parent);
		setResizable(false);
		setBackground(new Color(255, 255, 255));
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Sign up");
		setBounds(590, 160, 590, 780);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 550, 620);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		setModal(true);
		contentPanel.setLayout(null);

		{
			lbWelcomeMsg = new JLabel(" Join Twitter today");
			ImageIcon twitterImageIcon = new ImageIcon(SignUp.class.getResource("/images/twitter.png"));
			Image twitterImage = twitterImageIcon.getImage();
			Image newTwitterImage = twitterImage.getScaledInstance(55, 55, java.awt.Image.SCALE_SMOOTH);
			twitterImageIcon = new ImageIcon(newTwitterImage);
			lbWelcomeMsg.setIcon(twitterImageIcon);
			lbWelcomeMsg.setBounds(6, 6, 538, 66);
			contentPanel.add(lbWelcomeMsg);
			lbWelcomeMsg.setBackground(new Color(255, 255, 255));
			lbWelcomeMsg.setFont(new Font("Dialog", Font.BOLD, 28));
			lbWelcomeMsg.setHorizontalAlignment(SwingConstants.CENTER);
		}

		{
			JLabel lbName = new JLabel("Name*");
			lbName.setFont(new Font("Arial", Font.PLAIN, 15));
			lbName.setBounds(105, 105, 340, 30);
			contentPanel.add(lbName);
		}
		{
			tfName = new JTextField();
			tfName.setFont(new Font("Arial", Font.PLAIN, 15));
			tfName.setBorder(new LineBorder(new Color(29, 156, 239), 1, true));
			tfName.setBounds(105, 135, 340, 30);
			contentPanel.add(tfName);
			tfName.setColumns(10);
		}
		{
			JLabel lbID = new JLabel("ID*");
			lbID.setFont(new Font("Arial", Font.PLAIN, 15));
			lbID.setBounds(105, 175, 340, 30);
			contentPanel.add(lbID);
		}
		{
			tfID = new JTextField();
			tfID.setFont(new Font("Arial", Font.PLAIN, 15));
			tfID.setBorder(new LineBorder(new Color(29, 156, 239), 1, true));
			tfID.setBounds(105, 205, 340, 30);
			contentPanel.add(tfID);
			tfID.setColumns(10);
		}
		{
			JLabel lbPwd = new JLabel("Password*");
			lbPwd.setFont(new Font("Arial", Font.PLAIN, 15));
			lbPwd.setBounds(105, 245, 340, 30);
			contentPanel.add(lbPwd);
		}
		{
			pfPwd = new JPasswordField();
			pfPwd.setFont(new Font("Arial", Font.PLAIN, 15));
			pfPwd.setBorder(new LineBorder(new Color(29, 156, 239), 1, true));
			pfPwd.setBounds(105, 275, 340, 30);
			contentPanel.add(pfPwd);
		}
		{
			JLabel lbCnfPwd = new JLabel("Confirm Password*");
			lbCnfPwd.setFont(new Font("Arial", Font.PLAIN, 15));
			lbCnfPwd.setBounds(105, 315, 340, 30);
			contentPanel.add(lbCnfPwd);
		}
		{
			pfCnfPwd = new JPasswordField();
			pfCnfPwd.setFont(new Font("Arial", Font.PLAIN, 15));
			pfCnfPwd.setBorder(new LineBorder(new Color(29, 156, 239), 1, true));
			pfCnfPwd.setBounds(105, 345, 340, 30);
			contentPanel.add(pfCnfPwd);
		}
		{
			JLabel lbEmail = new JLabel("Email*");
			lbEmail.setFont(new Font("Arial", Font.PLAIN, 15));
			lbEmail.setBounds(105, 385, 340, 30);
			contentPanel.add(lbEmail);
		}
		{
			tfEmail = new JTextField();
			tfEmail.setFont(new Font("Arial", Font.PLAIN, 15));
			tfEmail.setBorder(new LineBorder(new Color(29, 156, 239), 1, true));
			tfEmail.setBounds(105, 415, 340, 30);
			contentPanel.add(tfEmail);
			tfEmail.setColumns(10);
		}
		{
			JLabel lbPhone = new JLabel("Phone*");
			lbPhone.setFont(new Font("Arial", Font.PLAIN, 15));
			lbPhone.setBounds(105, 455, 340, 30);
			contentPanel.add(lbPhone);
		}
		{
			tfPhone = new JTextField();
			tfPhone.setFont(new Font("Arial", Font.PLAIN, 15));
			tfPhone.setBorder(new LineBorder(new Color(29, 156, 239), 1, true));
			tfPhone.setBounds(105, 485, 340, 30);
			contentPanel.add(tfPhone);
			tfPhone.setColumns(10);
		}
		{
			JLabel lbBirthdate = new JLabel("Date of birth*");
			lbBirthdate.setFont(new Font("Arial", Font.PLAIN, 15));
			lbBirthdate.setBounds(105, 525, 340, 30);
			contentPanel.add(lbBirthdate);
		}
		{
			tfBirthdate = new JTextField();
			tfBirthdate.setFont(new Font("Arial", Font.PLAIN, 15));
			tfBirthdate.setBorder(new LineBorder(new Color(29, 156, 239), 1, true));
			tfBirthdate.setBounds(105, 555, 340, 30);
			contentPanel.add(tfBirthdate);
			tfBirthdate.setColumns(10);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 620, 550, 100);
			getContentPane().add(buttonPane);
			buttonPane.setBackground(new Color(255, 255, 255));
			{
				btnSignup = new JButton("Sign up");
				btnSignup.setFont(new Font("Arial", Font.BOLD, 17));
				btnSignup.setForeground(new Color(255, 255, 255));
				btnSignup.setOpaque(true);
				btnSignup.setBorderPainted(false);
				btnSignup.setBackground(new Color(0, 0, 0));
				btnSignup.setBounds(125, 10, 300, 40);
				btnSignup.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						signupUser();
						curUserId = userInfo.id;
						dispose();
						new Login(parent);
					}
				});
				buttonPane.setLayout(null);
				buttonPane.add(btnSignup);
				getRootPane().setDefaultButton(btnSignup);
			}
			{
				btnLogin = new JButton("Login");
				btnLogin.setFont(new Font("Arial", Font.PLAIN, 15));
				btnLogin.setForeground(new Color(29, 156, 239));
				btnLogin.setOpaque(true);
				btnLogin.setBorderPainted(false);
				btnLogin.setBounds(320, 60, 105, 30);
				btnLogin.setBackground(new Color(255, 255, 255));
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						new Login(parent);
					}
				});
				buttonPane.add(btnLogin);
			}
			{
				lbLogin = new JLabel("Have an account already?");
				lbLogin.setForeground(new Color(51, 51, 50));
				lbLogin.setFont(new Font("Arial", Font.PLAIN, 13));
				lbLogin.setHorizontalAlignment(SwingConstants.CENTER);
				lbLogin.setBounds(125, 60, 180, 30);
				buttonPane.add(lbLogin);
			}
		}

		setVisible(true);
	}

	/**
	 * Sign up userInfo.
	 */
	private void signupUser() {
		String id = tfID.getText(); // usr_id
		String pwd = String.valueOf(pfPwd.getPassword()); // usr_pwd
		String confirmPwd = String.valueOf(pfCnfPwd.getPassword()); // usr_pwd
		String name = tfName.getText(); // usr_name
		String email = tfEmail.getText(); // usr_email
		String phone = tfPhone.getText(); // usr_phone
		String birthdate = tfBirthdate.getText(); // usr_birthdate

		if (id.isEmpty() || pwd.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty()
				|| birthdate.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter all fields.", "Sign up Failed",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!pwd.equals(confirmPwd)) {
			JOptionPane.showMessageDialog(this, "Confirm password does not match.", "Sign up Failed",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		userInfo = addUserToDB(id, pwd, name, email, phone, birthdate);
		if (userInfo != null) {
			JOptionPane.showMessageDialog(this, "Registered a new userInfo.", "Sign up Success",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Failed to register new userInfo.", "Sign up Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Add userInfo to database.
	 */
	public UserInfo userInfo;

	private UserInfo addUserToDB(String id, String pwd, String name, String email, String phone, String birthdate) {
		UserInfo userInfo = null;

		final String DB_URL = "jdbc:mysql://localhost/twitter_3rd";
		final String USERNAME = "root";
		final String PASSWORD = "msNjs0330";

		try {
			Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			// connected to database successfully...

			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO userInfo(usr_id, usr_pwd, usr_name, usr_email, usr_phone, usr_birthdate)"
					+ "VALUES(?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pwd);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, birthdate);

			// insert row into the userInfo table
			int addedRows = preparedStatement.executeUpdate();
			if (addedRows > 0) {
				userInfo = new UserInfo();
				userInfo.id = id;
				userInfo.pwd = pwd;
				userInfo.name = name;
				userInfo.email = email;
				userInfo.phone = phone;
				userInfo.birthdate = birthdate;
			}

			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userInfo;
	}
}