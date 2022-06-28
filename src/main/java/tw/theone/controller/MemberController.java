
package tw.theone.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.Column;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tw.theone.model.Member;
import tw.theone.model.MemberService;

@Controller
public class MemberController{
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ServletContext context;

	@GetMapping(path = "/members")
	public String showData(Model model){
		List<Member> members = memberService.getAllMembers();
		model.addAttribute("members", members);
		return "MemberDashBoard";
	}
	
	// CREATE
	@GetMapping(path = "/member")
	public String toAdd() {
		return "MemberCreate";
	}
	
	@PostMapping(path = "/member")
	public String processCreate( Member member,MultipartFile imageFile) throws IOException {
		
		String imageFileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
		File savedFile = new File(uploadDirInit().getAbsolutePath(), imageFileName);
		
		imageFile.transferTo(savedFile); // 將上傳的照片資源寫進(轉移複製)savedFile
		member.setImage("membersImg" + File.separator + savedFile.getName());
		memberService.save(member);
		return "redirect:members";
	}
	
	
	//建立在tomcat的資料夾
	public File uploadDirInit() {
	File uploadDir = new File(context.getRealPath(File.separator + "membersImg"));
	if (!uploadDir.exists()) {
		uploadDir.mkdir();
		}
	return uploadDir;
	}
	

	// Update
	@GetMapping(path = "/member/{id}")
	public String processUpdate(@PathVariable("id") Integer id, Model model){
		Member member = memberService.getMember(id);
		model.addAttribute("member", member);
		model.addAttribute("serverContextPath", context.getContextPath());
		return "MemberUpdate";
	}
	
	@PostMapping(path = "/memberupdate")
	public String processUpdate( Member member, MultipartFile imageFile) throws IOException {
		
		String imageFileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
		File savedFile = new File(uploadDirInit().getAbsolutePath(), imageFileName);
		
		imageFile.transferTo(savedFile); // 將上傳的照片資源寫進(轉移複製)savedFile
		member.setImage("membersImg" + File.separator + savedFile.getName());
		memberService.updateMember(member);
		return "redirect:members";
	}
	
	

	// DELETE
	@DeleteMapping(path = "/member/{id}")
	@ResponseBody
	public String processDelete(@PathVariable("id")Integer id, Model model){

		memberService.deleteMember(id);
		return "ok";
		
	}

	
}