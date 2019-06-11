package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;

public class Server {
	static int PNum; // 문제 수
	static JFrame frame = new JFrame("Quiz"); // gui 프레임
	public static boolean start = false; //시작 여부
	static JPanel panel0;
	static int nowP = 0; // 현재 문제번호
	public static Problem[] test;
	static JLabel label;
	static Container contentpane;
	static HashMap<String, Object> hm;
	static Font size40 = new Font("휴먼매직체 보통", Font.BOLD, 40);
	static Font size20 = new Font("휴먼매직체 보통", Font.BOLD, 20);
	static PrintWriter pw;
	
	public static void main(String[] args)
	{
		int clientnum = 0;

		// txt 파일 읽어서 문제 객체 생성
		try {
			BufferedReader br = new BufferedReader(new FileReader("D:\\프로그래밍\\JAVA\\server\\src\\project\\source.txt"));
	        
			PNum = Integer.parseInt(br.readLine()); // 문제 수 초기화
			//System.out.printf("%d\n", PNum);
			
			test = new Problem[PNum]; // 문제 객체 초기화
			
			int count = 0; // 문제 카운트
			
			while(true) {
	            String question = br.readLine(); // 질문 읽기
	            if (question==null) break;
	            int answer = Integer.parseInt(br.readLine()); // 답 읽기
	            String[] option = new String[4]; // 선택지 배열 초기화
	            for(int i=0; i<4; i++) {
	            	option[i] = br.readLine(); // 선택지 읽기
	            }
	            test[count++] = new Problem(question, option, answer); // 문제 객체 생성
	        }
			
			for(int i = 0; i < count; i++)
	        {
	        	System.out.println(i + " 문제: " + test[i].question);
	        	
	        	for(int j = 0; j < 4; j++)
	        		System.out.println(j + " 번: " + test[i].select[j]);
	        	
	        	System.out.println(i + " 정답: " + test[i].answer);
	        }
	        br.close();
		}catch(FileNotFoundException e) {
			System.err.println("File Not Found!");
			
		}catch(IOException e) {
			System.err.println("IO Exception!");
		}
		
		// 게임 준비 화면 출력 - 현재 연결된 클라이언트 수 확인
		frame.setPreferredSize(new Dimension(700, 400));
		frame.setLocation(300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentpane = frame.getContentPane();
		//panel 0 : 타이틀 표시
		panel0 = new JPanel();
		JLabel title = new JLabel("서바이벌 퀴즈게임");
		panel0.setBackground(Color.decode("#D9E5FF"));
		title.setFont(size40);
		panel0.add(title);
		contentpane.add(panel0, BorderLayout.NORTH);
		//panel 1 : 접속된 클라이언트를 보여준다
		JPanel panel1 = new JPanel();
		label = new JLabel("현재 접속된 클라이언트 수 : 0");
		label.setFont(size20);
		panel1.add(label);
		panel1.setBackground(Color.WHITE);
		contentpane.add(panel1, BorderLayout.CENTER);
		//panel 2 : 시작 버튼
		JPanel panel2 = new JPanel();
		JButton button1 = new JButton("시작하기");
		button1.setFont(size20);
		button1.addActionListener(new ActionListener(){ //익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e){
				//버튼 클릭하면 퀴즈 시작하기
				start = true;
				// 문제 전송 스레드 시작 ( 단일 스레드 )
				ServerThread serverT = new ServerThread();
				serverT.start();
			}
		});
		panel2.add(button1);
		panel2.setBackground(Color.WHITE);
		contentpane.add(panel2, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
		//서버 시작
		try
		{
			ServerSocket server = new ServerSocket(10001);
			hm = new HashMap<String, Object>();
			while(true) {
				/*if(start) { // 시작하기 버튼 눌렀을 경우
					break;
				}*/
				//accept()
				Socket sock = server.accept();
				pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
				ClientThread clientT = new ClientThread(sock, hm, pw);
				//GUI 클라이언트 수 업데이트
				updateProgress(String.format("현재 접속된 클라이언트 수 : %d", ++clientnum));
				clientT.start();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//접속 클라이언트 수 업데이트
	public static void updateProgress(String updateString) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            label.setText(updateString);
	        }
	    });
	}
	
	//현재 문제 업데이트
	public static void updateNowP() {
		nowP++;
	}
	
	//해당 문제 Problem 객체 반환
	public static Problem getProblem(int num) {
		return test[num];
	}
	//클라이언트 리스트 반환
	public static HashMap<String, Object> getClientList(){
		return hm;
	}
}
