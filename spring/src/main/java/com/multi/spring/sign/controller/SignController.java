package com.multi.spring.sign.controller;

import com.multi.spring.sign.model.dto.SignDTO;
import com.multi.spring.sign.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class SignController {

	@Autowired
	SignService signService;

	@GetMapping("/sign/main")
	public String signMain() {
		return "sign_main";
	}

	@PostMapping("/sign/insert")
	public String insertSign(SignDTO signDTO, @RequestParam("image") MultipartFile image, Model model) {
		String savedName = null;
		try {
			if (!image.isEmpty()) {
				String rootPath = "path/to/your/upload/directory";
				String fileName = image.getOriginalFilename();
				String extension = fileName.substring(fileName.lastIndexOf("."));
				savedName = UUID.randomUUID().toString().replace("-", "") + extension;
				File dest = new File(rootPath + "/" + savedName);
				image.transferTo(dest);
				signDTO.setImg(savedName);
			}
			signService.insertSign(signDTO);
			model.addAttribute("savedName", savedName);
			model.addAttribute("signDTO", signDTO);
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("message", "파일 업로드 실패!!");
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/sign/main";
	}

	@GetMapping("/sign/list")
	public String selectList(Model model) {
		try {
			List<SignDTO> list = signService.selectList();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sign_list";
	}

	@GetMapping("/sign/one")
	public String selectSign(@RequestParam String name, @RequestParam String birth, Model model) {
		try {
			SignDTO dto = signService.selectSign(name, birth);
			model.addAttribute("dto", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sign_detail";
	}

	@GetMapping("/sign/delete")
	public String deleteSign(@RequestParam String name, @RequestParam String birth) {
		try {
			signService.deleteSign(name, birth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/sign/main";
	}
}
