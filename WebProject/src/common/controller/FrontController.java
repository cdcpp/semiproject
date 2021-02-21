package common.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@WebServlet(
		urlPatterns = { "*.do" }, 
		initParams = { 
				@WebInitParam(name = "config",
						value = "C:\\myjava\\Workspace\\WebProject\\WebContent\\WEB-INF\\Command.properties"
						)
		})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> cmdMap = new HashMap<>();
	
	/*서블릿 실행시 첫요청이 있을때 딱 한번 호출되는 메소드(init)
	 * init-param 에 기술되어있는 config에 해당하는 value값
	 * (Command.properties의 절대경로)을 얻어오자.
	 * web.xml에 기술. 최근에는 어노테이션으로 대치됨.
	 * */

	public void init(ServletConfig config) throws ServletException {
		String props=config.getInitParameter("config");
		System.out.println("init() props="+props);
		Properties pr=new Properties();
		//key=value 매핑하여 저장하는 자료구조. 
		//Command.properties파일에 매핑된 정보를 Properties로 옮기자.
		try {
		FileInputStream fis=new FileInputStream(props);
		pr.load(fis);
		if(fis!=null) fis.close();
		System.out.println(pr.getProperty("/index.do"));
		
		//pr에서 key값들만 추출하자.
		Set<Object> set= pr.keySet();
		
		for(Object key:set) {
			String cmd=key.toString(); //key값 /index.do
			String className=pr.getProperty(cmd);
			System.out.println("cmd="+cmd); //   /index.do 
			System.out.println("className"+className); 
									//common.controller.IndexAction
			if(className!=null) {
				className=className.trim();//공백 제거
				Class cmdClass=Class.forName(className);
				Object cmdInstance=cmdClass.newInstance();
				//해당 클래스를 객체화 시켜 메모리에 올려놓는다.
				cmdMap.put(cmd, cmdInstance);
				///////////////////
			}
			
		}//for
		
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}//init()----------------------

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		requestProcess(req, res);
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		requestProcess(req, res);
		
	}


	private void requestProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{
		System.out.println("FrontController실행됨..");
		//1. 클의 요청을 분석해서 Sub Controller(XXX Action)에게
		// 요청을 처리하도록 위임한다.
		String uri=req.getRequestURI();
		System.out.println("uri="+uri); // "/MVCWeb/index.do" 
		
		String myctx=req.getContextPath(); //  "/MVCWeb"
		int len=myctx.length(); //6
		String cmd=uri.substring(len); // "index.do"
		Object instance=cmdMap.get(cmd); // IndexAction 객체
		if(instance==null) {
			
			System.out.println("액션이 null");
			//return;
			throw new ServletException(cmd+": 액션이 널이에요");
		}//if
		AbstractAction action=(AbstractAction)instance;
		//서브컨트롤러의 execute()호출하기
		///////////////////////
		try {
			action.execute(req, res);
			///////////////////////
			//이동할 뷰페이지 얻기
			String viewPage=action.getViewPage();
			if(viewPage==null) {
				System.out.println("뷰페이지가 널");
				viewPage="test.html";
				//디폴트 페이지를 test.html로 지정
			}
			// 이동방식에 따라서 뷰페이지로 이동
			if(action.isRedirect()) {
				//redirect로 이동인 경우(true)
				res.sendRedirect(viewPage);
			}else {
				//forward 이동인 경우(false)
				RequestDispatcher disp = req.getRequestDispatcher(viewPage);
				disp.forward(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		//////////////////////
	}

}/////////////////////////////////////////////////////////////////////////
