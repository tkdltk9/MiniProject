package miniHotelProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("corner")
public class CornerController {
	@GetMapping("detailView")
	public String detailView(@RequestParam("goodsNum") String goodsNum) {
		
		return "thymeleaf/item/detailView";
	}
	
}
