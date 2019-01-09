package com.p.project;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.p.project.model.ReplyVO;
import com.p.project.service.ReplyService;

//������ ���� 3�̹Ƿ� RestControlelr X, ResponseBody ������̼� ���
@Controller
@RequestMapping("/replies")
public class ReplyController {

	@Inject
	private ReplyService service;
	
	//ResponseBody annotation : return�Ǵ� ���� view�� �ƴ� HTTP ResponseBody�� ���� �������� �ȴ�
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
		ResponseEntity<String> entity=null;
		
		try {
			service.addReply(vo);
			entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//register()--------------------------
	
	@ResponseBody
	@RequestMapping(value="/all/{bno}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno){
		ResponseEntity<List<ReplyVO>> entity=null;
		
		try {
			entity=new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//list()-------------------------------
}//ReplyController
