package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SearchNaverProxy {
	// public static void main(String[] args) 
	public String getData(String query, int start, int display)
	{
	        String clientId = "3rfay8xcs0RC2qwWW5SJ";//애플리케이션 클라이언트 아이디값";
	        String clientSecret = "L30wMHd7eF";//애플리케이션 클라이언트 시크릿값";
	        try {
	            String text = URLEncoder.encode(query, "UTF-8");
	            String apiURL="https://openapi.naver.com/v1/search/shop.json?query="+text+"&start="+start+"&display="+display;
	            //String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text; // json 결과
	            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("GET");
	            con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"UTF-8"));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
	            System.out.println(response.toString());
	            
	            return response.toString();
	        } catch (Exception e) {
	            System.out.println(e);
	            return null;
	        }
	    }//main
}
