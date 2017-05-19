package com.yyl.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializeUtils {
	
	private static Logger logger = LoggerFactory.getLogger(SerializeUtils.class);
	
	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 */
	public static Object deserialize(byte[] bytes) {
		
		Object result = null;
		
		if (isEmpty(bytes)) {
			return null;
		}

		try {
			ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
			try {
				ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);
				try {
					result = objectInputStream.readObject();
				}
				catch (ClassNotFoundException ex) {
					throw new Exception("Failed to deserialize object type", ex);
				}
			}
			catch (Throwable ex) {
				throw new Exception("Failed to deserialize", ex);
			}
		} catch (Exception e) {
			logger.error("Failed to deserialize",e);
		}
		return result;
	}
	
	public static boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}

	/**
	 * 序列化
	 * @param object
	 * @return
	 * @throws IOException 
	 */
	public static byte[] serialize(Object object) throws IOException {

		byte[] result = null;
		if (object == null) {
			System.out.println("object是空的");
			return new byte[0];
		}
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				byteStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
		result = byteStream.toByteArray();
		if (result == null) {
			System.out.println("result序列化之后是空的");
		}
		return result;
	}
}
