package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.ProcessBuilder.Redirect;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.service.*;
import com.sist.vo.*;

@Controller
public class RecipeController {
	@Autowired
	private RecipeService rService;
	/*
	@Autowired //생성자에 매개변수로 받아서 Autowired처리가 안전, 권장
	public RecipeController(RecipeService rService) {
		this.rService=rService;
	}
	*/
	
	@GetMapping("recipe/detail_before.do")
	public String recipe_detail_before(int no, HttpServletResponse response, RedirectAttributes ra) {
		// 전송 => Model : forward
		//        RedirectAttributes : sendRedirect
		
		// Cookie제작 => 저장 => 브라우저 전송 HttpServletResponse response 매개변수 필수
		Cookie cookie=new Cookie("recipe_"+no, String.valueOf(no));
		// 쿠키 - 브라우저에 저장, 문자열만 저장가능 키 getName(), 값 getValue()
		cookie.setMaxAge(60*60*24); // 저장기간 - 초단위 저장
		cookie.setPath("/"); //저장 위치
		//브라우저로 전송
		response.addCookie(cookie);
		
		ra.addAttribute("no", no);
		// ra.addFlashAttribute("no", no); 데이터 감춰서 보내기
		return "redirect:../recipe/detail.do";
	}
	
	@GetMapping("recipe/detail.do")
	public String recipe_detail(int no, Model model) {
		//데이터베이스 연결 => 데이터 읽기
		RecipeDetailVO vo=rService.recipeDetailData(no);
		
		String data=vo.getData();
		data=data.replace("구매", "");
		vo.setData(data.trim());
		//detail.jsp로 출력할 데이터를 보내준다
		model.addAttribute("vo", vo);
		
		List<String> mList=new ArrayList<String>();
		List<String> iList=new ArrayList<String>();
		
		String[] make=vo.getFoodmake().split("\n");
		for(String m:make) {
			StringTokenizer st=new StringTokenizer(m, "^");
			mList.add(st.nextToken());
			iList.add(st.nextToken());
		}
		model.addAttribute("mList", mList); //레시피 방식
		model.addAttribute("iList", iList); //레시피 이미지
		
		//Model은 JSP로 전송시 사용 => forward
		model.addAttribute("main_jsp", "../recipe/detail.jsp");
		return "main/main";
	}
	
	@GetMapping("recipe/chef_list.do")
	/*
	 *  반복 코딩 => 메소드처리 - 반복제거, 반드시 호출해서 사용
	 *             AOP - 공통사항 처리, 자동 호출
	 *  호출 위치 => 지정(JoinPoint)
	 *  public void display(){
	 *     => 호출 before
	 *     try{
	 *        => 핵심 모듈
	 *     }catch(Exception ex{
	 *        => 호출 after-throwing
	 *     }finally{
	 *        => 호출 after
	 *     }
	 *     return => 호출 after-returning
	 *  }
	 */
	public String recipe_chef_list(String page, Model model) {
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=50;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("start", start);
		map.put("end", end);
		
		//데이터베이스연동, 목록읽기
		List<ChefVO> list=rService.chefListData(map);
		
		//총페이지 읽기
		int totalpage=rService.chefTotalPage();
		
		//데이터전송
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("main_jsp", "../recipe/chef_list.jsp");
		return "main/main";
	}
	
	//쉐프 상세
	@GetMapping("recipe/chef_detail.do")
	public String recipe_chef_detail(String page, String chef, Model model) {
		// 셰프가 만든 레시피 목록 출력
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		map.put("chef", chef);
		map.put("start", start);
		map.put("end", end);
		
		// 목록
		List<RecipeVO> list=rService.chefMakeRecipeData(map);
		
		// 총페이지
		int totalpage=rService.chefMakeRecipeTotalPage(chef);
		
		//전송
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("chef", chef);
		model.addAttribute("main_jsp", "../recipe/chef_detail.jsp");
		return "main/main";
	}
	
	@GetMapping("recipe/cookie_all.do")
	public String recipe_cookie_all(HttpServletRequest request, Model model) {
		//쿠키 출력 HttpServletRequest request 매개변수 필요
		Cookie[] cookies=request.getCookies();
		//쿠키 담는 List
		List<RecipeVO> cList=new ArrayList<RecipeVO>();
		
		if(cookies!=null) {
			//최신부터 담는다
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("recipe_")) {
					String no=cookies[i].getValue();
					RecipeVO vo=rService.recipeCookieInfoData(Integer.parseInt(no));
					cList.add(vo);
				}
			}
		}
		model.addAttribute("cList", cList);
		model.addAttribute("size", cList.size());
		model.addAttribute("main_jsp", "../recipe/cookie_all.jsp");
		return "main/main";
	}
	
	@GetMapping("recipe/cookie_delete.do")
	public String recipe_cookie_delete(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				if(cookies[i].getName().startsWith("recipe_")) {
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
		}
		return "redirect:../main/main.do";
	}
}
