package com.yiting.Server;

import com.yiting.service.ThriftCase;
import com.yiting.serviceImp.ThriftCaseImp;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by Administrator on 2015/4/2.
 */
public class Main {
	public static final int SERVER_PORT=9010;
	private ThriftCaseImp thriftCaseImp;
	private TServerTransport transport;

	public Main(ThriftCaseImp thriftCaseImp) {
		this.thriftCaseImp = thriftCaseImp;
	}

	public TServerTransport getTransport() {
		return transport;
	}

	public void setTransport(TServerTransport transport) {
		this.transport = transport;
	}

	public void  start(){
		try {
			transport=new TServerSocket(SERVER_PORT);
			ThriftCase.Processor processor=new ThriftCase.Processor(thriftCaseImp);

			TBinaryProtocol.Factory factory=new TBinaryProtocol.Factory(true,true);

			TThreadPoolServer.Args args=new TThreadPoolServer.Args(transport);
			args.processor(processor);
			args.protocolFactory(factory);

			TServer server=new TThreadPoolServer(args);
			System.out.println("Listening on the port :"+SERVER_PORT);
			server.serve();

		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}


	public  static void main(String[] args){
		Main main=new Main(new ThriftCaseImp());
		main.start();
	}
}
