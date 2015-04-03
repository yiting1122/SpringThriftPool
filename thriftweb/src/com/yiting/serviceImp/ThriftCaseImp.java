package com.yiting.serviceImp;

import com.yiting.service.ThriftCase;
import com.yiting.service.UserRequest;
import com.yiting.service.UserResponse;
import org.apache.thrift.TException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/2.
 */
public class ThriftCaseImp implements ThriftCase.Iface {
	@Override
	public UserResponse integralService(UserRequest request) throws TException {
		try{
			UserResponse userResponse=new UserResponse();
			if("12345".equals(request.getId())){
				userResponse.setCode("0");
				Map<String,String> params=new HashMap<String,String>();
				params.put("integer","10");
				userResponse.setParams(params);

			}
			System.out.println("接收到的参数是：ID="+request.id);
			return userResponse;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
