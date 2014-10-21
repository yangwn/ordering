package com.github.bean.showcase.web.foundation.audit;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.bean.showcase.common.SystemVariableUtils;
import com.github.bean.showcase.common.enumeration.entity.OperatingState;
import com.github.bean.showcase.entity.foundation.audit.OperatingRecord;
import com.github.bean.showcase.service.foundation.SystemAuditManager;
import com.github.dactiv.common.spring.mail.JavaMailService;
import com.github.dactiv.orm.core.Page;
import com.github.dactiv.orm.core.PageRequest;
import com.github.dactiv.orm.core.PageRequest.Sort;
import com.github.dactiv.orm.core.PropertyFilter;
import com.github.dactiv.orm.core.PropertyFilters;

/**
 * 操作记录管理Controller
 * 
 * @author maurice
 *
 */
@Controller
@RequestMapping("/foundation/audit/operating-record")
public class OperatingRecordController {
	
	@Autowired
	private SystemAuditManager systemAuditManager;
	@Autowired
	private JavaMailService javaMailService;
	@Value("${mail.exception.receive}")
	private String mailReceive;
	
	/**
	 * 获取操作记录列表
	 * 
	 * @param pageRequest 分页实体信息
	 * @param request HttpServlet请求
	 * 
	 * @return {@link Page}
	 */
	@RequestMapping("view")
	public Page<OperatingRecord> view(PageRequest pageRequest,HttpServletRequest request) {
		
		List<PropertyFilter> filters = PropertyFilters.get(request,true);
		
		if (!pageRequest.isOrderBySetted()) {
			pageRequest.setOrderBy("id");
			pageRequest.setOrderDir(Sort.DESC);
		}
		
		request.setAttribute("operatingState", SystemVariableUtils.getVariables(OperatingState.class));
		
		return systemAuditManager.searchOperatingRecordPage(pageRequest, filters);
	}
	
	/**
	 * 
	 * 读取操作记录,返回foundation/audit/operating-record/read.html页面
	 * 
	 * @param id 操作记录主键id
	 * @param model Spring mvc的Model接口，主要是将model的属性返回到页面中
	 * 
	 * @return String
	 * 
	 */
	@RequestMapping("read")
	public String read(Model model, @RequestParam("id")String id) {
		model.addAttribute("entity",systemAuditManager.getOperatingRecord(id));
		return "/foundation/audit/operating-record/read";
		
	}
	
	/**
	 * 发送错误报告
	 * 
	 * @param id 操作记录主键id
	 * 
	 * @return String
	 * 
	 */
	@ResponseBody
	@RequestMapping("send-error")
	public String sendError(@RequestParam("id")String id) {

		OperatingRecord value = systemAuditManager.getOperatingRecord(id);
		Hibernate.initialize(value);
		Map<String, OperatingRecord> model = Collections.singletonMap("entity", value);
		
		try {
			javaMailService.sendByTemplate(mailReceive, "base-curd-project", 
						"异常错误报告", "operating-mail-template.ftl", null, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "发送成功";
	}

}
