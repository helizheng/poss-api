package com.joenee.common.web.servlet;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.joenee.common.util.LoggerUtil;
import com.joenee.common.util.PageUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseServlet {

	/**
	 * 
	 * @Title: writeprint
	 * @Description: 分页专用json
	 * @param response
	 * @param pageUtil json返回对象
	 * @param page 传递参数
	 * @return: void
	 */
	@SuppressWarnings("rawtypes")
	public void writeprint(HttpServletResponse response,PageUtil pageUtil,Page page){
		pageUtil.setRows(page.getRecords());
		pageUtil.setTotal(page.getTotal());
//		pageUtil.setLimit(page.getLimit());
//		pageUtil.setOffset(page.getOffset());
//		pageUtil.setPageNumber(page.getCurrent());
		String json = JSON.toJSONString(pageUtil);
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			LoggerUtil.log.error(e.getMessage(),e);
		}
	}
}	
