package miniHotelProject.service.goods;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.domain.FileDTO;

@Service
public class FileDelService {

	public int execute(String orgFile, String storeFrile, HttpSession session) {
		int i = 0;
		FileDTO dto = new FileDTO();
		dto.setOrgFile(orgFile);
		dto.setStoreFile(storeFrile);
		Boolean newFile = true;
		// session 세션 여부 확인
		List<FileDTO> list = (List<FileDTO>)session.getAttribute("fileList");
		if(list == null) {
			list = new ArrayList<FileDTO>();
		}
		// session에 있으면 삭제
		for( FileDTO fd : list ) {
			if(fd.getStoreFile().equals(storeFrile)) {
				list.remove(fd);
				newFile = false;
				break;
			}
		}
		if(newFile) {
			list.add(dto);
			i = 1;
		}
		session.setAttribute("fileList", list); // session이 null일 때를 위해
		return i;
	}
}
