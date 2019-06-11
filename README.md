# NetworkProgrammingTP_quiz
- Server
ClientThread : client에게서 답 받고 채점 결과를 전송하는 쓰레드
Problem : 질문, 답, 선택지가 저장되는 문제 객체
Server : 서버 메인 클래스
ServerPanel : 게임 시작 후 문제 UI 패널
ServerThread : client에게 문제 발송하는 쓰레드
SurvivorPanel : 게임 종료 시 생존자 현황 보여주는 UI 패널
TimerThread : 타이머 쓰레드
- Client
Client: client 메인 클래스
exitPanel: 게임 종료 시 UI 패널
GamePanel: 문제와 선택지 보여주는 UI 패널
OxPanel: 채점 결과 보여주는 UI 패널
ReadThread: 서버로부터 문제 데이터, 채점 결과 수신하는 쓰레드
TimerThread: 타이머 쓰레드
WaitPanel: 게임 시작 대기 UI 패널
