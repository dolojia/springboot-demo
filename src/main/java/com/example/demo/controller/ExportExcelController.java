package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Application;
import com.example.demo.utils.ExcelCreateUtil;

@Controller
public class ExportExcelController {
	
	Logger logger = LogManager.getLogger(Application.class);
	
	/**
	 * 导出excel数据
	 * 前端使用<a href='http://ip:host/project/exportExcelDddjInfo'></a>
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "exportExcelDddjInfo", method = RequestMethod.GET)
	@ResponseBody
	public void exportExcelDddjInfo(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		String fileUrl = "";
		String filename = "gfsdsdfg";
		try {
			List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", "1");
			map.put("creditAmount", "2");
			map.put("askFree", "3");
			map.put("creditModel", "4");
			map.put("annuity", "5");
			map.put("endureAccount", "6");
			map.put("cahe", "7");
			map.put("idProduct", "8");
			map.put("newProductId", "9");
			map.put("isProductNo", "10");
			datalist.add(map);
			createExcle(datalist);
		} catch (Exception e) {
			logger.error("exportExcelDddjInfo exception创建excel异常:" + e);
		}

		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(),"ISO-8859-1"));
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		try (InputStream fis = new BufferedInputStream(new FileInputStream(fileUrl));
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());) {
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			toClient.write(buffer);
			toClient.flush();
		} catch (Exception e) {
			logger.error("exportExcelDddjInfo exception下载导出excel异常:" + e);
		}
	}
	
	private String createExcle(List<Map<String, Object>> datalist) {
		ExcelCreateUtil create = new ExcelCreateUtil();
		create.createSheet("统计日报表");
		List<String> headerList = new LinkedList<String>();
		List<Integer> headWidths = new ArrayList<Integer>();
		headerList = new LinkedList<String>();
		headerList.add("合同id ");
		headerList.add("贷款金额");
		headerList.add("咨询费");
		headerList.add("合同模式");
		headerList.add("原期款");
		headerList.add("新期款");
		headerList.add("差额");
		headerList.add("原产品id ");
		headerList.add("新产品id");
		headerList.add("产品ID是否一致");
		for (int i = 0; i < headerList.size(); i++) {
			headWidths.add(3000);
		}
		create.addHeader(headerList, headWidths);

		List<Object> rowList = null;
		for (int i = 0, n = datalist.size(); i < n; i++) {
			rowList = new ArrayList<Object>();
			HashMap<String, Object> map = (HashMap<String, Object>) datalist.get(i);
			rowList.add(String.valueOf(map.get("id")));
			rowList.add(String.valueOf(map.get("creditAmount")));
			rowList.add(String.valueOf(map.get("askFree")));
			rowList.add(String.valueOf(map.get("creditModel")));
			rowList.add(String.valueOf(map.get("annuity")));
			rowList.add(String.valueOf(map.get("endureAccount")));
			rowList.add(String.valueOf(map.get("cahe")));
			rowList.add(String.valueOf(map.get("idProduct")));
			rowList.add(String.valueOf(map.get("newProductId")));
			rowList.add(String.valueOf(map.get("isProductNo")));
			create.addRow(rowList);
		}

		String fileName = "C:/Users/100196/Desktop/" + "数据表.xls";
		try {
			create.exportExcel(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

}
