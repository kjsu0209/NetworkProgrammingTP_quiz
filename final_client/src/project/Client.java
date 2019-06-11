package project;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
//import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.SwingUtilities;

public class Client {
	static String id = null;//유저 이름 
	static JFrame frame = new JFrame("Quiz"); // gui 프레임
	// 클라이언트 소켓 초기화
	static Socket sock = null;
	static BufferedReader br = null; //입력 버퍼
	static PrintWriter pw = null; // 출력 버퍼
	static String IPaddr = "192.168.237.1"; //IP 주소
	boolean endflag = false;
	static Container contentpane;
	static Thread reader = null;
	static Font size40 = new Font("휴먼매직체 보통", Font.BOLD, 40);
	static Font size20 = new Font("휴먼매직체 보통", Font.BOLD, 20);
	
	public static void main(String[] args)
	{
//		String question= null; // 문제 수신 변수 1) 문제
//		String []option = new String[4]; // 문제 수신 변수 2) 선택지

		//1. 이름 묻는 화면 출력
		// 게임 준비 화면 출력 - 현재 연결된 클라이언트 수 확인
		frame.setPreferredSize(new Dimension(700, 400));
		frame.setLocation(300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentpane = frame.getContentPane();
		frame.setBackground(Color.BLACK);
		//panel 0 : 타이틀 표시
		JPanel panel0 = new JPanel();
		panel0.setSize(700,300);
		panel0.setBackground(Color.decode("#FFD8D8"));
		JLabel title = new JLabel("서바이벌 퀴즈게임");
		title.setFont(size40);
		panel0.add(title);
		contentpane.add(panel0, BorderLayout.NORTH);
		//panel 1 : 사용자 이름 묻기 
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		JLabel label = new JLabel("참가자 이름 : ");
		label.setFont(size20);
		JTextField tf = new JTextField(10);
		panel1.add(label);
		panel1.add(tf);
		Button submitbtn = new Button("확인");
		submitbtn.setFont(size20);
		
		
		submitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//사용자 이름 저장
				id = tf.getText();
				//화면 바꾸기 - 게임 대기화면 출력
				contentpane.removeAll();
				JPanel newpanel = new WaitPanel().panel;
				newpanel.setBackground(Color.WHITE);
				contentpane.add(newpanel, BorderLayout.CENTER);
				frame.setVisible(true);				
				try {
					//2. 게임 시작 대기 화면 출력
					//2-1. 서버에게 유저 이름 보내기
					//서버와 소켓 연결
					sock = new Socket();
					sock.connect(new InetSocketAddress(IPaddr, 10001));
					pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
					br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					pw.println(id);
			        pw.flush();
					//3. 게임 시작 - 문제 수신, 타이머 쓰레드 만들기
					reader = new ReadThread(sock, br);
					reader.start();
				}catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		panel1.add(submitbtn);
		contentpane.add(panel1, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);

		/*try {
			reader.join(); //문제 수신 쓰레드 종료 시까지 대기
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		try {
			if(pw != null) {
				pw.close();
			}
			if(br != null) {
				br.close();
			}
			if(sock != null) {
				sock.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
}
