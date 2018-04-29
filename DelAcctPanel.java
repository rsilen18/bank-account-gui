import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class DelAcctPanel extends JPanel
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
	
	public DelAcctPanel(ArrayList<BankAccount> bAccts)
	{
		bankAccts = bAccts;
		setSize(400, 300);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel lblNum = new JLabel("Enter Account Number: ");
		add(lblNum, gbc);
		gbc.gridx++;
		JTextField txtNum = new JTextField();
		txtNum.setPreferredSize(new Dimension(180, 20));
		add(txtNum, gbc);
		gbc.gridx--;
		gbc.gridy++;
		JButton btnSubmit = new JButton("Submit");
		add(btnSubmit, gbc);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = -1;
				for (int i = 0; i < bankAccts.size(); i++)
				{
					if (bankAccts.get(i).getAcctNum() == Integer.parseInt(txtNum.getText()))
					{
						index = i;
					}
				}
				if (index != -1)
				{
					bankAccts.remove(index);
					JOptionPane.showMessageDialog(thisPanel, "Account successfully removed");
				}
				else
					JOptionPane.showMessageDialog(thisPanel, "Account number does not exist");
				txtNum.setText("");
			}
		});
	}
}
