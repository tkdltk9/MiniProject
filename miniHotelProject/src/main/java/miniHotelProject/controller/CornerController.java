package miniHotelProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import miniHotelProject.service.item.GoodsDetailViewService;

@Controller
@RequestMapping("corner")
public class CornerController {
	@Autowired
	GoodsDetailViewService goodsDetailViewService;
	@GetMapping("detailView")
	public String detailView(@RequestParam("goodsNum") String goodsNum
			, Model model
			, HttpServletResponse response
			, HttpSession session) {
		goodsDetailViewService.execute(goodsNum, model, response, session);
		return "thymeleaf/item/detailView";
	}
}
