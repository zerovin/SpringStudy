package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;
@RestController
public class SeoulRestController {
	@Autowired
	private SeoulService sService;
	
	@GetMapping(value="seoul/location_vue.do", produces="text/plain;charset=UTF-8")
	public String seoul_location(int page) throws Exception{
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<SeoulVO> list=sService.seoulLocationListData(map);
		int totalpage=sService.seoulLocationTotalPage();
		
		final int BLOCK=10;
		int startpage=((page-1)/BLOCK*BLOCK)+1;
		int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage=totalpage;
		}
		map=new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	
	@GetMapping(value="seoul/nature_vue.do", produces="text/plain;charset=UTF-8")
	public String seoul_nature(int page) throws Exception{
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<SeoulVO> list=sService.seoulNatureListData(map);
		int totalpage=sService.seoulNatureTotalPage();
		
		final int BLOCK=10;
		int startpage=((page-1)/BLOCK*BLOCK)+1;
		int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage=totalpage;
		}
		map=new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}

	@GetMapping(value="seoul/shop_vue.do", produces="text/plain;charset=UTF-8")
	public String seoul_shop(int page) throws Exception{
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("pStart", start);
		map.put("pEnd", end);
		
		List<SeoulVO> list=sService.seoulShopListData(map);
		int totalpage=sService.seoulShopTotalPage();
		
		final int BLOCK=10;
		int startpage=((page-1)/BLOCK*BLOCK)+1;
		int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage=totalpage;
		}
		map=new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}

	
	@GetMapping(value="seoul/location_detail_vue.do", produces="text/plain;charset=UTF-8")
	public String location_detail(int no) throws Exception{
		//03045 서울 종로구 삼청로 37 (세종로, 국립민속박물관)
		SeoulVO vo=sService.seoulLocationDetailData(no);
		String addr1=vo.getAddress();
		addr1=addr1.substring(addr1.indexOf(" ")+1);
		String addr2=addr1.trim();
		addr2=addr2.substring(addr2.indexOf(" ")+1);
		String addr3=addr2.trim();
		addr3=addr3.substring(0, addr3.indexOf(" "));
		vo.setAddr(addr3.trim());
		//System.out.println("주소 : "+vo.getAddr());
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value="seoul/nature_detail_vue.do", produces="text/plain;charset=UTF-8")
	public String nature_detail(int no) throws Exception{
		//03045 서울 종로구 삼청로 37 (세종로, 국립민속박물관)
		SeoulVO vo=sService.seoulNatureDetailData(no);
		String addr1=vo.getAddress();
		addr1=addr1.substring(addr1.indexOf(" ")+1);
		String addr2=addr1.trim();
		addr2=addr2.substring(addr2.indexOf(" ")+1);
		String addr3=addr2.trim();
		addr3=addr3.substring(0, addr3.indexOf(" "));
		vo.setAddr(addr3.trim());
		//System.out.println("주소 : "+vo.getAddr());
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value="seoul/shop_detail_vue.do", produces="text/plain;charset=UTF-8")
	public String shop_detail(int no) throws Exception{
		//03045 서울 종로구 삼청로 37 (세종로, 국립민속박물관)
		Map map=new HashMap();
		map.put("pNo",  no);
		SeoulVO vo=sService.seoulShopDetailData(map);
		String addr1=vo.getAddress();
		addr1=addr1.substring(addr1.indexOf(" ")+1);
		String addr2=addr1.trim();
		addr2=addr2.substring(addr2.indexOf(" ")+1);
		String addr3=addr2.trim();
		addr3=addr3.substring(0, addr3.indexOf(" "));
		vo.setAddr(addr3.trim());
		//System.out.println("주소 : "+vo.getAddr());
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
}
