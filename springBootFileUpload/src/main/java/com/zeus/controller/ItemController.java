package com.zeus.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.zeus.domain.Item;
import com.zeus.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@MapperScan(basePackages = "com.zeus.mapper")
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	//application.properties 에서 upload.path에 저장된 값을 주입한다.
	@Value("${upload.path}")
	private String uploadPath;
	
	@GetMapping("/createForm")
	public String itemCreateForm(Model model) {
		log.info("/createForm");
		return "item/createForm";
	}

	@PostMapping("/create")
	public String itemCreate(Item item, Model model) throws IOException, Exception {
		log.info("/create item" + item.toString());
		// 1. 파일업로드한것을 가져올것
		MultipartFile file = item.getPicture();
		// 2. 파일정보를 로그파일에 기록한다.
		log.info("originalName: " + file.getOriginalFilename());
		log.info("size: " + file.getSize());
		log.info("contentType: " + file.getContentType());
		// 3. 파일을 외장하드에 저장할것
		String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
		//4. 저장된 새로생성된 파일명을 아이템도메인에 저장한다.
		item.setUrl(createdFileName);
		//5. 데이터 베이스에 상품화면 정보를 저장.
		int count = itemService.create(item);
		if (count > 0) {
			model.addAttribute("message","%s 상품등록이 성공되었습니다.".formatted(file.getOriginalFilename()));
			return "item/success";
		}
		model.addAttribute("message","%s 상품등록이 실패되었습니다.".formatted(file.getOriginalFilename()));
		return "item/failed";
		
	}

	private String uploadFile(String originalName, byte[] fileData) throws 
	Exception { 
		//절대 중복되지않을 문자열을 생성해냄
		UUID uid = UUID.randomUUID();
		//new file("D:/upload",~~~~~.도훈.jpg")
		//D:/upload/~~~~~~~~~~~~~~~~~~~_도훈.jpg 내용없는 파일명만 생성)
		String createdFileName = uid.toString() + "_" + originalName; 
		File target = new File(uploadPath, createdFileName); 
		//내용이 있는 진짜 파일내용 (파일내용이 있는 바이트배열) byte[] fileData
		//~~~~~~~~도훈.jpg 복사진행
		FileCopyUtils.copy(fileData, target); 
		
		return createdFileName; 
}


}