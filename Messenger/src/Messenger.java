import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Messenger extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

	// Create a text field for the user to type in
    JTextField textField = new JTextField();

    // Create a text area to display the conversation
    JTextArea textArea = new JTextArea();

    public Messenger() {
        // Set the title of the window
        setTitle("Messenger");

        // Set the size of the window
        setSize(400, 400);

        // Set the layout of the window
        setLayout(new BorderLayout());

        // Add the text field and text area to the window
        add(textField, BorderLayout.SOUTH);
        add(textArea, BorderLayout.CENTER);

        // Add an action listener to the text field
        textField.addActionListener(this);

        // Set the default close operation for the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the window to be visible
        setVisible(true);
    }

    // Handle the user pressing Enter in the text field
    public void actionPerformed(ActionEvent e) {
        // Get the text from the text field
        String text = textField.getText();

        // Append the text to the text area
        textArea.append(text + "\n");

        // Clear the text field
        textField.setText("");
    }

    public static void main(String[] args) {
        // Create a new instance of the Messenger program
        new Messenger();
    }
}
