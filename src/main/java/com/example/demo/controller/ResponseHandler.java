package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.utils.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseHandler {
	
public static final String MESSAGE ="message";
	
	public static final String TIME_STAMP ="timestamp";
	
	public static final String STATUS ="status";
	
	public static final String ERRORS ="errors";
	
	public static final String DATA ="data";
	
	public static final String IS_SUCCESS ="isSuccess";
	
	public static final String RETURN_CODE ="returnCode";
	
	public static final String INFO ="info";
	
	public static final String WARNINGS ="warnings";
		
	
	public static String unAuthorizedMessage() throws JsonProcessingException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, String> warnings = new HashMap<String, String>();
		HashMap<String, String> errors = new HashMap<String, String>();
		//errors.put(ErrorCode.ERR_UNAUTHORIZED.toString(), ErrorCode.ERR_UNAUTHORIZED.getMessage());
		map.put(TIME_STAMP, new Date());
		map.put(IS_SUCCESS, false);
		map.put(MESSAGE,ResponseMessage.MESSAGE_ON_UNAUTHORIZED);
		map.put(RETURN_CODE, prepareReturnCode(ResponseMessage.MESSAGE_ON_UNAUTHORIZED, warnings, errors));
		map.put(STATUS, HttpStatus.UNAUTHORIZED);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(map);
	}
	
	
	public static ResponseEntity<?> doSuccessResponse(String message) {
		return doSuccessResponse(message,null);
	}
  
	public static ResponseEntity<?> doSuccessResponse(Object object) {
		return doSuccessResponse(ResponseMessage.MESSAGE_REQUEST_SUCCESSFUL,object);
	}
	
	public static ResponseEntity<?> doSuccessResponse(String message, Object object) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(TIME_STAMP, new Date());
		map.put(IS_SUCCESS, true);
		map.put(MESSAGE, message);
		map.put(STATUS, HttpStatus.OK);
		if(object!=null)
		map.put(DATA, object);
		return ResponseEntity.ok(map);

	}
	
	public static ResponseEntity<?> doErrorResponse(HttpStatus status,String error) {
		return doErrorResponse(status, ResponseMessage.MESSAGE_SOMETHING_WENT_WRONG, Collections.singletonList(error));
	}
	
	public static ResponseEntity<?> doErrorResponse(List<String> errors) {
		return doErrorResponse(HttpStatus.BAD_REQUEST,ResponseMessage.MESSAGE_SOMETHING_WENT_WRONG, errors);
	}
	public static ResponseEntity<?> doErrorResponse(String error) {
		return doErrorResponse(HttpStatus.BAD_REQUEST,ResponseMessage.MESSAGE_SOMETHING_WENT_WRONG, Collections.singletonList(error));
	}
	public static ResponseEntity<?> doErrorResponse(HttpStatus status,String message,List<String> errors) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(TIME_STAMP, new Date());
		map.put(IS_SUCCESS, false);
		map.put(MESSAGE, message);
		if(errors!=null && errors.size()>0)
		map.put(ERRORS, errors);
		map.put(STATUS,status);
		return new ResponseEntity<Object>(map,status);

	}
	

	public static ResponseEntity<?> sendResponse(HttpStatus status, Map<String, Object> errors, Object... object) {
		Map<String, Object> response = new HashMap<String, Object>();
		if ((status.equals(HttpStatus.OK) || status.equals(HttpStatus.ACCEPTED)) && (errors == null)) {
			if (object != null)
		    response.put(DATA, object);
			response.put(IS_SUCCESS, true);
		} else {
			response.put(ERRORS, errors);
			response.put(IS_SUCCESS, false);
		}
		response.put(TIME_STAMP, new Date());
		response.put(STATUS, status);
		return new ResponseEntity<Object>(response, status);
	}
	
	
	private static HashMap<String,Object> getMap(){
	    return new HashMap<String,Object>();
	}
	
	private static String messageFormatter(String field, String message){
	   return String.format(message, field);
	}
	
	/**
	 * To prepare returnCode hashmap with info,warnings and errors.
	 * 
	 * @param info
	 * @param warnings
	 * @param errors
	 * @return
	 */
	public static HashMap<String, Object> prepareReturnCode(Object info, HashMap<String, String> warnings,
			HashMap<String, String> errors) {

		HashMap<String, Object> returnCode = new HashMap<String, Object>();

		returnCode.put("info", prepareListOfMapByInfo(info));

		returnCode.put("warnings", prepareListOfHashMaps(warnings));

		returnCode.put("errors", prepareListOfHashMaps(errors));

		return returnCode;

	}
	
	/**
	 * To prapare list of hashmaps with a given hashmap. get key-value from
	 * input hashmap and make individual hashmap for each key-value. then make
	 * list of those all new hashmaps.
	 * 
	 * @param map
	 * @return
	 */
	private static List<HashMap<String, Object>> prepareListOfHashMaps(HashMap<String, String> map) {

		ArrayList<HashMap<String, Object>> listOfMaps = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> newMap;
		for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
			newMap = new HashMap<String, Object>();
			newMap.put(stringStringEntry.getKey(), stringStringEntry.getValue());
			listOfMaps.add(newMap);
		}
		return listOfMaps;
	}
	
	/**
	 * To prepare list Of Hashmap .having single hashmap having info.
	 * 
	 * @param info
	 * @return
	 */
	private static List<HashMap<String, Object>> prepareListOfMapByInfo(Object info) {

		ArrayList<HashMap<String, Object>> listOfMaps = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("INFO_MESSAGE", info);
		listOfMaps.add(newMap);
		return listOfMaps;
	}

	public static ResponseEntity<?> successResponse(String info ,HashMap<String,String> warnings ,HashMap<String,String> errors, Object dataObject) {
		return prepareSuccessResponse(ResponseMessage.MESSAGE_REQUEST_SUCCESSFUL,dataObject,prepareReturnCode( info ,warnings ,errors ));
	}
	
	public static ResponseEntity<?> prepareSuccessResponse(String message, Object object ,HashMap<String,Object> returnCode ) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(TIME_STAMP, new Date());
		map.put(IS_SUCCESS, true);
		map.put(MESSAGE, message);
		map.put(STATUS, HttpStatus.OK);
		if(object!=null)
		map.put(DATA, object);
		map.put(RETURN_CODE,returnCode);
		return ResponseEntity.ok(map);

	}
	
   public static ResponseEntity<?> successResponse(String info ,HashMap<String,String> warnings ,HashMap<String,String> errors, Object dataObject, String erpDocId) {
      return prepareSuccessResponse(ResponseMessage.MESSAGE_REQUEST_SUCCESSFUL,dataObject,prepareReturnCode( info ,warnings ,errors), erpDocId);
   }
   
   public static ResponseEntity<?> prepareSuccessResponse(String message, Object object ,HashMap<String,Object> returnCode, String erpDocId ) {
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put(TIME_STAMP, new Date());
      map.put(IS_SUCCESS, true);
      map.put(MESSAGE, message);
      map.put(STATUS, HttpStatus.OK);
      if (erpDocId!=null)
         map.put("erpDocId",erpDocId);
      if(object!=null)
      map.put(DATA, object);
      map.put(RETURN_CODE,returnCode);
      return ResponseEntity.ok(map);

   }
   
	public static ResponseEntity<?> errorResponse(HttpStatus status, Object info ,HashMap<String,String> warnings ,HashMap<String,String> errors) {
		return prepareErrorResponse(status,ResponseMessage.MESSAGE_SOMETHING_WENT_WRONG, prepareReturnCode( info ,warnings ,errors ));
	}
	
	public static ResponseEntity<?> errorResponse(Object info ,HashMap<String,String> warnings ,HashMap<String,String> errors) {
		return prepareErrorResponse(HttpStatus.BAD_REQUEST,ResponseMessage.MESSAGE_SOMETHING_WENT_WRONG, prepareReturnCode( info ,warnings ,errors ));
	}
	
	public static ResponseEntity<?> prepareErrorResponse(HttpStatus status,String message,HashMap<String,Object> returnCode) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(TIME_STAMP, new Date());
		map.put(IS_SUCCESS, false);
		map.put(MESSAGE, message);
		map.put(RETURN_CODE, returnCode);
		map.put(STATUS,status);
		return new ResponseEntity<Object>(map,status);

	}
	
   public static ResponseEntity<?> errorResponse(HttpStatus status, Object info ,HashMap<String,String> warnings ,HashMap<String,String> errors, String erpDocId) {
      return prepareErrorResponse(status,ResponseMessage.MESSAGE_SOMETHING_WENT_WRONG, prepareReturnCode( info ,warnings ,errors ), erpDocId);
   }
   public static ResponseEntity<?> errorResponse(Object info ,HashMap<String,String> warnings ,HashMap<String,String> errors, String erpDocId) {
      return prepareErrorResponse(HttpStatus.BAD_REQUEST,ResponseMessage.MESSAGE_SOMETHING_WENT_WRONG, prepareReturnCode( info ,warnings ,errors ), erpDocId);
   }
   
   public static ResponseEntity<?> prepareErrorResponse(HttpStatus status,String message,HashMap<String,Object> returnCode, String erpDocId) {
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put(TIME_STAMP, new Date());
      map.put(IS_SUCCESS, false);
      map.put(MESSAGE, message);
      if (erpDocId!=null)
         map.put("erpDocId",erpDocId);
      map.put(RETURN_CODE, returnCode);
      map.put(STATUS,status);
      return new ResponseEntity<Object>(map,status);

   }
	
	

}
