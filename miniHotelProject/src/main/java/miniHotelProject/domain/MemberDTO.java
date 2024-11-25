package miniHotelProject.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Alias("memDTO")
@Data
public class MemberDTO {
	String memberNum;
	String memberId;
	String memberPw;
	String memberName;
	String memberPhone1;
	String memberPhone2;
	String memberAddr;
	String memberAddrDetail;
	String memberPost;
	String gender;
	String memberEmail;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memberBirth;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memberRegist;
	
	String memberEmailConf;
}
