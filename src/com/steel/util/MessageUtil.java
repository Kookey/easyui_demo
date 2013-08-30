package com.steel.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageUtil {

	public static String getMessage(boolean success,String msg){
		String result = "";
		Message message = new Message();
		message.setSuccess(success);
		message.setMsg(msg);
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(message);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
