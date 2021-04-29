package com.saltlux.mydictionary.controller;


import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.saltlux.mydictionary.service.DictionaryService;
import com.saltlux.mydictionary.vo.DictionaryVo;
import com.saltlux.mydictionary.vo.PageVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class DictionaryControllerTest {

	@Autowired
	private DictionaryService dictionaryService;

	@Test
	public void search() {
		PageVo pagevo = new PageVo(1, 10);
		pagevo.setKeyword("프로이트");
		String responseBody = dictionaryService.getResponseBody(pagevo);
		Map<String, Object> searchItems = dictionaryService.convertJSONstringToMap(responseBody);
		assertThat((String)searchItems.get("errorMessage"), nullValue(String.class));
	}

	@Test
	public void convertJSONstringToMap() {
		PageVo pagevo = new PageVo(1, 10);
		pagevo.setKeyword("프로이트");
		String responseBody = dictionaryService.getResponseBody(pagevo);
		Map<String, Object> searchItems = dictionaryService.convertJSONstringToMap(responseBody);
		assertThat((String)searchItems.get("errorMessage"), nullValue(String.class));


		int total = Integer.parseInt(searchItems.get("total").toString());
		List items =  (List) searchItems.get("items");
		if(total > 0) {
			assertNotNull(items.get(0));
		}
	}

	@Test
	public void convertMapToDictionaryVo() {
		PageVo pagevo = new PageVo(1, 10);
		pagevo.setKeyword("프로이트");
		String responseBody = dictionaryService.getResponseBody(pagevo);
		Map<String, Object> searchItems = dictionaryService.convertJSONstringToMap(responseBody);
		assertThat((String)searchItems.get("errorMessage"), nullValue(String.class));
		
		List<Map<String, String>> items =  (List<Map<String, String>>) searchItems.get("items");
		List<DictionaryVo> list = dictionaryService.convertMapToDictionaryVo(searchItems);

		assertEquals(list.get(0).getTitle(), items.get(0).get("title"));
	}
}
