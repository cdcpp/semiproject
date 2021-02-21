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
	
	/*���� ����� ù��û�� ������ �� �ѹ� ȣ��Ǵ� �޼ҵ�(init)
	 * init-param �� ����Ǿ��ִ� config�� �ش��ϴ� value��
	 * (Command.properties�� ������)�� ������.
	 * web.xml�� ���. �ֱٿ��� ������̼����� ��ġ��.
	 * */

	public void init(ServletConfig config) throws ServletException {
		String props=config.getInitParameter("config");
		System.out.println("init() props="+props);
		Properties pr=new Properties();
		//key=value �����Ͽ� �����ϴ� �ڷᱸ��. 
		//Command.properties���Ͽ� ���ε� ������ Properties�� �ű���.
		try {
		FileInputStream fis=new FileInputStream(props);
		pr.load(fis);
		if(fis!=null) fis.close();
		System.out.println(pr.getProperty("/index.do"));
		
		//pr���� key���鸸 ��������.
		Set<Object> set= pr.keySet();
		
		for(Object key:set) {
			String cmd=key.toString(); //key�� /index.do
			String className=pr.getProperty(cmd);
			System.out.println("cmd="+cmd); //   /index.do 
			System.out.println("className"+className); 
									//common.controller.IndexAction
			if(className!=null) {
				className=className.trim();//���� ����
				Class cmdClass=Class.forName(className);
				Object cmdInstance=cmdClass.newInstance();
				//�ش� Ŭ������ ��üȭ ���� �޸𸮿� �÷����´�.
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
		System.out.println("FrontController�����..");
		//1. Ŭ�� ��û�� �м��ؼ� Sub Controller(XXX Action)����
		// ��û�� ó���ϵ��� �����Ѵ�.
		String uri=req.getRequestURI();
		System.out.println("uri="+uri); // "/MVCWeb/index.do" 
		
		String myctx=req.getContextPath(); //  "/MVCWeb"
		int len=myctx.length(); //6
		String cmd=uri.substring(len); // "index.do"
		Object instance=cmdMap.get(cmd); // IndexAction ��ü
		if(instance==null) {
			
			System.out.println("�׼��� null");
			//return;
			throw new ServletException(cmd+": �׼��� ���̿���");
		}//if
		AbstractAction action=(AbstractAction)instance;
		//������Ʈ�ѷ��� execute()ȣ���ϱ�
		///////////////////////
		try {
			action.execute(req, res);
			///////////////////////
			//�̵��� �������� ���
			String viewPage=action.getViewPage();
			if(viewPage==null) {
				System.out.println("���������� ��");
				viewPage="test.html";
				//����Ʈ �������� test.html�� ����
			}
			// �̵���Ŀ� ���� ���������� �̵�
			if(action.isRedirect()) {
				//redirect�� �̵��� ���(true)
				res.sendRedirect(viewPage);
			}else {
				//forward �̵��� ���(false)
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
