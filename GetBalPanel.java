import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GetBalPanel extends JPanel
{
	private ArrayList<BankAccount> bankAccts;
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
	
	public GetBalPanel(ArrayList<BankAccount> bAccts)
	{
		bankAccts = bAccts;
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
		JLabel lblBal = new JLabel("Balance: ");
		add(lblBal, gbc);
		gbc.gridx++;
		JTextField txtBal = new JTextField();
		txtBal.setPreferredSize(new Dimension(180, 20));
		txtBal.setEditable(false);
		add(txtBal, gbc);
		gbc.gridx--;
		gbc.gridy++;
		JButton btnSubmit = new JButton("Submit");
		add(btnSubmit, gbc);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int acctNum = -1;
				double amt = 0;
				if (inArrayList(txtNum.getText(), bankAccts))
				{
					int index = -1;
					for (int i = 0; i < bankAccts.size(); i++)
					{
						if (bankAccts.get(i).getAcctNum() == Integer.parseInt(txtNum.getText()))
							index = i;
					}
					txtBal.setText("" + bankAccts.get(index).getBalance());
				}
			}
		});
	}
}
