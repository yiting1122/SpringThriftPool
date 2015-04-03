package com.yiting.connection;


import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


/**
 * Created by Administrator on 2015/4/2.
 */
public class ConnectionProviderImp implements ConnectionProvider, InitializingBean, DisposableBean {

	private static final Logger logger = LoggerFactory.getLogger(ConnectionProviderImp.class);
	private String serviceIP;
	private int servicePort;
	private int conTimeOut;
	private int maxActive = GenericObjectPool.DEFAULT_MAX_ACTIVE;
	private int maxIdle = GenericObjectPool.DEFAULT_MAX_IDLE;
	private int minIdle = GenericObjectPool.DEFAULT_MIN_IDLE;
	private long maxWait = GenericObjectPool.DEFAULT_MAX_WAIT;

	private boolean testOnBorrow = GenericObjectPool.DEFAULT_TEST_ON_BORROW;
	private boolean testOnReturn = GenericObjectPool.DEFAULT_TEST_ON_RETURN;
	private boolean testWhileIdle = GenericObjectPool.DEFAULT_TEST_WHILE_IDLE;

	private ObjectPool objectPool = null;


	@Override
	public TSocket getConnection() throws Exception {
		return (TSocket) objectPool.borrowObject();

	}

	@Override
	public void returnCon(TSocket socket) throws Exception {
		this.objectPool.returnObject(socket);
	}

	@Override
	public void destroy() throws Exception {
		objectPool.close();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		objectPool = new GenericObjectPool();
		((GenericObjectPool) objectPool).setMaxActive(maxActive);
		((GenericObjectPool) objectPool).setMaxIdle(maxIdle);
		((GenericObjectPool) objectPool).setMinIdle(minIdle);
		((GenericObjectPool) objectPool).setMaxWait(maxWait);
		((GenericObjectPool) objectPool).setTestOnBorrow(testOnBorrow);
		((GenericObjectPool) objectPool).setTestOnReturn(testOnReturn);
		((GenericObjectPool) objectPool).setTestWhileIdle(testWhileIdle);
		((GenericObjectPool)objectPool).setWhenExhaustedAction(GenericObjectPool.DEFAULT_WHEN_EXHAUSTED_ACTION);
		ThriftPoolableObjectFactory thriftPoolableObjectFactory=new ThriftPoolableObjectFactory(serviceIP,servicePort,conTimeOut);
		((GenericObjectPool) objectPool).setFactory(thriftPoolableObjectFactory);
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

	public int getConTimeOut() {
		return conTimeOut;
	}

	public void setConTimeOut(int conTimeOut) {
		this.conTimeOut = conTimeOut;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public ObjectPool getObjectPool() {
		return objectPool;
	}
}
