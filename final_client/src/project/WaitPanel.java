package project;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WaitPanel extends JPanel
{
	static Font size40 = new Font("�޸ո���ü ����", Font.BOLD, 40);
	JPanel panel = new JPanel();
	
	public WaitPanel()
	{
		JLabel label = new JLabel("�ٸ� �����ڸ� ��ٸ��� ��...");
		label.setFont(size40);
		panel.add(label);
	}
}
