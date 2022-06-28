package tw.theone.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.theone.model.Resume;
import tw.theone.model.ResumeService;
@Controller

public class ResumeController {
	@Autowired
	private ResumeService resumeService;

	@GetMapping(path = "/resumes")
	public String showData(Model model){
		List<Resume> resumes = resumeService.getAllResumes();
		model.addAttribute("resumes", resumes);
		return "ResumeDashBoard";
	}
	
	//寫了這個才從履歷主頁跳轉進來新增履歷頁面
	@GetMapping(path = "/resume")
	public String toAdd() {
		return "ResumeCreate";
	}
	
	//因為action那邊post方法帶資料進來這邊
	@PostMapping(path = "/resume")
	public String processCreate(Resume resume){
		resumeService.save(resume);
		return "redirect:resumes";  
		//要求重導頁面，前端重導url又符合了@Getmapping(path = "/resumes")的狀況，所以又可以showData
	}
	
	
	
	
	@GetMapping(path = "/resume/{id}")
	public String processUpdate(@PathVariable("id") Integer id, Model model){
		Resume resume = resumeService.getResume(id);
		model.addAttribute("resume", resume);
		return "UpdateResume";
	}
	
	@PostMapping(path = "/resumeupdate")
	public String processUpdate(Resume resume){
		resumeService.updateResume(resume);
		return "redirect:resumes";
	}
	
	
	
	@DeleteMapping(path = "/resume/{id}")
	@ResponseBody
	public String processDelete(@PathVariable("id")Integer id, Model model){
		resumeService.deleteResume(id);
		return "ok";
	}

}
