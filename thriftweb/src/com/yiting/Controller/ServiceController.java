package com.yiting.Controller;

import com.yiting.client.ThriftClient;
import com.yiting.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/4/2.
 */
@Controller
public class ServiceController {

	private Person person;
	private ThriftClient client;

	@Autowired
	public void setClient(ThriftClient client) {
		this.client = client;
	}

	@Autowired
	public void setPerson(Person person) {
		this.person = person;
	}

	@RequestMapping("/getservice.do")
	public String getservice(HttpServletRequest request,HttpServletResponse response){
		client.start();
		return "index";
	}

	@RequestMapping("/getpeople.do")
	public String getPerson(HttpServletRequest request,HttpServletResponse response){
		System.out.print(person.getId()+person.getAge());
		return "index";
	}


}
