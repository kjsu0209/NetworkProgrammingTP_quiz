package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OxPanel extends JPanel
{
	JPanel panel = new JPanel();
	static Font size40 = new Font("�޸ո���ü ����", Font.BOLD, 40);
	static Font size30 = new Font("�޸ո���ü ����", Font.BOLD, 30);
	
	public OxPanel(int ox)
	{
		JLabel label, label1;
		
		if(ox == 1)
		{
			label = new JLabel("�����Դϴ�!");
			label.setFont(size40);
			panel.add(label);
		}
		
		else
		{
			label = new JLabel("Ż��! �����Դϴ�. �� ������ ����˴ϴ�.");
			label.setFont(size30);
			panel.add(label);
		}
		panel.setBackground(Color.WHITE);
	}
}