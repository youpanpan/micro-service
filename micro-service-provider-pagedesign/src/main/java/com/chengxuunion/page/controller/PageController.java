package com.chengxuunion.page.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chengxuunion.common.HttpStatus;
import com.chengxuunion.common.ResponseBean;
import com.chengxuunion.exception.BaseException;
import com.chengxuunion.page.service.IPageService;
import com.chengxuunion.vo.Page;

/**
 * 页面REST服务
 *
 * @author kutome
 * @date 2018年10月24日
 * @version V1.0
 */
@RestController
public class PageController {
	
	@Autowired
	private IPageService pageService;

	@RequestMapping(value="/pages/user/{userId}", method=RequestMethod.GET)
	public ResponseBean queryAllPage(@PathVariable("userId") String userId) throws BaseException {
		List<Page> pages = pageService.queryAllPage(userId);
		
		return new ResponseBean(HttpStatus.OK, pages);
	}
	
	@RequestMapping(value="/pages/{pageId}", method=RequestMethod.GET)
	public ResponseBean queryPage(@PathVariable("pageId") String pageId) throws BaseException {
		Page page = pageService.queryPage(pageId);
		return new ResponseBean(HttpStatus.OK, page);
	}
	
	@RequestMapping(value="/pages", method=RequestMethod.POST)
	public ResponseBean addPage(Page page) throws BaseException {
		int result = pageService.savePage(page);
		
		return new ResponseBean(HttpStatus.OK, result);
	}
	
	@RequestMapping(value="/pages/{pageId}", method=RequestMethod.DELETE)
	public ResponseBean deletePage(@PathVariable("pageId") String pageId) throws BaseException {
		int result = pageService.deletePage(pageId);
		
		return new ResponseBean(HttpStatus.OK, result);
	}
	
}
