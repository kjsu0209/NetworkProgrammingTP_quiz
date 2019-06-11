//ClientThread: 클라이언트에게서 문제 답 받고 채점 결과 전송
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
	private Socket sock; //소켓 정보
	private String id; //유저 id
	private int select; //답
	private BufferedReader br; //받은 메세지 읽기
	private PrintWriter pw;
	private boolean initFlag = false; //처음 접속했으면 false
	private int nowNum = 0; //현재 문제 번호
	private static HashMap<String, Object> hm; //유저 정보 리스트
	static int flag = 0;
	
	public ClientThread(Socket sock, HashMap<String, Object> hm, PrintWriter pw) {
		this.sock = sock;
		this.hm = hm;
		this.pw = pw;
		
		try
		{
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			id = br.readLine(); // 처음에 클라이언트 소켓이 접속하면 id를 보낸다.
			System.out.println("접속한 사용자의 아이디 : "+id);
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
	                // 정답을 보낼 경우 채점 후 결과 전달
	            	// 결과 받아서 출력하는것까지만 구현했음
	            	line = br.readLine();
	            	select = Integer.parseInt(line); // 답 저장
	            	System.out.println("선택한 답:"+select + " 정답:"+Server.test[i].answer);
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
	            broadcast(id+"님이 접속을 종료했습니다.");
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
	
	//클라이언트 리스트 hm에 있는 유저들에게 메세지 보내기
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
