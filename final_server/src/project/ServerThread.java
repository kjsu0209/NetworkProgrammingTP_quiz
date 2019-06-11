//���� ������ ���� ������ ����ϴ� ������
//���� ���� ��ư�� ������ �� ������ ����
package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ServerThread extends Thread
{
	int flag = 0, count = 0;
	static Thread timer;
	static Font comic_sans = new Font("Comic Sans MS", Font.BOLD, 20);
	public void run()
	{	
		while(true)
		{
			if (Server.hm.isEmpty() == true) {
        		ClientThread.flag = 3;
        		Server.contentpane.removeAll();
				JPanel newpanel = new SurvivorPanel(Server.hm).panel;
				Server.contentpane.add(newpanel, BorderLayout.CENTER);
				Server.frame.setVisible(true);
				
				break;
        	}
			//���� �����ϱ�
			for(int i = 0; i<Server.PNum; i++)
			{
				//UI �ٲٱ�
				updateProblem(i, Server.getClientList());
				ClientThread.broadcast(Server.getProblem(i).question); //���� �����ϱ�
				count++;
				//������ �����ϱ�
				for(int j = 0; j<4;j++) {
					ClientThread.broadcast(Server.getProblem(i).select[j]);
				}
				//ClientThread.broadcast(Integer.toString(Server.getProblem(i).answer)); //�� �����ϱ�
				
				//Ÿ�̸� �����ϱ�( 10�� ) -> ServerPanel ȭ�鿡 �� �޼ҵ� ���� Ÿ�̸� �����־�� ��
				JPanel panelT = new JPanel();
				JLabel labelT = new JLabel("10");
				labelT.setFont(comic_sans);
				panelT.add(labelT);
				panelT.setBackground(Color.decode("0X88FFFF"));
				Server.contentpane.add(panelT, BorderLayout.SOUTH);
				Thread timer = new TimerThread(labelT);
				timer.start();

				//Ÿ�̸� ���������� ��ٸ���
				try
				{
					Thread.sleep(10000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				Server.updateNowP();
				
				if(count == Server.PNum || ClientThread.flag == 2)
				{
					Server.contentpane.removeAll();
					JPanel newpanel = new SurvivorPanel(Server.hm).panel;
					Server.contentpane.add(newpanel, BorderLayout.CENTER);
					Server.frame.setVisible(true);
					
					break;
				}
			}
			
			flag = 1;
			
			if(flag == 1)
				break;
		}	
	}
	
	//���� ���� Ȯ�� ȭ�� ������Ʈ
	public static void updateProblem(int num, HashMap<String, Object> hm) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	Server.contentpane.removeAll();
	        	
				JPanel newpanel = new ServerPanel(Server.getProblem(num), hm);
				newpanel.setBackground(Color.WHITE);
				Server.contentpane.add(newpanel);
				Server.frame.setVisible(true);
	        }
	    });
	}
}
