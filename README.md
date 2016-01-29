#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
 > DB 초기화를 담당하는 ContextLoaderListner 클래스에서 ResourceDatabasePopulator라는 객체를 하나 생성하고 
ClassPathResource라는 객체를 통해 스크립트를 추가한다.
이후에 실행할 때에는 스크립트 정보를 가지고 있는 ResourceDatabasePopulator의 객체와 데이터베이스 드라이버, url, 유저네임과
패스워드를 담고 있는 객체로부터의 정보를 읽어와서 연결을 하면서 DB 초기화가 진행된다

 > 그 후에, RequestMapping 클래스의 initMapping 메서드를 보면 그 중에 mappings.put("/qna/list.next", new ListController()); 라는
라인이 있는데 브라우저 상에서 localhost:8080으로 접근하면 /qna/list.next 로 리다이랙트 되도록 설정이 되어있기 때문에 ListController
객체를 생성하면서 시작된다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
 > WebServerLauncher 클래스에서 톰캣 객체를 하나 생성하고, 앱솔루트 패쓰을 찾아가서 디폴트 주소로 정하기 위한
파일을 찾는데 webapp/이라는 경로만 지정해주고 '/'를 넣어서 앱솔루트 패쓰로 정할 url를 찾도록 하는데
디폴트로 설정되어 있는 index.jsp 파일 안의 response.sendRedirect("/qna/list.next"); 라인을 참조해
/qna/list.next을 앱솔루트 패쓰로 정한다.

