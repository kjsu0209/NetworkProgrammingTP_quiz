# NetworkProgrammingTP_quiz
 이번 프로젝트에서는 서바이벌 퀴즈 게임을 TCP 서버/클라이언트 소켓 프로그램으로 구현하였습니다. 멀티 클라이언트 서버를 구현하기 위해 멀티쓰레드 방식을 사용했습니다
## Server
1. ClientThread : client에게서 답 받고 채점 결과를 전송하는 쓰레드
2. Problem : 질문, 답, 선택지가 저장되는 문제 객체
3. Server : 서버 메인 클래스
4. ServerPanel : 게임 시작 후 문제 UI 패널
5. ServerThread : client에게 문제 발송하는 쓰레드
6. SurvivorPanel : 게임 종료 시 생존자 현황 보여주는 UI 패널
7. TimerThread : 타이머 쓰레드
## Client
1. Client: client 메인 클래스
2. exitPanel: 게임 종료 시 UI 패널
3. GamePanel: 문제와 선택지 보여주는 UI 패널
4. OxPanel: 채점 결과 보여주는 UI 패널
5. ReadThread: 서버로부터 문제 데이터, 채점 결과 수신하는 쓰레드
6. TimerThread: 타이머 쓰레드
7. WaitPanel: 게임 시작 대기 UI 패널

## 

<https://www.youtube.com/watch?v=E_E69lraVgc>
