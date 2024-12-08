package miniHotelProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.mapper.BookMapper;
import miniHotelProject.service.book.BookInsertService;
import miniHotelProject.service.purchase.OrderProcessListService;

@Controller
@RequestMapping("book")
public class BookController {
	@Autowired
	OrderProcessListService orderProcessListService;
	@Autowired
	BookInsertService bookInsertService;
	
	@GetMapping("bookRegist")
	public String bookRegist(String purchaseNum
			,Model model, HttpSession session) {
		orderProcessListService.execute(session, model, purchaseNum);
		model.addAttribute("newLineChar", "\n");
		return "thymeleaf/book/bookRegist"; 
	}
	@PostMapping("bookRegist")
	public String bookRegist(String purchaseNum
			,@RequestParam(value="deliveryNum" , required = false , defaultValue = "")String deliveryNum) {
		bookInsertService.execute(purchaseNum, deliveryNum);
		return "redirect:bookRegist?purchaseNum="+purchaseNum;
	}
	@Autowired
	BookMapper bookMapper;
	@GetMapping("bookStatus")
	public String bookStatus(String purchaseNum) {
		bookMapper.bookStatusUpdate(purchaseNum);
		return "redirect:/purchase/purchaseList";
	}
	@PostMapping("bookDelete")
	public String bookDelete(String purchaseNum) {
		bookMapper.bookDelete(purchaseNum);
		return "redirect:bookRegist?purchaseNum="+purchaseNum;
	}
	
}