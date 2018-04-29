import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TransferPanel extends JPanel
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
	
	public TransferPanel(ArrayList<BankAccount> bAccts)
	{
		bankAccts = bAccts;
		setSize(400, 300);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel lblNum1 = new JLabel("Enter first account number: ");
		add(lblNum1, gbc);
		gbc.gridx++;
		JTextField txtNum1 = new JTextField();
		txtNum1.setPreferredSize(new Dimension(180, 20));
		add(txtNum1, gbc);
		gbc.gridx--;
		gbc.gridy++;
		JLabel lblNum2 = new JLabel("Enter second account number: ");
		add(lblNum2, gbc);
		gbc.gridx++;
		JTextField txtNum2 = new JTextField();
		txtNum2.setPreferredSize(new Dimension(180, 20));
		add(txtNum2, gbc);
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
				int acctNum = -1;
				double amt = 0;
				if (isNumeric(txtAmt.getText()) && inArrayList(txtNum1.getText(), bankAccts) && inArrayList(txtNum2.getText(), bankAccts) && Integer.parseInt(txtAmt.getText()) >= 0)
				{
					int index1 = -1;
					int index2 = -1;
					for (int i = 0; i < bankAccts.size(); i++)
					{
						if (bankAccts.get(i).getAcctNum() == Integer.parseInt(txtNum1.getText()))
							index1 = i;
						else if (bankAccts.get(i).getAcctNum() == Integer.parseInt(txtNum2.getText()))
							index2 = i;
					}
					try {
						bankAccts.get(index1).transfer(bankAccts.get(index2), Integer.parseInt(txtAmt.getText()));
						JOptionPane.showMessageDialog(thisPanel, "Transaction successful");
					} catch (IllegalArgumentException a) {
						JOptionPane.showMessageDialog(thisPanel, "Transaction not authorized");
					}
				}
				else
					JOptionPane.showMessageDialog(thisPanel, "Invalid Input");
				txtNum1.setText("");
				txtNum2.setText("");
				txtAmt.setText("");
			}
		});
	}
}
