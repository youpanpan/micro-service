package com.chengxuunion.page.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.chengxuunion.common.ResponseBean;
import com.chengxuunion.exception.BaseException;
import com.chengxuunion.vo.Page;

/**
 * 页面控制器（客户端交互）
 *
 * @author kutome
 * @date 2018年10月24日
 * @version V1.0
 */
@Controller
public class PageController {
	
	private static final String QUERY_ALL_PAGE = "http://localhost:8090/pagedesign-provider/pages/user/{userId}";
	private static final String QUERY_PAGE = "http://localhost:8090/pagedesign-provider/pages/{pageId}";
	private static final String ADD_PAGE = "http://localhost:8090/pagedesign-provider/pages";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@ResponseBody
	@RequestMapping(value="/pages/user/{userId}", method=RequestMethod.GET)
	public ResponseBean queryAllPage(@PathVariable("userId") String userId) throws BaseException {
		ResponseEntity<ResponseBean> responseEntity = restTemplate.getForEntity(QUERY_ALL_PAGE , ResponseBean.class, userId);
		ResponseBean responseBean = responseEntity.getBody();
		
		return responseBean;
	}
	
	@ResponseBody
	@RequestMapping(value="/pages/{pageId}", method=RequestMethod.GET)
	public ResponseBean queryPage(@PathVariable("pageId") String pageId) throws BaseException {
		ResponseEntity<ResponseBean> responseEntity = restTemplate.getForEntity(QUERY_PAGE, ResponseBean.class, pageId);
		ResponseBean responseBean = responseEntity.getBody();
		
		return responseBean;
	}
	
	@ResponseBody
	@RequestMapping(value="/pages", method=RequestMethod.POST)
	public ResponseBean addPage(Page page) throws BaseException {
		ResponseEntity<ResponseBean> responseEntity = restTemplate.postForEntity(ADD_PAGE, page, ResponseBean.class);
		
		return responseEntity.getBody();
	}
	
	@ResponseBody
	@RequestMapping(value="/pages/{pageId}", method=RequestMethod.DELETE)
	public ResponseBean deletePage(@PathVariable("pageId") String pageId) throws BaseException {
		ResponseEntity<ResponseBean> responseEntity = restTemplate.exchange(QUERY_PAGE, HttpMethod.DELETE, null, ResponseBean.class, pageId);
		
		return responseEntity.getBody();
	}
	

}
