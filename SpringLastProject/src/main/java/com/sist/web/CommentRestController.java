package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;
import com.sist.vo.*;
@RestController
public class CommentRestController {
	@Autowired
	private CommentService cService;
	
	public String commonsListData(int page, int rno, int type) throws Exception{
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		map.put("start", start);
		map.put("end", end);
		map.put("rno", rno);
		map.put("type", type);
		
		List<CommentVO> list=cService.commentListData(map);
		int totalpage=cService.commentTotalPage(map);
		
		final int BLOCK=5;
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
	
	@GetMapping(value="comment/list_vue.do", produces="text/plain;charset=UTF-8")
	public String comment_list(int page, int rno, int type) throws Exception{
		return commonsListData(page, rno, type);
	}
	
	@PostMapping(value="comment/insert_vue.do", produces="text/plain;charset=UTF-8")
	public String comment_insert(CommentVO vo, HttpSession session) throws Exception{
		String id=(String)session.getAttribute("userId");
		String name=(String)session.getAttribute("userName");
		String sex=(String)session.getAttribute("sex");
		vo.setId(id);
		vo.setName(name);
		vo.setSex(sex);
		cService.commentInsert(vo);
		// id, name 저장
		return commonsListData(1, vo.getRno(), vo.getType());
	}
	
	@PostMapping(value="comment/reply_insert_vue.do", produces="text/plain;charset=UTF-8")
	public String comment_reply_insert(int cno, CommentVO vo, HttpSession session) throws Exception{
		String id=(String)session.getAttribute("userId");
		String name=(String)session.getAttribute("userName");
		String sex=(String)session.getAttribute("sex");
		vo.setId(id);
		vo.setName(name);
		vo.setSex(sex);
		cService.commentReplyReplyInsert(cno, vo);
		return commonsListData(1, vo.getRno(), vo.getType());
	}
	
	@GetMapping(value="comment/delete_vue.do", produces="text/plain;charset=UTF-8")
	public String comment_delete(int cno, int rno, int type) throws Exception{
		//데이터베이스 연동
		return commonsListData(1, rno, type);
	}
}
