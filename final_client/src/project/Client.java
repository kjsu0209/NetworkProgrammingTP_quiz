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
	static String id = null;//���� �̸� 
	static JFrame frame = new JFrame("Quiz"); // gui ������
	// Ŭ���̾�Ʈ ���� �ʱ�ȭ
	static Socket sock = null;
	static BufferedReader br = null; //�Է� ����
	static PrintWriter pw = null; // ��� ����
	static String IPaddr = "192.168.237.1"; //IP �ּ�
	boolean endflag = false;
	static Container contentpane;
	static Thread reader = null;
	static Font size40 = new Font("�޸ո���ü ����", Font.BOLD, 40);
	static Font size20 = new Font("�޸ո���ü ����", Font.BOLD, 20);
	
	public static void main(String[] args)
	{
//		String question= null; // ���� ���� ���� 1) ����
//		String []option = new String[4]; // ���� ���� ���� 2) ������

		//1. �̸� ���� ȭ�� ���
		// ���� �غ� ȭ�� ��� - ���� ����� Ŭ���̾�Ʈ �� Ȯ��
		frame.setPreferredSize(new Dimension(700, 400));
		frame.setLocation(300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentpane = frame.getContentPane();
		frame.setBackground(Color.BLACK);
		//panel 0 : Ÿ��Ʋ ǥ��
		JPanel panel0 = new JPanel();
		panel0.setSize(700,300);
		panel0.setBackground(Color.decode("#FFD8D8"));
		JLabel title = new JLabel("�����̹� �������");
		title.setFont(size40);
		panel0.add(title);
		contentpane.add(panel0, BorderLayout.NORTH);
		//panel 1 : ����� �̸� ���� 
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		JLabel label = new JLabel("������ �̸� : ");
		label.setFont(size20);
		JTextField tf = new JTextField(10);
		panel1.add(label);
		panel1.add(tf);
		Button submitbtn = new Button("Ȯ��");
		submitbtn.setFont(size20);
		
		
		submitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//����� �̸� ����
				id = tf.getText();
				//ȭ�� �ٲٱ� - ���� ���ȭ�� ���
				contentpane.removeAll();
				JPanel newpanel = new WaitPanel().panel;
				newpanel.setBackground(Color.WHITE);
				contentpane.add(newpanel, BorderLayout.CENTER);
				frame.setVisible(true);				
				try {
					//2. ���� ���� ��� ȭ�� ���
					//2-1. �������� ���� �̸� ������
					//������ ���� ����
					sock = new Socket();
					sock.connect(new InetSocketAddress(IPaddr, 10001));
					pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
					br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					pw.println(id);
			        pw.flush();
					//3. ���� ���� - ���� ����, Ÿ�̸� ������ �����
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
			reader.join(); //���� ���� ������ ���� �ñ��� ���
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
