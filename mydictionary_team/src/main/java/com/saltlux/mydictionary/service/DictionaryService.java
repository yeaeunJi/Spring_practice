package com.saltlux.mydictionary.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saltlux.mydictionary.exception.DictionaryServiceException;
import com.saltlux.mydictionary.vo.DictionaryVo;
import com.saltlux.mydictionary.vo.PageVo;

@Service
public class DictionaryService {
	
	private String searchUrl = "https://openapi.naver.com/v1/search/encyc";
	private String clientId = "";
	private String clientSecret = "";
	
	
	public List<DictionaryVo> search(PageVo pagevo) {
		String response = getResponseBody(pagevo);
		Map<String, Object> responseMap = convertJSONstringToMap(response);
		List<DictionaryVo> list = convertMapToDictionaryVo(responseMap);
		return list;
	}
	
	public String getResponseBody(PageVo pagevo) {
		String apiURL = makeURL(pagevo);
		Map<String, String> requestHeaders = makeRequestHeader();
		String responseBody = get(apiURL,requestHeaders);
		return responseBody;
	}
	

	
	public List<DictionaryVo> markingBookmarkFlag(List<String> compareList, List<DictionaryVo> list){
		for(int i=0; i<list.size(); i++) {
			if (compareList.contains(list.get(i).getLink())){
				list.get(i).setBookmarkFlag(true);
			}else {
				continue;
			}
		}
		return list;
	}

	/* open api 연결 관련 메서드  */
	private String makeURL(PageVo pagevo) {
		String url = searchUrl;
		Map<String, Object> urlParam = new HashMap<>();
		String txtKeyword = "";
	
		try {
			txtKeyword = URLEncoder.encode(pagevo.getKeyword(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new DictionaryServiceException(e.toString());
		}
	
		urlParam.put("query", txtKeyword);
		urlParam.put("display", pagevo.getShowNum());
		urlParam.put("start", pagevo.getStartRow());
		Iterator<String> keyIter = urlParam.keySet().iterator();

		for(int i =0; keyIter.hasNext(); i++) {
			if(i==0) {
				url += "?";
			}			
			String key = keyIter.next().toString();
			url += key+"="+urlParam.get(key);
			if(keyIter.hasNext()) {
				url += "&";
			}
		}

		return url;
	}
	
	private Map<String, String> makeRequestHeader() {
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		return requestHeaders;
	}
	
	private String get(String apiUrl, Map<String, String> requestHeaders){
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}


			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private HttpURLConnection connect(String apiUrl){
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	/* 응답 결과 처리 */

	private static String readBody(InputStream body){
		InputStreamReader streamReader = new InputStreamReader(body);


		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();


			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}


			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}
	
	
    public Map<String,Object> convertJSONstringToMap(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        
        try {
			map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
		} catch (JsonMappingException e) {
			throw new DictionaryServiceException();
		} catch (JsonProcessingException e) {
			throw new DictionaryServiceException();
		}
        return map;
    }
    
    public List<DictionaryVo> convertMapToDictionaryVo(Map<String, Object>  searchItems){
    	List<DictionaryVo> list = new ArrayList<>();

		int total = Integer.parseInt(searchItems.get("total").toString());
		int start =  Integer.parseInt(searchItems.get("start").toString());
		int display =  Integer.parseInt(searchItems.get("display").toString());		

		List<Map<String, String>> items =  (List<Map<String, String>>) searchItems.get("items");
		

		for(int i=0; i < items.size(); i++) {
			Map<String, String> item = (Map<String, String>) items.get(i);
			DictionaryVo vo = new DictionaryVo(total, start, display);
			vo.setDescription(item.get("description"));
			vo.setLink(item.get("link"));
			vo.setThumbnail(item.get("thumbnail"));
			vo.setTitle(item.get("title"));
			list.add(vo);
		}
		
		return list;
    }

	
}
