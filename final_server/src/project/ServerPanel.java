package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ServerPanel extends JPanel {
	public JPanel panelQ = new JPanel(); // 질문 나오는 패널
	public JPanel panelS = new JPanel(); // 선택지 나오는 패널
	public JPanel panelC = new JPanel(); // 살아남은 클라이언트 나오는 패널
	static Font comic_sans = new Font("Comic Sans MS", Font.BOLD, 20);
	static Font comic_sans15 = new Font("Comic Sans MS", Font.BOLD, 15);
	public ServerPanel(Problem p, HashMap<String, Object> hm) {
	
		//질문 패널 설정
		Server.frame.setBackground(Color.WHITE);
		JPanel panelC = new JPanel();
		//panel0.setLayout(new BoxLayout(panel0, BoxLayout.X_AXIS));
		panelQ.setSize(600,150);
		panelS.setSize(600,150);
		panelC.setSize(100,300);
		
		JLabel question = new JLabel(p.question);
		question.setFont(comic_sans);
		//question.setHorizontalTextPosition(SwingConstants.CENTER);
		panelQ.setBackground(Color.decode("0X88FF88"));
		panelQ.add(question);
		Server.frame.add(panelQ, BorderLayout.NORTH);
		
		//선택지 패널 설정 ( 답일 경우 색깔 다르게 ) - 색 바꾸는건 나중에 추가
		//JLabel select[] = new JLabel[4];
		for (int i =0 ;i<4;i++) {
			if(p.answer == i+1) { //답일 경우
				JLabel select = new JLabel(p.select[i]);
				select.setFont(comic_sans);
				panelS.add(select);
			}
			else {
				JLabel select = new JLabel(p.select[i]);
				select.setFont(comic_sans15);
				panelS.add(select);
			}
		}
		panelS.setBackground(Color.YELLOW);
		panelS.setLayout(new FlowLayout(WIDTH, 20, 5));
		add(panelS, BorderLayout.CENTER);
		//Server.contentpane.add(panelS, BorderLayout.CENTER);
		//살아남은 클라이언트 나오는 패널
		/*Set set = hm.keySet();
        Iterator<?> iter = set.iterator();
        while(iter.hasNext()) {
            JLabel survivor = new JLabel(iter.next().toString());
            survivor.setFont(comic_sans);
            panelC.add(survivor);
        }
		panelC.setLayout(new FlowLayout(FlowLayout.LEFT));
//		panelC.setBackground(Color.WHITE);
		Server.contentpane.add(panelC, BorderLayout.EAST);*/
        setVisible(true); 
	}
}
