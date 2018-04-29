import java.util.ArrayList;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class DepositPanel extends JPanel 
{
	private ArrayList<BankAccount> bankAccts;
	private final double OVER_DRAFT_FEE = 15, RATE = .0025, TRANSACTION_FEE = 1.5, MIN_BAL = 300, MIN_BAL_FEE = 10;
	private final int FREE_TRANSACTIONS = 10;
	private JPanel thisPanel = this;
	
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
	
	public boolean inArrayList(String str, ArrayList<BankAccount> accts)
	{
		try {
			int acctNum = Integer.parseInt(str);
			for (int i = 0; i < accts.size(); i++)
			{
				if (acctNum == accts.get(i).getAcctNum())
				{
					return true;
				}
			}
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	public DepositPanel(ArrayList<BankAccount> accts)
	{
		bankAccts = accts;
		setSize(400, 300);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel lblNum = new JLabel("Enter your account number: ");
		add(lblNum, gbc);
		gbc.gridx++;
		JTextField txtNum = new JTextField();
		txtNum.setPreferredSize(new Dimension(180, 20));
		add(txtNum, gbc);
		gbc.gridx--;
		gbc.gridy++;
		JLabel lblAmt = new JLabel("Enter amount: ");
		add(lblAmt, gbc);
		gbc.gridx++;
		JTextField txtAmt = new JTextField();
		txtAmt.setPreferredSize(new Dimension(180, 20));
		add(txtAmt, gbc);
		gbc.gridx--;
		gbc.gridy++;
		JButton btnSubmit = new JButton("Submit");
		add(btnSubmit, gbc);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNumeric(txtAmt.getText()) && inArrayList(txtNum.getText(), bankAccts) && Integer.parseInt(txtAmt.getText()) >= 0)
				{
					int index = -1;
					for (int i = 0; i < bankAccts.size(); i++)
					{
						if (bankAccts.get(i).getAcctNum() == Integer.parseInt(txtNum.getText()))
							index = i;
					}
					try {
						bankAccts.get(index).deposit(Integer.parseInt(txtAmt.getText()));
						JOptionPane.showMessageDialog(thisPanel, "Transaction successful");
					} catch (IllegalArgumentException a) {
						JOptionPane.showMessageDialog(thisPanel, "Transaction not authorized");
					}
				}
				else
					JOptionPane.showMessageDialog(thisPanel, "Invalid Input");
				txtNum.setText("");
				txtAmt.setText("");
			}
		});
		
	}
}
