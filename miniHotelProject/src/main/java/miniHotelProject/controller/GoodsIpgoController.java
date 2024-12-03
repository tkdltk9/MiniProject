package miniHotelProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.command.GoodsIpgoCommand;
import miniHotelProject.domain.GoodsIpgoNameDTO;
import miniHotelProject.service.AutoNumService;
import miniHotelProject.service.goods.GoodsListService;
import miniHotelProject.service.goodsIpgo.GoodsIpgoService;
import miniHotelProject.service.goodsIpgo.IpgoDetailService;
import miniHotelProject.service.goodsIpgo.IpgoListService;





@Controller
@RequestMapping("ipgo")
public class GoodsIpgoController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	GoodsIpgoService goodsIpgoService;
	@Autowired
	IpgoListService ipgoListService;
	@Autowired
	IpgoDetailService ipgoDetailService;
	@GetMapping("goodsIpgoList")
	public String goodsIpgoList(Model model) {
		ipgoListService.execute(model);
		return "thymeleaf/goodsIpgo/goodsIpgoList";
	}
	@GetMapping("goodsIpgo")
	public String goodsIpgo(Model model) {
		String autoNum = autoNumService.execute("ipgo_", "ipgo_num", 6, "goods_ipgo");
		GoodsIpgoCommand goodsIpgoCommand = new GoodsIpgoCommand();
		goodsIpgoCommand.setIpgoNum(autoNum);
		model.addAttribute("goodsIpgoCommand", goodsIpgoCommand);
		return "thymeleaf/goodsIpgo/goodsIpgo";
	}
	@PostMapping("ipgoRegist")
	public String ipgoRegist(@Validated GoodsIpgoCommand goodsIpgoCommand
			, BindingResult result
			, HttpSession session) {
		if (result.hasErrors()) {
			return "thymeleaf/goodsIpgo/goodsIpgo";
		}
		goodsIpgoService.execute(goodsIpgoCommand, session);
		return "redirect:goodsIpgoList";
	}
	@Autowired
	GoodsListService goodsListService;
	@GetMapping("goodsItem")
	public String goodsItem(Model model) {
		goodsListService.execute(model);
		return "thymeleaf/goodsIpgo/goodsItem";
	}
	@GetMapping("goodsIpgoDetail")
	public String goodsIpgoDetail(String ipgoNum, String goodsNum, Model model) {
		GoodsIpgoNameDTO dto = ipgoDetailService.execute(ipgoNum, goodsNum, model);
		return "thymeleaf/goodsIpgo/goodsIpgoDetail";
	}
	
}
