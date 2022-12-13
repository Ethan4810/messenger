

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.border.LineBorder;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JLabel lbWelcomeUser;
	private JLabel lbID;
	private JLabel lbPwd;

	private JTextField tfID;
	private JPasswordField pfPwd;

	/* Button panel. */
	private JButton btnLogin;
	private JLabel lbSignup;
	private JButton btnSignup;

	public static String curUserId; // declare current userInfo id

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialogLogin = new Login(null);

			UserInfo userInfo = dialogLogin.userInfo;

			if (userInfo != null) {
				System.out.println("Login success!");
				System.out.println("ID: " + userInfo.id);
				System.out.println("Name: " + userInfo.name);
				System.out.println("Email: " + userInfo.email);
				System.out.println("Phone: " + userInfo.phone);
			} else {
				System.out.println("Login canceled.");
			}

			dialogLogin.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialogLogin.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the Login dialog.
	 */
	public Login(JFrame parent) {
		super(parent);
		setResizable(false);
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Login");
		setBounds(590, 160, 590, 780);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 550, 620);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		setModal(true);
		contentPanel.setLayout(null);

		{
			lbWelcomeUser = new JLabel(" Sign in to Twitter");
			ImageIcon twitterImageIcon = new ImageIcon(Login.class.getResource("/images/twitter.png"));
			Image twitterImage = twitterImageIcon.getImage();
			Image newTwitterImage = twitterImage.getScaledInstance(55, 55, java.awt.Image.SCALE_SMOOTH);
			twitterImageIcon = new ImageIcon(newTwitterImage);
			lbWelcomeUser.setIcon(twitterImageIcon);
			lbWelcomeUser.setFont(new Font("Lucida Grande", Font.BOLD, 28));
			lbWelcomeUser.setBackground(new Color(255, 255, 255));
			lbWelcomeUser.setHorizontalAlignment(SwingConstants.CENTER);
			lbWelcomeUser.setBounds(6, 6, 538, 57);
			contentPanel.add(lbWelcomeUser);
		}

		lbID = new JLabel("ID");
		lbID.setBackground(new Color(255, 255, 255));
		lbID.setBounds(105, 175, 340, 30);
		lbID.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPanel.add(lbID);

		tfID = new JTextField();
		tfID.setBackground(new Color(255, 255, 255));
		tfID.setBorder(new LineBorder(new Color(29, 156, 239), 1, true));
		tfID.setBounds(105, 205, 340, 30);
		lbID.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPanel.add(tfID);

		lbPwd = new JLabel("Password");
		lbPwd.setBackground(new Color(255, 255, 255));
		lbPwd.setBounds(105, 255, 340, 30);
		lbPwd.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPanel.add(lbPwd);

		pfPwd = new JPasswordField();
		pfPwd.setBackground(new Color(255, 255, 255));
		pfPwd.setBorder(new LineBorder(new Color(29, 156, 239), 1, true));
		pfPwd.setBounds(105, 285, 340, 30);
		pfPwd.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPanel.add(pfPwd);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBounds(0, 620, 550, 100);
			getContentPane().add(buttonPane);

			btnSignup = new JButton("Sign up");
			btnSignup.setFont(new Font("Arial", Font.PLAIN, 15));
			btnSignup.setForeground(new Color(29, 156, 239));
			btnSignup.setOpaque(true);
			btnSignup.setBorderPainted(false);
			btnSignup.setBounds(320, 60, 105, 30);
			btnSignup.setBackground(new Color(255, 255, 255));
			btnSignup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new SignUp(parent);
				}
			});
			{
				btnLogin = new JButton("Login");
				btnLogin.setFont(new Font("Arial", Font.BOLD, 17));
				btnLogin.setForeground(new Color(255, 255, 255));
				btnLogin.setBackground(new Color(0, 0, 0));
				btnLogin.setOpaque(true);
				btnLogin.setBorderPainted(false);
				btnLogin.setBounds(125, 10, 300, 40);
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String id = tfID.getText();
						String pwd = String.valueOf(pfPwd.getPassword());

						userInfo = authenticateUser(id, pwd);
						curUserId = userInfo.id;

						// save userInfo id for other functions!!!
						if (userInfo != null) {
							JOptionPane.showMessageDialog(Login.this, "Hello! " + userInfo.id, "Login Success",
									JOptionPane.INFORMATION_MESSAGE);
							System.out.println("(Login) current userInfo id = " + curUserId); // for debugging
							setVisible(true);

							dispose();
						} else {
							JOptionPane.showMessageDialog(Login.this, "ID or Password Invalid.", "Login Failed",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				buttonPane.setLayout(null);
				buttonPane.add(btnLogin);
				getRootPane().setDefaultButton(btnLogin);
			}
			buttonPane.add(btnSignup);

			lbSignup = new JLabel("Don't have an account?");
			lbSignup.setForeground(new Color(51, 51, 50));
			lbSignup.setFont(new Font("Arial", Font.PLAIN, 13));
			lbSignup.setHorizontalAlignment(SwingConstants.CENTER);
			lbSignup.setBounds(125, 60, 180, 30);
			buttonPane.add(lbSignup);
		}

		setVisible(true);
	}

	/**
	 * Authenticate the userInfo.
	 */
	public UserInfo userInfo;

	private UserInfo authenticateUser(String id, String pwd) {
		UserInfo userInfo = null;

		final String DB_URL = "jdbc:mysql://localhost/twitter_3rd";
		final String USERNAME = "root";
		final String PASSWORD = "msNjs0330";

		try {
			Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			// connected to database successfully...

			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM userInfo WHERE usr_id = ? AND usr_pwd = ?";

			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pwd);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				userInfo = new UserInfo();
				userInfo.id = resultSet.getString("usr_id");
				userInfo.pwd = resultSet.getString("usr_pwd");
				userInfo.name = resultSet.getString("usr_name");
				userInfo.email = resultSet.getString("usr_email");
				userInfo.phone = resultSet.getString("usr_phone");
				userInfo.birthdate = resultSet.getString("usr_birthdate");
			}

			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return userInfo;
	}
}