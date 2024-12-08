package miniHotelProject.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miniHotelProject.domain.DeliveryDTO;
import miniHotelProject.mapper.BookMapper;

@Service
public class BookInsertService {
	@Autowired
	BookMapper bookMapper;
	public void execute(String purchaseNum, String deliveryNum) {
		DeliveryDTO dto= new DeliveryDTO();
		dto.setDeliveryNum(deliveryNum);
		dto.setPurchaseNum(purchaseNum);
		bookMapper.bookInsert(dto);	
	}
}