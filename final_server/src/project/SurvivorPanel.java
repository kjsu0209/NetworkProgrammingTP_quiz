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

public class SurvivorPanel extends JPanel
{
	static Font size40 = new Font("휴먼매직체 보통", Font.BOLD, 40);
	static Font size30 = new Font("휴먼매직체 보통", Font.BOLD, 30);
	static HashMap<String, Object> hm;
	String[] idlist = new String[10];
	int index = 0;
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	
	public SurvivorPanel(HashMap<String, Object> hm)
	{
		this.hm = hm;
		
		if (ClientThread.flag == 1)
		{
			for(int i = 0; i < 10; i++)
				idlist[i] = null;
			
			Iterator<String> iter = hm.keySet().iterator();
			while(iter.hasNext())
			{
				String id = (String)iter.next();
				idlist[index++] = id;
			}
			
			index = 0;
			
			JLabel title, survivor[];
			title = new JLabel("마지막 생존자: ");
			title.setFont(size40);
			panel.add(title);
			panel.setBackground(Color.WHITE);

			survivor = new JLabel[10];	
			while(idlist[index] != null)
			{
				survivor[index] = new JLabel(idlist[index] + " ");
				survivor[index].setFont(size30);
				panel.add(survivor[index++]);
			}
		}
		
		else if (ClientThread.flag == 2)
		{
			JLabel title = new JLabel("전원 탈락!");
			title.setFont(size40);
			panel.add(title);
			panel.setBackground(Color.WHITE);
		}
		
		else if (ClientThread.flag == 3)
		{
			JLabel title = new JLabel("참가자 없음");
			title.setFont(size40);
			panel.add(title);
			panel.setBackground(Color.WHITE);
		}
	}
}