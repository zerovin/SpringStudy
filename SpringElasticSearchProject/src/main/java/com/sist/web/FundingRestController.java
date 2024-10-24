package com.sist.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.io.*;
import java.net.*;
@RestController
public class FundingRestController {
	@GetMapping(value="funding/find_vue.do", produces="text/plain;charset=UTF-8")
	public String funding_find(String title) throws Exception{
		String strUrl="http://localhost:9200/funding/_search?q=title="+URLEncoder.encode(title, "UTF-8");
		URL url=new URL(strUrl);
		//URL 연결
		HttpURLConnection conn=(HttpURLConnection)url.openConnection();
		StringBuffer sb=new StringBuffer(); //데이터를 모아둔다
		if(conn!=null) { //사이트에 연결이 된 경우
			BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			while(true) {
				String data=in.readLine();
				if(data==null) break;
				sb.append(data);
			}
			in.close();
		}
		return sb.toString();
	}
}
