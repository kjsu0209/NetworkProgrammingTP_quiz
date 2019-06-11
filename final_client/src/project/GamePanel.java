package project;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class GamePanel extends JPanel {
	public JPanel panelQ = new JPanel(); // 질문 나오는 패널
	public JPanel panelS = new JPanel(); // 선택지 버튼 나오는 패널
	public JPanel panelT = new JPanel(); // 타이머 나오는 패널 -> 추가해야됨
    static Thread timer = null;
    static Font comic_sans = new Font("Comic Sans MS", Font.BOLD, 15);
	static Font comic_sans20 = new Font("Comic Sans MS", Font.BOLD, 20);
    
	JLabel question; // 질문
	/*
	JButton btn1; //1번 선택지
	JButton btn2; //2번 선택지
	JButton btn3; //3번 선택지
	JButton btn4; //4번 선택지
	JButton btn5; //5번 선택지
*/
	JToggleButton btn1;
	JToggleButton btn2;
	JToggleButton btn3;
	JToggleButton btn4;
	JToggleButton btn5;
	
	public GamePanel(String q, String[] s) { // q는 질문, s는 선택지
		//질문 패널 설정
		JPanel panel0 = new JPanel();
		
		JLabel question = new JLabel(q);
		question.setFont(comic_sans20);
		panelQ.add(question);
		panelQ.setBackground(Color.decode("0X88FF88"));
		Client.frame.add(panelQ, BorderLayout.NORTH);

		//선택지 패널 설정
		//1번 선택지 추가
		btn1 = new JToggleButton(s[0]);
		btn1.setFont(comic_sans);
		btn1.addActionListener(new ActionListener(){ //익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e){
				//버튼 클릭하면 선택한 번호 전송하기
				Client.pw.println("1");
		        Client.pw.flush();
			}
		});
		panelS.add(btn1);
		//2번 선택지 추가
		btn2 = new JToggleButton(s[1]);
		btn2.setFont(comic_sans);
		btn2.addActionListener(new ActionListener(){ //익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e){
				//버튼 클릭하면 선택한 번호 전송하기
				Client.pw.println("2");
		        Client.pw.flush();
			}
		});
		panelS.add(btn2);
		//3번 선택지 추가
		btn3 = new JToggleButton(s[2]);
		btn3.setFont(comic_sans);
		btn3.addActionListener(new ActionListener(){ //익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e){
				//버튼 클릭하면 선택한 번호 전송하기
				Client.pw.println("3");
		        Client.pw.flush();
			}
		});
		panelS.add(btn3);
		//4번 선택지 추가
		btn4 = new JToggleButton(s[3]);
		btn4.setFont(comic_sans);
		btn4.addActionListener(new ActionListener(){ //익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e){
				//버튼 클릭하면 선택한 번호 전송하기
				Client.pw.println("4");
		        Client.pw.flush();
			}
		});
		panelS.add(btn4);
		
		panelS.setBackground(Color.YELLOW);
		panelS.setLayout(new FlowLayout(WIDTH, 10, 5));
		add(panelS, BorderLayout.CENTER);
		
		setVisible(true);

		//타이머 패널 설정
		JLabel labelT = new JLabel();
		labelT.setFont(comic_sans20);
		panelT.setBackground(Color.decode("0X88FFFF"));
		panelT.add(labelT);
		Client.frame.add(panelT, BorderLayout.SOUTH);
		
		timer = new TimerThread(labelT);
		timer.start();
	}
}
