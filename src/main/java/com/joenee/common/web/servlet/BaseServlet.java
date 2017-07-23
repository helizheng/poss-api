package com.joenee.common.web.servlet;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.joenee.common.common.result.JsonResult;
import com.joenee.common.enums.PossErrorEnum;
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
	protected void writeprint(HttpServletResponse response,PageUtil pageUtil,Page page){
		pageUtil.setRows(page.getRecords());
		pageUtil.setTotal(page.getTotal());
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

	/**
	 * 赋值page
	 */
	protected Page getPages(PageUtil pageUtil){
		Page page = new Page();
		page.setCurrent(pageUtil.getPageNumber());
		page.setSize(pageUtil.getLimit());
		return page;
	}


	/**
	 * 渲染失败数据
	 *
	 * @return result
	 */
	protected JsonResult renderError() {
		JsonResult result = new JsonResult();
		result.setSuccess(false);
		return result;
	}

	/**
	 * 渲染失败数据（带消息）
	 *
	 * @param msg 需要返回的消息
	 * @return result
	 */
	protected JsonResult renderError(String msg) {
		JsonResult result = renderError();
		result.setMsg(msg);
		return result;
	}

	/**
	 * 渲染失败数据（枚举消息）
	 * @param responseEnum
	 * @return
	 */
	protected JsonResult renderError(PossErrorEnum responseEnum){
		JsonResult result = renderError();
		result.setCode(responseEnum.getCode()+"");
		result.setMsg(responseEnum.getMessage());
		return result;
	}
	/**
	 * 渲染成功数据
	 *
	 * @return result
	 */
	protected JsonResult renderSuccess() {
		JsonResult result = new JsonResult();
		result.setSuccess(true);
		return result;
	}

	/**
	 * 渲染成功数据（带信息）
	 *
	 * @param msg 需要返回的信息
	 * @return result
	 */
	protected JsonResult renderSuccess(String msg) {
		JsonResult result = renderSuccess();
		result.setMsg(msg);
		return result;
	}

	/**
	 * 渲染成功数据（带数据）
	 *
	 * @param obj 需要返回的对象
	 * @return result
	 */
	protected JsonResult renderSuccess(Object obj) {
		JsonResult result = renderSuccess();
		result.setData(obj);
		return result;
	}
}	
