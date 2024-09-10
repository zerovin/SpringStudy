package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Controller
@RequestMapping("recipe/")
public class RecipeController {
	@Autowired
	private RecipeDAO rDao;
	
	@GetMapping("detail.do")
	//recipe/detail.do?no=123 ==> 매개변수 => DispatcherServlet
	/*
	 *  매개변수 데이터형
	 *  1. int
	 *  2. String
	 *  3. String[]
	 *  4. ~VO
	 *  
	 *  매개변수 내장객체
	 *  1. Model : 전송할 데이터가 있는 경우
	 *  2. HttpSession
	 *  3. HttpServletRequest / HttpServletResponse => Cookie, 업로드
	 *  4. RedirectAttributes : sendRedirect 사용시 데이터 전송
	 *  
	 */
	public String recipe_detail(int no, Model model) {
		//String no=request.getParameter("no") => Integer.parseInt(no)
		//DB연동
		RecipeDetailVO vo=rDao.recipeDetailData(no);
		String data=vo.getData();
		data.replace("구매", "");
		vo.setData(data.trim());
		
		List<String> mList=new ArrayList<String>();
		List<String> iList=new ArrayList<String>();
		
		String[] foodMake=vo.getFoodmake().split("\n");
		for(String fm:foodMake) {
			StringTokenizer st=new StringTokenizer(fm, "^");
			mList.add(st.nextToken());
			iList.add(st.nextToken());
		}
		
		//JSP 출력할 데이터 전송
		model.addAttribute("vo", vo);
		model.addAttribute("mList", mList);
		model.addAttribute("iList", iList);
		return "recipe/detail";
	}
	
	//검색:POST, 페이징:GET => POST+GET 동시사용 => Request
	@RequestMapping("find.do")
	public String recipe_find(String fd, String page, Model model) {
		if(fd==null) {
			fd="비빔밥";
		}
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("fd", fd);
		
		//DB연동
		List<RecipeVO> list=rDao.recipeFindData(map);
		int totalpage=rDao.recipeFindTotalPage(map);
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage=totalpage;
		}
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("fd", fd);
		return "recipe/find";
	}
	
	@GetMapping("chef_list.do")
	public String recipe_chef_list(String page, Model model) {
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		//DB연동
		List<ChefVO> list=rDao.chefListData(map);
		int totalpage=rDao.chefTotalPage();
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage=totalpage;
		}
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		
		return "recipe/chef_list";
	}
	
	@GetMapping("chef_make.do")
	public String recipe_chef_make(String page, String chef, Model model) {
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("chef", chef);
		
		//DB연동
		List<RecipeVO> list=rDao.chefMakeData(map);
		int totalpage=rDao.chefMakeTotalData(map);
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage=totalpage;
		}
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("chef", chef);
		return "recipe/chef_make";
	}
}
