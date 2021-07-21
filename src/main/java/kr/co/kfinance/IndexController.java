package kr.co.kfinance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String index() {
		return "index";
	}

	@GetMapping("manager")
	public String manager() {
		return "manager";
	}
}
