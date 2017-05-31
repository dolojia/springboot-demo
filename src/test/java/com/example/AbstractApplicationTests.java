package com.example;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AbstractApplicationTests.class)
public class AbstractApplicationTests {

	protected Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockMvc;

	protected Map<String, Object> doGet(String url) throws Exception {
		MockHttpServletRequestBuilder msrb = MockMvcRequestBuilders.get(url);
		print(url);
		return doService(msrb);
	}

	protected Map<String, Object> doPost(String url, String jsonData) throws Exception {
		MockHttpServletRequestBuilder msrb = MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
				.content(jsonData.getBytes()).characterEncoding("UTF-8");
		print(url, jsonData);
		return doService(msrb);
	}

	protected Map<String, Object> doPut(String url, String jsonData) throws Exception {
		MockHttpServletRequestBuilder msrb = MockMvcRequestBuilders.put(url).contentType(MediaType.APPLICATION_JSON)
				.content(jsonData.getBytes()).characterEncoding("UTF-8");
		print(url, jsonData);
		return doService(msrb);
	}

	protected Map<String, Object> doDelete(String url, String jsonData) throws Exception {
		MockHttpServletRequestBuilder msrb = MockMvcRequestBuilders.delete(url).contentType(MediaType.APPLICATION_JSON)
				.content(jsonData.getBytes()).characterEncoding("UTF-8");
		print(url, jsonData);
		return doService(msrb);
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> doService(MockHttpServletRequestBuilder msrb) throws Exception {
		ResultActions ra = mockMvc.perform(msrb);
		MvcResult mr = ra.andReturn();
		String result = mr.getResponse().getContentAsString();
		print(result);
		return JSON.parseObject(result, Map.class);
	}

	private void print(String... str) {
		System.out.println();
		for (String string : str) {
			System.out.println("=========> " + string);
		}
		System.out.println();
	}

}
