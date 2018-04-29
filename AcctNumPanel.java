import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AcctNumPanel extends JPanel 
{
	private ArrayList<BankAccount> bankAccts;
	private String[] nameAccts;
	private final double OVER_DRAFT_FEE = 15, RATE = .0025, TRANSACTION_FEE = 1.5, MIN_BAL = 300, MIN_BAL_FEE = 10;
	private final int FREE_TRANSACTIONS = 10;
	private JTextPane tpAccts;
	
	public boolean isNumeric(String str)
	{
		try
		{
			Double.parseDouble(str);
			return true;
		}
		catch (IllegalArgumentException e)
		{
			return false;
		}
	}
	
	public AcctNumPanel(ArrayList<BankAccount> bAccts)
	{
		bankAccts = bAccts;
		setSize(400, 300);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel lblName = new JLabel("Enter Name: ");
		add(lblName, gbc);
		gbc.gridx++;
		JTextField txtName = new JTextField();
		txtName.setPreferredSize(new Dimension(220, 20));
		add(txtName, gbc);
		gbc.gridx--;
		gbc.gridy++;
		JButton btnSubmit = new JButton("Submit");
		add(btnSubmit, gbc);
		gbc.gridy++;
		JLabel lblBlank = new JLabel(" ");
		add(lblBlank, gbc);
		gbc.gridy++;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		tpAccts = new JTextPane();
		tpAccts.setEditable(false);
		JScrollPane scpAccts = new JScrollPane(tpAccts);
		scpAccts.setPreferredSize(new Dimension(300, 100));
		add(scpAccts, gbc);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tpAccts.setText("Type\tNumber\tName\tBalance");
				int count = 0;
				for (int i = 0; i < bankAccts.size(); i++)
				{
					if (bankAccts.get(i).getName().equals(txtName.getText()))
					{
						tpAccts.setText(tpAccts.getText() + "\n" + bankAccts.get(i).toString());
						count++;
					}
				}
				if (count == 0)
				{
					tpAccts.setText("No Accounts Found");
				}
				txtName.setText("");
			}
		});
	}

}
