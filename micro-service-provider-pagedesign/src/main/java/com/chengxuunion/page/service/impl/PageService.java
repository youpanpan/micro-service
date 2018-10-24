package com.chengxuunion.page.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chengxuunion.exception.BaseException;
import com.chengxuunion.page.dao.PageDao;
import com.chengxuunion.page.service.IPageService;
import com.chengxuunion.utils.IdGenerator;
import com.chengxuunion.vo.Page;

/**
 * 页面服务实现
 *
 * @author kutome
 * @date 2018年10月24日
 * @version V1.0
 */
@Service
public class PageService implements IPageService {
	
	@Autowired
	private PageDao pageDao;

	@Override
	public List<Page> queryAllPage(String userId) throws BaseException {
		return pageDao.queryAllPage(userId);
	}

	@Override
	public Page queryPage(String pageId) throws BaseException {
		return pageDao.queryPage(pageId);
	}

	@Override
	public int savePage(Page page) throws BaseException {
		IdGenerator idGenerator = new IdGenerator();
		String id = idGenerator.nextIdString();
		page.setId(id);
		
		return pageDao.addPage(page);
	}

	@Override
	public int deletePage(String pageId) throws BaseException {
		return pageDao.deletePage(pageId);
	}
	
}
