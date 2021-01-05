package generator;

import java.util.ArrayList;
import java.util.List;

public class ParseFields {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>(); 
		list.add(" 	F_CODE_FIND('0711', EEA_MMBR_CD, ' ', '1') EEA_MMBR_NM,");
		list.add(" 	F_CODE_FIND('0703', ICR_NTNA_CD, ' ', '1') EEA_NATI_NM,");
		list.add(" 	F_CODE_FIND('0712', EEA_SEND_CD, ' ', '1') EEA_SEND_NM,");
		
		for (int i = 0; i < list.size(); i++) {
			String temp = list.get(i);
			
			// 맨 뒤에서 가장 먼저 나오는 공백 찾기
			int spaceIndex = temp.lastIndexOf(" ");
			
			// 자르기
			temp = temp.substring(spaceIndex, temp.length());
			
			temp = temp.replace(",", ""); // 컴마 제거
			temp = temp.trim(); // 공백제거
			System.out.println(temp);
		}
		
	}
}
