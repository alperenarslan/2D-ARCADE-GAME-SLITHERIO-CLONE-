package play;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tcp.Client;
import ui.RoundedFieldUI;

import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class LoginFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static LoginFrame loginFrame;
	

	private JPanel contentPane;
	private JTextField textField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	
	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setBackground(new Color(79, 138, 197));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(79, 138, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, -5, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, -5, SpringLayout.EAST, contentPane);
		contentPane.add(panel);
		
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		JLabel lblNewLabel = new JLabel(new ImageIcon("header.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBackground(Color.WHITE);
		panel.add(lblNewLabel);
		sl_panel.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblNewLabel, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel, 200, SpringLayout.NORTH, panel);
		
		textField = new JTextField(100);
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setUI ( new RoundedFieldUI () );
		textField.setText("username");
		textField.setForeground(Color.GRAY);
		sl_panel.putConstraint(SpringLayout.NORTH, textField, 50, SpringLayout.SOUTH, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.HORIZONTAL_CENTER, textField, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		panel.add(textField);
		textField.setColumns(10);
		textField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				 if (textField.getText().equals("username")) {
					 textField.setText("");
					 textField.setForeground(Color.WHITE);
			        }

				
			}
			public void focusLost(FocusEvent e) {
				if (textField.getText().isEmpty()) {
					textField.setForeground(Color.GRAY);
					textField.setText("username");
		        }
				
			}
		    });
		
		
		JButton btnPlay = new JButton("PLAY");
		btnPlay.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				Client client = null;
				try {
					client = new Client(username, 0, getBounds());
					setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} 
		});
		btnPlay.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnPlay.setBackground(new Color(50, 205, 50));
		btnPlay.setForeground(new Color(255, 255, 255));
		btnPlay.setOpaque(true);
		btnPlay.setBorderPainted(false);
		sl_panel.putConstraint(SpringLayout.NORTH, btnPlay, 50, SpringLayout.SOUTH, textField);
		sl_panel.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnPlay, 0, SpringLayout.HORIZONTAL_CENTER, textField);
		panel.add(btnPlay);

	}

	
}

