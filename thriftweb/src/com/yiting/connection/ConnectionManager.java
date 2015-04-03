package com.yiting.connection;

import org.apache.thrift.transport.TSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2015/4/2.
 */

public class ConnectionManager {
	ThreadLocal<TSocket> socketThreadSafe=new ThreadLocal<TSocket>();

	private ConnectionProvider connectionProvider;

	public ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}

	@Autowired
	public void setConnectionProvider(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}



	public TSocket getSocket(){
		TSocket socket=null;
		try{
			socket=connectionProvider.getConnection();
			socketThreadSafe.set(socket);
			return socketThreadSafe.get();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(socket!=null) {
					connectionProvider.returnCon(socket);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return socket;
	}



}
