package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO mDao;

	//서비스 => DAO에서 받아온 내용으로 처리
	@Override
	public MemberVO isLogin(String id, String pwd) {
		// TODO Auto-generated method stub
		MemberVO vo=new MemberVO();
		int count=mDao.memberIdCount(id);
		if(count==0) {
			// ID가 없는 상태
			vo.setMsg("NOID");
		}else {
			MemberVO dvo=mDao.memberGetPassword(id);
			if(pwd.equals(dvo.getPwd())) { //로그인
				vo.setId(dvo.getId());
				vo.setName(dvo.getName());
				vo.setAdmin(dvo.getAdmin());
				vo.setMsg("OK");
				//session에 저장 먹족
			}else { //다른 비밀번호
				vo.setMsg("NOPWD");
			}
		}
		return vo;
	}
	
}
