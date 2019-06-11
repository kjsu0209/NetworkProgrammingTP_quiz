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
	static int PNum; // ���� ��
	static JFrame frame = new JFrame("Quiz"); // gui ������
	public static boolean start = false; //���� ����
	static JPanel panel0;
	static int nowP = 0; // ���� ������ȣ
	public static Problem[] test;
	static JLabel label;
	static Container contentpane;
	static HashMap<String, Object> hm;
	static Font size40 = new Font("�޸ո���ü ����", Font.BOLD, 40);
	static Font size20 = new Font("�޸ո���ü ����", Font.BOLD, 20);
	static PrintWriter pw;
	
	public static void main(String[] args)
	{
		int clientnum = 0;

		// txt ���� �о ���� ��ü ����
		try {
			BufferedReader br = new BufferedReader(new FileReader("D:\\���α׷���\\JAVA\\server\\src\\project\\source.txt"));
	        
			PNum = Integer.parseInt(br.readLine()); // ���� �� �ʱ�ȭ
			//System.out.printf("%d\n", PNum);
			
			test = new Problem[PNum]; // ���� ��ü �ʱ�ȭ
			
			int count = 0; // ���� ī��Ʈ
			
			while(true) {
	            String question = br.readLine(); // ���� �б�
	            if (question==null) break;
	            int answer = Integer.parseInt(br.readLine()); // �� �б�
	            String[] option = new String[4]; // ������ �迭 �ʱ�ȭ
	            for(int i=0; i<4; i++) {
	            	option[i] = br.readLine(); // ������ �б�
	            }
	            test[count++] = new Problem(question, option, answer); // ���� ��ü ����
	        }
			
			for(int i = 0; i < count; i++)
	        {
	        	System.out.println(i + " ����: " + test[i].question);
	        	
	        	for(int j = 0; j < 4; j++)
	        		System.out.println(j + " ��: " + test[i].select[j]);
	        	
	        	System.out.println(i + " ����: " + test[i].answer);
	        }
	        br.close();
		}catch(FileNotFoundException e) {
			System.err.println("File Not Found!");
			
		}catch(IOException e) {
			System.err.println("IO Exception!");
		}
		
		// ���� �غ� ȭ�� ��� - ���� ����� Ŭ���̾�Ʈ �� Ȯ��
		frame.setPreferredSize(new Dimension(700, 400));
		frame.setLocation(300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentpane = frame.getContentPane();
		//panel 0 : Ÿ��Ʋ ǥ��
		panel0 = new JPanel();
		JLabel title = new JLabel("�����̹� �������");
		panel0.setBackground(Color.decode("#D9E5FF"));
		title.setFont(size40);
		panel0.add(title);
		contentpane.add(panel0, BorderLayout.NORTH);
		//panel 1 : ���ӵ� Ŭ���̾�Ʈ�� �����ش�
		JPanel panel1 = new JPanel();
		label = new JLabel("���� ���ӵ� Ŭ���̾�Ʈ �� : 0");
		label.setFont(size20);
		panel1.add(label);
		panel1.setBackground(Color.WHITE);
		contentpane.add(panel1, BorderLayout.CENTER);
		//panel 2 : ���� ��ư
		JPanel panel2 = new JPanel();
		JButton button1 = new JButton("�����ϱ�");
		button1.setFont(size20);
		button1.addActionListener(new ActionListener(){ //�͸�Ŭ������ ������ �ۼ�
			public void actionPerformed(ActionEvent e){
				//��ư Ŭ���ϸ� ���� �����ϱ�
				start = true;
				// ���� ���� ������ ���� ( ���� ������ )
				ServerThread serverT = new ServerThread();
				serverT.start();
			}
		});
		panel2.add(button1);
		panel2.setBackground(Color.WHITE);
		contentpane.add(panel2, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
		//���� ����
		try
		{
			ServerSocket server = new ServerSocket(10001);
			hm = new HashMap<String, Object>();
			while(true) {
				/*if(start) { // �����ϱ� ��ư ������ ���
					break;
				}*/
				//accept()
				Socket sock = server.accept();
				pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
				ClientThread clientT = new ClientThread(sock, hm, pw);
				//GUI Ŭ���̾�Ʈ �� ������Ʈ
				updateProgress(String.format("���� ���ӵ� Ŭ���̾�Ʈ �� : %d", ++clientnum));
				clientT.start();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//���� Ŭ���̾�Ʈ �� ������Ʈ
	public static void updateProgress(String updateString) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            label.setText(updateString);
	        }
	    });
	}
	
	//���� ���� ������Ʈ
	public static void updateNowP() {
		nowP++;
	}
	
	//�ش� ���� Problem ��ü ��ȯ
	public static Problem getProblem(int num) {
		return test[num];
	}
	//Ŭ���̾�Ʈ ����Ʈ ��ȯ
	public static HashMap<String, Object> getClientList(){
		return hm;
	}
}
