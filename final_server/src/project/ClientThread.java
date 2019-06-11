//ClientThread: Ŭ���̾�Ʈ���Լ� ���� �� �ް� ä�� ��� ����
package project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ClientThread extends Thread {
	private Socket sock; //���� ����
	private String id; //���� id
	private int select; //��
	private BufferedReader br; //���� �޼��� �б�
	private PrintWriter pw;
	private boolean initFlag = false; //ó�� ���������� false
	private int nowNum = 0; //���� ���� ��ȣ
	private static HashMap<String, Object> hm; //���� ���� ����Ʈ
	static int flag = 0;
	
	public ClientThread(Socket sock, HashMap<String, Object> hm, PrintWriter pw) {
		this.sock = sock;
		this.hm = hm;
		this.pw = pw;
		
		try
		{
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			id = br.readLine(); // ó���� Ŭ���̾�Ʈ ������ �����ϸ� id�� ������.
			System.out.println("������ ������� ���̵� : "+id);
            synchronized (hm)
            {
                hm.put(this.id, pw);
            }
            
            System.out.println("hm:"+hm);
            initFlag = true;
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void run() {
		while(true) {
			try {
				if (flag == 3) {
					break;
				}
				int i = 0;
	            for(i = 0; i<Server.PNum;i++)
	            {
	            	String line = null;
	                // ������ ���� ��� ä�� �� ��� ����
	            	// ��� �޾Ƽ� ����ϴ°ͱ����� ��������
	            	line = br.readLine();
	            	select = Integer.parseInt(line); // �� ����
	            	System.out.println("������ ��:"+select + " ����:"+Server.test[i].answer);
	            	if(select == Server.test[i].answer)
	            	{
	            		pw.println("1");
	            		pw.flush();
	            	}
	            	else
	            	{
	            		pw.println("0");
	            		pw.flush();
	            		synchronized(hm)
	            		{
	            			hm.remove(id);
	            			
	            		}
	            	}
	            	
	            	System.out.println("hm:"+hm);
	            	
	            	if (hm.isEmpty() == true) 
	            	{
	            		flag = 2;
	            		break;
	            	}
	            }
	            if (i == Server.PNum)
	            	flag = 1;
	        }
			catch (Exception e)
			{
	            e.printStackTrace();
	        }
			/*finally {
	            synchronized (hm) {
	                hm.remove(id);
	            }
	            broadcast(id+"���� ������ �����߽��ϴ�.");
	            try {
	                if(sock != null) {
	                    sock.close();
	                    break;
	                }
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }*/
			
			if(flag == 1 || flag == 2)
				break;
		}
	}
	
	//Ŭ���̾�Ʈ ����Ʈ hm�� �ִ� �����鿡�� �޼��� ������
	public static void broadcast(String msg) {
        synchronized (hm) {
        	if (hm.isEmpty() == true);
            Collection<Object> collection = hm.values();
            Iterator<?> iter = collection.iterator();
            while(iter.hasNext()) {
                PrintWriter pw = (PrintWriter)iter.next();
                pw.println(msg);
                pw.flush();
            }
        }
    }
}
