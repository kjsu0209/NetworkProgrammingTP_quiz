//ReadThread : ���� �޾Ƽ� �а� gui ����ϴ� ������
package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ReadThread extends Thread
{
	private Socket sock = null;
    private BufferedReader br = null;
    private int ox;

    private JPanel panel0 = null;
    public ReadThread(Socket sock,BufferedReader br)
    {
        this.sock = sock;
        this.br = br;
    }

	public void run()
	{
		String question = null;
		String[] option = new String[4];
		String line = null;
		int flag = 0;
		
		while(true)
		{
			try
			{
				question = br.readLine();//���� �ޱ�
				flag++;
				System.out.println("flag:"+flag);
				
				for(int i=0;i<4;i++)
				{
					option[i] = br.readLine(); // ������ �ޱ�
				}
				//���� ǥ���ϱ�(Ÿ�̸ӵ� �Բ� ����)
				updateProgress(question, option);
				//ä�� ��� �ޱ� -> �߰��ؾ���
			
				line = br.readLine();
				ox = Integer.parseInt(line);
				JPanel newpanel;
				
				Client.contentpane.removeAll();
					
				newpanel = new OxPanel(ox).panel;
				Client.contentpane.add(newpanel, BorderLayout.CENTER);
				Client.frame.setVisible(true);
					
				try
				{
					GamePanel.timer.join(); //���� ���� ������ ���� �ñ��� ���
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}

				if(ox == 0)
				{
					Client.contentpane.removeAll();
					
					newpanel = new exitPanel(0).panel;
					Client.contentpane.add(newpanel, BorderLayout.CENTER);
					Client.frame.setVisible(true);
				}
				
				if(ox == 1 && flag == 2)
				{
					Client.contentpane.removeAll();
					
					newpanel = new exitPanel(1).panel;
					Client.contentpane.add(newpanel, BorderLayout.CENTER);
					Client.frame.setVisible(true);
				}
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}
	//���� ������Ʈ
	public static void updateProgress(String question, String[] option) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	try {
	        		Client.contentpane.removeAll();
		        	JPanel newpanel2 = new GamePanel(question, option);
		        	newpanel2.setBackground(Color.WHITE);
		        	Client.contentpane.add(newpanel2, BorderLayout.CENTER);
					Client.frame.setVisible(true);
	        	}
	        	catch(Exception e){		
	        		e.printStackTrace();
	        	}
	        }
	    });
	}
}
