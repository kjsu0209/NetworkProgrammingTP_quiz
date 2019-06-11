package project;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class exitPanel extends JPanel
{
	static Font size40 = new Font("휴먼매직체 보통", Font.BOLD, 40);
	JPanel panel = new JPanel();
	
	public exitPanel(int value)
	{
		JLabel label;
		
		if(value == 1)
		{
			label = new JLabel("축하합니다! 마지막 생존자 입니다");
			label.setFont(size40);
			panel.add(label);
		}
		
		else if(value == 0)
		{
			label = new JLabel("접속 종료");
			label.setFont(size40);
			panel.add(label);
		}
		panel.setBackground(Color.WHITE);
	}
}
