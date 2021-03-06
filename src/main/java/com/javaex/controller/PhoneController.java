package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.service.PhoneService;
import com.javaex.vo.PersonVo;

@Controller
//@RequestMapping(value="/guest")
public class PhoneController {
	
	//필드
	@Autowired
	private PhoneService phoneService;
	
	//생성자
	
	//gs
	
	//메소드
	
	//전화번호 리스트
		@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
		public String list(Model model) {
			System.out.println("PhoneController>list");
			
			//DAO를 통해서 PersonList 가져오기
			//PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList =  phoneService.getPersonList();
			
			//ds 데이터 보내기 --> request attribute에 넣는다
			model.addAttribute("personList",personList);
			
			return "/WEB-INF/views/list.jsp";
		}
	
	

	
	//수정폼
	@RequestMapping(value="/updateForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String updateForm(Model model, @RequestParam("no") int no) {
		System.out.println("PhoneController>updateForm()");

		PersonVo personVo = phoneService.getPerson(no);
		
		model.addAttribute("personVo", personVo);
		
		return "/WEB-INF/views/updateForm.jsp";
	}
	
	//수정
	@RequestMapping(value="/update", method= {RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController>update()");
		
		int count = phoneService.personUpdate(personVo);
		System.out.println(count);
		
		return "redirect:/list"; 
	}
	
	//삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("PhoneController>delete");
		
		//값 꺼내기
		System.out.println(no);
		
		//Dao처리하기
		int count = phoneService.personDelete(no);
		System.out.println(count);
		
		return "redirect:/list";
	}
	
	
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController>write()");

		System.out.println(personVo);
		//dao로 저장하기
		int count = phoneService.personInsert(personVo);
		System.out.println(count);
		
		//리다이렉트
		return "redirect:/list";

	}
	
	
	//전화번호 등록
	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam("name") String name,
						@RequestParam("hp") String hp,
						@RequestParam("company") String company) {
		System.out.println("PhoneController>write2()");
		
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
		
		//리다이렉트
		return "redirect:/list";

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
