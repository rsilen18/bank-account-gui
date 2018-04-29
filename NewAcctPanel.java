import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class NewAcctPanel extends JPanel
{
	private String[] accounts = {"Savings", "Checking"};
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
	
	public NewAcctPanel(ArrayList<BankAccount> bAccts)
	{
		bankAccts = bAccts;
		setSize(400, 300);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel lblName = new JLabel("Name: ");
		add(lblName, gbc);
		gbc.gridx++;
		JTextField txtName = new JTextField();
		txtName.setPreferredSize(new Dimension(180, 20));
		add(txtName, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		JLabel lblDep = new JLabel("Initial Deposit: ");
		add(lblDep, gbc);
		gbc.gridx++;
		JTextField txtDep = new JTextField();
		txtDep.setPreferredSize(new Dimension(180, 20));
		add(txtDep, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		JLabel lblAcct = new JLabel("Account Type: ");
		add(lblAcct, gbc);
		gbc.gridx++;
		JList accts = new JList(accounts);
		JScrollPane list = new JScrollPane(accts);
		list.setPreferredSize(new Dimension(150, 20));
		add(list, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		JButton btnSubmit = new JButton("Submit");
		add(btnSubmit, gbc);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				double bal = 0;
				if (isNumeric(txtDep.getText()))
				{
					bal = Double.parseDouble(txtDep.getText());
				}
				if (!(txtName.getText().equals("") || accts.getSelectedValue() == null) && (isNumeric(txtDep.getText()) || txtDep.getText().equals("")))
				{
					if (accts.getSelectedValue().equals("Savings"))
					{
						bankAccts.add(new SavingsAccount(name, bal, RATE, MIN_BAL, MIN_BAL_FEE));
					}
					else if (accts.getSelectedValue().equals("Checking"))
					{
						bankAccts.add(new CheckingAccount(name, bal, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
					}
					txtName.setText("");
					txtDep.setText("");
					JOptionPane.showMessageDialog(thisPanel, "Account Successfully Created");
				}
			}
		});
	}
	
	public ArrayList<BankAccount> getList()
	{
		return bankAccts;
	}
	
}
