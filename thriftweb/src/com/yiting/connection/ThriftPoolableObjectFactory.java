package com.yiting.connection;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2015/4/2.
 */
public class ThriftPoolableObjectFactory implements PoolableObjectFactory{
	private static final Logger logger = LoggerFactory.getLogger(ThriftPoolableObjectFactory.class);

	private String serviceIP;
	private int servicePort;
	private int timeOut;

	public ThriftPoolableObjectFactory(String serviceIP, int servicePort, int timeOut) {
		this.serviceIP = serviceIP;
		this.servicePort = servicePort;
		this.timeOut = timeOut;
	}

	/**
	 * 创造对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object makeObject() throws Exception {
		try{
			TTransport transport=new TSocket(this.serviceIP,this.servicePort,this.timeOut);
			transport.open();
			return transport;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 销毁对象
	 * @param o
	 * @throws Exception
	 */
	@Override
	public void destroyObject(Object o) throws Exception {
		if(o instanceof TSocket){
			TSocket socket=(TSocket)o;
			if(socket.isOpen()){
				socket.close();
			}
		}
	}

	/**
	 * 检查对象是否可以由pool安全返回
	 * @param o
	 * @return
	 */
	@Override
	public boolean validateObject(Object o) {
		try{
			if(o instanceof TSocket){
				TSocket thriftSocket=(TSocket)o;
				if(thriftSocket.isOpen()){
					return true;
				}else{
					return false;
				}
			}else
			{
				return false;
			}
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 激活对象
	 * @param o
	 * @throws Exception
	 */
	@Override
	public void activateObject(Object o) throws Exception {

	}

	@Override
	public void passivateObject(Object o) throws Exception {

	}

	public String getServiceIP() {
		return serviceIP;
	}

	public void setServiceIP(String serviceIP) {
		this.serviceIP = serviceIP;
	}

	public int getServicePort() {
		return servicePort;
	}

	public void setServicePort(int servicePort) {
		this.servicePort = servicePort;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
}
