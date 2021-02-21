package shop.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.controller.AbstractAction;
import shop.model.ProductDAO;
import shop.model.ProductVO;

public class ProdInputAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("-------------------");
		ServletContext app=req.getServletContext();
		//String upDir=app.getRealPath("/product_images");
		String upDir="C:\\myjava\\Workspace\\WebProject\\WebContent\\product_images";
		
		DefaultFileRenamePolicy df
		=new DefaultFileRenamePolicy();
		
		MultipartRequest mr
		=new MultipartRequest(req,upDir,10*1024*1024,"utf-8",df);
		System.out.println("업로드 성공!");
		
		String pname =mr.getParameter("pname");
		String upcodeStr=mr.getParameter("upcode");
		String downcodeStr=mr.getParameter("downcode");
		
		String pimage1=mr.getFilesystemName("pimage1");
		String priceStr=mr.getParameter("price");
		String pcont=mr.getParameter("pcont");
		System.out.println(pname);
		System.out.println(upcodeStr);
		System.out.println(downcodeStr);
		if(pname==null||upcodeStr==null
				||downcodeStr==null||
				upcodeStr.trim().isEmpty()||
				downcodeStr.trim().isEmpty()) 
		{
			this.setViewPage("input.do");
			this.setRedirect(true);
			return;
		}//
		
		int upCode=Integer.parseInt(upcodeStr);
		int downCode=Integer.parseInt(downcodeStr);
		int price=Integer.parseInt(priceStr);
		
		ProductVO vo = new ProductVO(0, pname, upCode, downCode, pimage1, price, pcont, null);
		
		ProductDAO dao = new ProductDAO();
		int n=dao.productInsert(vo);
		
		String str=(n>0)?"상품등록 성공":"상품등록 실패";
		String loc=(n>0)?"../index.do":"javascript:history.back()";
		System.out.println(str);
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		
		this.setRedirect(false);
		this.setViewPage("/Main.jsp");
		
	}

}
