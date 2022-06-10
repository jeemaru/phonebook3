package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
//@RequestMapping(value="/guest")
public class PhoneController {
	
	//필드
	
	//생성자
	
	//gs
	
	//메소드
	
	//전화번호 등록
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam("name") String name,
						@RequestParam("hp") String hp,
						@RequestParam("company") String company) {
		System.out.println("PhoneController>write()");
		
		//파라미터 꺼내기
		/*
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		*/
		
		//vo로 묶기
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);
		
		//dao로 저장하기
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personInsert(personVo);
		System.out.println(count);
		return "";

	}
	
	
	//전화번호 등록 폼
	@RequestMapping(value="/writeForm", method={RequestMethod.GET, RequestMethod.POST} )
	public String writeForm() {
		
		System.out.println("PhoneController>writeForm()");
		return "/WEB-INF/views/writeForm.jsp";
		
	}
	
	
	
	@RequestMapping(value="/test", method={RequestMethod.GET, RequestMethod.POST} )
	public String test() {
		
		System.out.println("PhoneController>test()");
		return "/WEB-INF/views/test.jsp";
	}

	
	
}
