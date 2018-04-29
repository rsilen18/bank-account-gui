import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame 
{
	ArrayList<BankAccount> bankAccts = new ArrayList<BankAccount>();
	
	public MainFrame()
	{
		setTitle("Title");
		CardLayout cl = new CardLayout();
		JPanel overall = new JPanel();
		overall.setLayout(cl);
		JMenuBar mBar = new JMenuBar();
		JMenu home = new JMenu("Home");
		JMenuItem welcomeScreen = new JMenuItem("Welcome Screen");
		JMenu acct = new JMenu("Account");
		JMenuItem addAcct = new JMenuItem("New Account");
		JMenuItem getAcctNum = new JMenuItem("Get Account Number");
		JMenuItem delAcct = new JMenuItem("Delete Account");
		JMenu trans = new JMenu("Transaction");
		JMenuItem deposit = new JMenuItem("Deposit");
		JMenuItem withdraw = new JMenuItem("Withdraw");
		JMenuItem transfer = new JMenuItem("Transfer");
		JMenuItem getBal = new JMenuItem("Get Balance");
		home.add(welcomeScreen);
		mBar.add(home);
		acct.add(addAcct);
		acct.add(getAcctNum);
		acct.add(delAcct);
		mBar.add(acct);
		trans.add(deposit);
		trans.add(withdraw);
		trans.add(transfer);
		trans.add(getBal);
		mBar.add(trans);
		JPanel welcome = new WelcomePanel();
		JPanel newAcctPanel = new NewAcctPanel(bankAccts);
		JPanel acctNumPanel = new AcctNumPanel(bankAccts);
		JPanel delAcctPanel = new DelAcctPanel(bankAccts);
		JPanel depositPanel = new DepositPanel(bankAccts);
		JPanel withdrawPanel = new WithdrawPanel(bankAccts);
		JPanel transferPanel = new TransferPanel(bankAccts);
		JPanel getBalPanel = new GetBalPanel(bankAccts);
		setBounds(200, 200, 400, 300);
		overall.add(welcome, "welcome");
		overall.add(newAcctPanel, "newAcctPanel");
		overall.add(acctNumPanel, "acctNumPanel");
		overall.add(delAcctPanel, "delAcctPanel");
		overall.add(depositPanel, "depositPanel");
		overall.add(withdrawPanel, "withdrawPanel");
		overall.add(transferPanel, "transferPanel");
		overall.add(getBalPanel, "getBalPanel");
		add(overall);
		cl.show(overall, "welcome");
		setJMenuBar(mBar);
		welcomeScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(overall, "welcome");
//				System.out.println(bankAccts);
			}
		});
		addAcct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(overall, "newAcctPanel");
			}
		});
		getAcctNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(overall, "acctNumPanel");
			}
		});
		delAcct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(overall, "delAcctPanel");
			}
		});
		deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(overall, "depositPanel");
			}
		});
		withdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(overall, "withdrawPanel");
			}
		});
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(overall, "transferPanel");
			}
		});
		getBal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(overall, "getBalPanel");
			}
		});
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		new MainFrame();
	}
	
}
