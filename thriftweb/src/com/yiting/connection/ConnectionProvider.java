package com.yiting.connection;

import org.apache.thrift.transport.TSocket;

/**
 * Created by Administrator on 2015/4/2.
 */
public interface ConnectionProvider {
	/**
	 * 获取连接池中的一个连接
	 * @return
	 */
	public TSocket getConnection() throws Exception;

	/**
	 * 连接使用完后，返回连接
	 * @param socket
	 */
	public void returnCon(TSocket socket) throws Exception;
}
