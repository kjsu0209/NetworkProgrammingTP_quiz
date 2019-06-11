package project;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.lang.InterruptedException;

//import javax.swing.JPanel;
import javax.swing.JLabel;

public class TimerThread extends Thread
{
	public JLabel labelT = new JLabel();
	//public JPanel panelT = new JPanel();
	static Container contentpane;
	JLabel tLabel[] = new JLabel[10];
	//static String[] times = {"1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10 "};
	
	public TimerThread(JLabel labelT)
	{
		this.labelT = labelT;
	}
	
	public void run()
	{
		try
		{
			for (int i = 10; i >= 1; i--) {
				changeNumber(String.format("%d", i));
				sleep(1000);
				/*
				panelT.setVisible(false);
				tLabel[i] = new JLabel(times[i]);
				panelT.add(tLabel[i]);
				Server.contentpane.add(panelT, BorderLayout.SOUTH);
				panelT.setVisible(true);
				sleep(1000);
				*/
			}
			labelT.removeAll();
		}
		catch(InterruptedException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void changeNumber(String numbers) {
		labelT.setText(numbers);
	}

}
