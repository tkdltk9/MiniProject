package miniHotelProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.command.PurchaseCommand;
import miniHotelProject.mapper.PurchaseMapper;
import miniHotelProject.service.purchase.GoodsBuyService;
import miniHotelProject.service.purchase.GoodsOrderService;
import miniHotelProject.service.purchase.IniPayReqService;
import miniHotelProject.service.purchase.OrderProcessListService;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	GoodsBuyService goodsBuyService;
	@Autowired
	GoodsOrderService goodsOrderService;
	@Autowired
	IniPayReqService iniPayReqService;
	@Autowired
	OrderProcessListService orderProcessListService;
	@RequestMapping("goodsBuy")
	public String goodsBuy(String goodsNum, Integer qty, HttpSession session,Model model) {
		goodsBuyService.execute(goodsNum, qty, session, model);
		return "thymeleaf/purchase/goodsOrder";
	}
	@PostMapping("goodsOrder")
	public String goodsOrder(PurchaseCommand purchaseCommand, HttpSession session,
			Model model) {
		String purchaseNum = goodsOrderService.execute(purchaseCommand, session, model);
		return "redirect:paymentOk?purchaseNum="+purchaseNum;
	}
	@GetMapping("paymentOk")
	public String paymentOk(String purchaseNum, Model model) {
		try {
			iniPayReqService.execute(purchaseNum, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "thymeleaf/purchase/payment";
	}

	@GetMapping("orderList")
	public String orderList(HttpSession session, Model model) {
		orderProcessListService.execute(session, model, null);
		return "thymeleaf/purchase/orderList";
	}
	@GetMapping("purchaseList")
	public String purchaseList(HttpSession session, Model model) {
		orderProcessListService.execute(session, model, null);
		return "thymeleaf/purchase/purchaseList";
	}
	@Autowired
	PurchaseMapper purchaseMapper;
	@GetMapping("paymentDel")
	public String paymentDel(String purchaseNum) {
		purchaseMapper.paymentDel(purchaseNum);
		return "redirect:orderList";
	}
	@GetMapping("purchaseOk")
	public String purchaseOk(String purchaseNum) {
		purchaseMapper.paymentStatusUpdate(purchaseNum);
		return "redirect:orderList";
	}
}
