package project;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WaitPanel extends JPanel
{
	static Font size40 = new Font("휴먼매직체 보통", Font.BOLD, 40);
	JPanel panel = new JPanel();
	
	public WaitPanel()
	{
		JLabel label = new JLabel("다른 참가자를 기다리는 중...");
		label.setFont(size40);
		panel.add(label);
	}
}
