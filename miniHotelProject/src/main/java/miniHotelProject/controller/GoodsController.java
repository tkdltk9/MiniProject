package miniHotelProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import miniHotelProject.command.GoodsCommand;
import miniHotelProject.service.AutoNumService;


@RequestMapping("goods")
@Controller
public class GoodsController {
	@Autowired
	AutoNumService autoNumService;
	@GetMapping("goodsList")
	public String goodsList() {
		return "thymeleaf/goods/goodsList";
	}
	@GetMapping("goodsForm")
	public String goodsForm(Model model) {
		String autoNum = autoNumService.execute("hotel_", "goods_num", 7, "goods");
		GoodsCommand goodsCommand = new GoodsCommand();
		goodsCommand.setGoodsNum(autoNum);
		model.addAttribute("goodsCommand", goodsCommand);
		return "thymeleaf/goods/goodsForm";
	}
	
}
