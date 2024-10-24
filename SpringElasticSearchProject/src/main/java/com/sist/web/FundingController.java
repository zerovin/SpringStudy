package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FundingController {
	@GetMapping("funding/find.do")
	public String funding_find() {
		return "funding/find";
	}
}
