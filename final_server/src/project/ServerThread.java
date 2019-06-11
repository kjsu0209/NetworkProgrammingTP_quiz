//서버 측에서 문제 보낼때 사용하는 쓰레드
//문제 시작 버튼을 눌렀을 때 쓰레드 시작
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
			//문제 전송하기
			for(int i = 0; i<Server.PNum; i++)
			{
				//UI 바꾸기
				updateProblem(i, Server.getClientList());
				ClientThread.broadcast(Server.getProblem(i).question); //문제 전송하기
				count++;
				//선택지 전송하기
				for(int j = 0; j<4;j++) {
					ClientThread.broadcast(Server.getProblem(i).select[j]);
				}
				//ClientThread.broadcast(Integer.toString(Server.getProblem(i).answer)); //답 전송하기
				
				//타이머 시작하기( 10초 ) -> ServerPanel 화면에 라벨 메소드 만들어서 타이머 보여주어야 함
				JPanel panelT = new JPanel();
				JLabel labelT = new JLabel("10");
				labelT.setFont(comic_sans);
				panelT.add(labelT);
				panelT.setBackground(Color.decode("0X88FFFF"));
				Server.contentpane.add(panelT, BorderLayout.SOUTH);
				Thread timer = new TimerThread(labelT);
				timer.start();

				//타이머 끝날때까지 기다리기
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
	
	//서버 문제 확인 화면 업데이트
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
