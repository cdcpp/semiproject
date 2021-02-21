package shop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartBean implements Serializable{
	private List<ProductVO> cartList
	=new ArrayList<>();

/**장바구니에 상품을 추가하는 메소드*/
public void addProduct(int pnum, ProductVO item) {
	/* 1. 새로 추가하려는 상품이 이미 장바구니(cartList)
	 * 에 있는지 검색한다.
	 * 1) 이미 있는 상품이라면 => 이미 추가한 상품입니다 => 거래중? 처리할것
	 * 
	 * */
	for(ProductVO pd:cartList) {
		int pnum2=pd.getPnum();
		if(pnum2==pnum) {
			//이미 담긴 상품이라면
			System.out.println("처리중");
			return;
		}//if--
		
	}//for------
	
	/*2. 그렇지 않고 새롭게 추가하는 상품이라면
	 *   장바구니(cartList)에 저장하자*/
	if(item!=null) {
		
		cartList.add(item);
	}
	
	
	
	
}

public List<ProductVO> getCartList() {
	return cartList;
}//----

/*장바구니 상품의 총액과 총포인트를 반환하는 메소드*/
public Map<String,Integer> getCartTotal(){
	Map<String,Integer> map=new HashMap<>();
	int totalPrice=0;
	for(ProductVO pd:cartList) {
		totalPrice+=pd.getTotalPrice();
	}//for----
	map.put("TotalPrice", totalPrice);
	return map;
}//------------------

/**장바구니에서 특정 상품을 삭제하는 메소드*/
public void removeProduct(int pnum) {
	//반복문 돌면서 cartList에서 상품을 꺼내와서
	//해당 상품의 상품번호와 삭제하려는 상품번호가 같은지 체크
	//같다면 cartList에서 해당 상품 제거
	for(ProductVO pd:cartList) {
		
		if(pd.getPnum()==pnum) {
			cartList.remove(pd);
			break;
		}//if
	}///for
}///

}
