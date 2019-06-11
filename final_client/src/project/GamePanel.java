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
	public JPanel panelQ = new JPanel(); // ���� ������ �г�
	public JPanel panelS = new JPanel(); // ������ ��ư ������ �г�
	public JPanel panelT = new JPanel(); // Ÿ�̸� ������ �г� -> �߰��ؾߵ�
    static Thread timer = null;
    static Font comic_sans = new Font("Comic Sans MS", Font.BOLD, 15);
	static Font comic_sans20 = new Font("Comic Sans MS", Font.BOLD, 20);
    
	JLabel question; // ����
	/*
	JButton btn1; //1�� ������
	JButton btn2; //2�� ������
	JButton btn3; //3�� ������
	JButton btn4; //4�� ������
	JButton btn5; //5�� ������
*/
	JToggleButton btn1;
	JToggleButton btn2;
	JToggleButton btn3;
	JToggleButton btn4;
	JToggleButton btn5;
	
	public GamePanel(String q, String[] s) { // q�� ����, s�� ������
		//���� �г� ����
		JPanel panel0 = new JPanel();
		
		JLabel question = new JLabel(q);
		question.setFont(comic_sans20);
		panelQ.add(question);
		panelQ.setBackground(Color.decode("0X88FF88"));
		Client.frame.add(panelQ, BorderLayout.NORTH);

		//������ �г� ����
		//1�� ������ �߰�
		btn1 = new JToggleButton(s[0]);
		btn1.setFont(comic_sans);
		btn1.addActionListener(new ActionListener(){ //�͸�Ŭ������ ������ �ۼ�
			public void actionPerformed(ActionEvent e){
				//��ư Ŭ���ϸ� ������ ��ȣ �����ϱ�
				Client.pw.println("1");
		        Client.pw.flush();
			}
		});
		panelS.add(btn1);
		//2�� ������ �߰�
		btn2 = new JToggleButton(s[1]);
		btn2.setFont(comic_sans);
		btn2.addActionListener(new ActionListener(){ //�͸�Ŭ������ ������ �ۼ�
			public void actionPerformed(ActionEvent e){
				//��ư Ŭ���ϸ� ������ ��ȣ �����ϱ�
				Client.pw.println("2");
		        Client.pw.flush();
			}
		});
		panelS.add(btn2);
		//3�� ������ �߰�
		btn3 = new JToggleButton(s[2]);
		btn3.setFont(comic_sans);
		btn3.addActionListener(new ActionListener(){ //�͸�Ŭ������ ������ �ۼ�
			public void actionPerformed(ActionEvent e){
				//��ư Ŭ���ϸ� ������ ��ȣ �����ϱ�
				Client.pw.println("3");
		        Client.pw.flush();
			}
		});
		panelS.add(btn3);
		//4�� ������ �߰�
		btn4 = new JToggleButton(s[3]);
		btn4.setFont(comic_sans);
		btn4.addActionListener(new ActionListener(){ //�͸�Ŭ������ ������ �ۼ�
			public void actionPerformed(ActionEvent e){
				//��ư Ŭ���ϸ� ������ ��ȣ �����ϱ�
				Client.pw.println("4");
		        Client.pw.flush();
			}
		});
		panelS.add(btn4);
		
		panelS.setBackground(Color.YELLOW);
		panelS.setLayout(new FlowLayout(WIDTH, 10, 5));
		add(panelS, BorderLayout.CENTER);
		
		setVisible(true);

		//Ÿ�̸� �г� ����
		JLabel labelT = new JLabel();
		labelT.setFont(comic_sans20);
		panelT.setBackground(Color.decode("0X88FFFF"));
		panelT.add(labelT);
		Client.frame.add(panelT, BorderLayout.SOUTH);
		
		timer = new TimerThread(labelT);
		timer.start();
	}
}
