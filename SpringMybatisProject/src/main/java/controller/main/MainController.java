package controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("main")
	public String aaa() {
		return "main/main";
	}
}
