package project;

public class Problem {
	public String question;//����
	public String[] select = new String[4];//������ string �迭
	public int answer;//����
	
	//���� ��ü ������
	public Problem(String input_q, String[] input_s, int input_a) {
		this.question = input_q;
		this.select = input_s;
		this.answer = input_a;
	}
}