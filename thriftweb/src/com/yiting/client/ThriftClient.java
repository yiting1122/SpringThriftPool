package com.yiting.client;

import com.yiting.connection.ConnectionManager;
import com.yiting.model.Person;
import com.yiting.service.ThriftCase;
import com.yiting.service.UserRequest;
import com.yiting.service.UserResponse;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2015/4/2.
 */

public class ThriftClient {

	private Person person;
	private ConnectionManager connectionManager;
	@Autowired
	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}


	@Autowired
	public void setPerson(Person person) {
		this.person = person;
	}

	public void start(){
		try{
			System.out.println("+++++"+person.getId());
			TProtocol protocol= new TBinaryProtocol(connectionManager.getSocket());
			ThriftCase.Client client=new ThriftCase.Client(protocol);
			UserRequest request=new UserRequest();
			request.setId("12345");
			UserResponse userResponse=client.integralService(request);
			if(userResponse.code!=null&&!userResponse.code.equals("")){
				System.out.println(userResponse.params.get("integer"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
