package com.xzguo.netty.codec;

import java.io.IOException;

import org.jboss.marshalling.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
 * 下述为创建marshaller和unmarshaller的经典写法
 * 返回的值都是类的实例。即接口中的方法都已经实现
 */
public class MarshallingCodecFactory {
	Logger logger = LoggerFactory.getLogger(MarshallingCodecFactory.class);
	
	public  static Marshaller buildMarshalling() throws Exception {
		MarshallerFactory marshallerFactory = 
				Marshalling.getProvidedMarshallerFactory("serial");
		MarshallingConfiguration marshallingConfiguration = 
				new MarshallingConfiguration();
		marshallingConfiguration.setVersion(5);
		Marshaller marshaller = 
				marshallerFactory.createMarshaller(marshallingConfiguration);
		return marshaller;
	}
	
	public static Unmarshaller buildUmMarshalling() throws Exception {
		MarshallerFactory marshallerFactory = 
				Marshalling.getProvidedMarshallerFactory("serial");
		MarshallingConfiguration marshallingConfiguration = 
				new MarshallingConfiguration();
		marshallingConfiguration.setVersion(5);
		Unmarshaller  umMarshaller = 
				marshallerFactory.createUnmarshaller(marshallingConfiguration);
		return umMarshaller;
	}

}
