package miniHotelProject.service.goods;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.command.GoodsCommand;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.domain.FileDTO;
import miniHotelProject.domain.GoodsDTO;
import miniHotelProject.mapper.EmployeeMapper;
import miniHotelProject.mapper.GoodsMapper;

@Service
public class GoodsUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsCommand goodsCommand, BindingResult result, HttpSession session, Model model) {
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsContents(goodsCommand.getGoodsContents());
		dto.setGoodsName(goodsCommand.getGoodsName());
		dto.setGoodsNum(goodsCommand.getGoodsNum());
		dto.setGoodsPrice(goodsCommand.getGoodsPrice());
 		dto.setHotelAddr(goodsCommand.getHotelAddr());
		dto.setHotelAddrDetail(goodsCommand.getHotelAddrDetail());
 		dto.setHotelPost(goodsCommand.getHotelPost());
 		dto.setHotelLocation(goodsCommand.getHotelLocation());
		// 수정한 직원의 정보를 알기 위해 로그인 정보를 이용해서 직원정보를 가지고왔습니다.
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		dto.setUpdateEmpNum(empNum);
		
		//파일 시스템에서 삭제
		//1. 디렉터리 정보
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = resource.getFile();
		System.out.println(fileDir);
		if(goodsCommand.getGoodsMainImage() != null) {
			// 2. 파일객체를 불러오기
			MultipartFile mf = goodsCommand.getGoodsMainImage();
			// 3. 파일이름 가져오기
			String originalFile = mf.getOriginalFilename();
			// 4. 확장자 분리하기
			String extension = originalFile.substring(originalFile.lastIndexOf("."));
			// 5. 새로운 파일명 만들기
			String storeName = UUID.randomUUID().toString().replace("-", "");
			// 6. 새로운 파일명과 확장자 붙이기
			String storeFileName = storeName + extension;
			// 7. 파일객체 만들기
			File file = new File(fileDir + "/" + storeFileName);
			// 8. 파일 저장
			try {
				mf.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 9. dto에 파일이름 저장
			dto.setGoodsMainImage(originalFile);
			dto.setGoodsMainStoreImage(storeFileName);
		}
		String originalTotal = "";
		String storeTotal = "";
		if(!goodsCommand.getGoodsDetailImage()[0].getOriginalFilename().isEmpty()) {
			// 1. 디렉터리 정보
			// 2. 파일객체를 불러오기
			for(MultipartFile mf : goodsCommand.getGoodsDetailImage()) {
				// 3. 파일이름 가져오기
				String originalFile = mf.getOriginalFilename();
				// 4. 확장자 분리하기
				String extension = originalFile.substring(originalFile.lastIndexOf("."));
				// 5. 새로운 파일명 만들기
				String storeName = UUID.randomUUID().toString().replace("-", "");
				// 6. 새로운 파일명과 확장자 붙이기
				String storeFileName = storeName + extension;
				// 7. 파일객체 만들기
				File file = new File(fileDir + "/" + storeFileName);
				// 8. 파일 저장
				try {
					mf.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				originalTotal += originalFile + "/";
				storeTotal += storeFileName + "/";
			}
		}
		/// session에 있는 값은 삭제, 새로 추가한 파일은 insert되게
		List<FileDTO> list = (List<FileDTO>)session.getAttribute("fileList");
		// 파일의 정보를 디비로부터 가져오기
		GoodsDTO goodsDTO = goodsMapper.goodsSelectOne(goodsCommand.getGoodsNum());
		// session에 있는 정보를 디비로부터 제거
		if(goodsDTO.getGoodsDetailImage() != null) {
			List<String> dbOrg = new ArrayList<>(Arrays.asList(goodsDTO.getGoodsDetailImage().split("[/`]")));
			List<String> dbStore = new ArrayList<>(Arrays.asList(goodsDTO.getGoodsDetailStoreImage().split("[/`]")));
			if(list != null) {
				for(FileDTO fdto : list) {
					for(String img : dbOrg) {
						if(fdto.getOrgFile().equals(img)) {
							dbOrg.remove(fdto.getOrgFile());
							dbStore.remove(fdto.getStoreFile());
							break;
						}
					}
				}
			}
			for(String img : dbOrg) originalTotal += img + "/";
			for(String img : dbStore) storeTotal += img + "/";
		}
		dto.setGoodsDetailStoreImage(storeTotal);
		dto.setGoodsDetailImage(originalTotal);
		
		int i = goodsMapper.goodsUpdate(dto);
		if(i > 0) {
			if(list != null) {
				for(FileDTO fd : list) {
					File file = new File(fileDir + "/" + fd.getStoreFile());
					if(file.exists()) file.delete();
				}
			}
		}
	}
}
