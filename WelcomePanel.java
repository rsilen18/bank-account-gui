import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public class WelcomePanel extends JPanel
{
	public WelcomePanel()
	{
		setSize(400, 300);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		BufferedImage bankjpg = null;
		try
		{
			bankjpg = ImageIO.read(new File("bank.png"));
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		JLabel lblHome = new JLabel(new ImageIcon(bankjpg));
		add(lblHome, gbc);
	}
}
