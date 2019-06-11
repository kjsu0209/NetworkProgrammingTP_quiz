package project;

public class Problem {
	public String question;//문제
	public String[] select = new String[4];//선택지 string 배열
	public int answer;//정답
	
	//문제 객체 생성자
	public Problem(String input_q, String[] input_s, int input_a) {
		this.question = input_q;
		this.select = input_s;
		this.answer = input_a;
	}
}