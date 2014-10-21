package com.github.bean.showcase.web.account;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.bean.showcase.common.SystemVariableUtils;
import com.github.bean.showcase.common.annotation.OperatingAudit;
import com.github.bean.showcase.common.enumeration.entity.ResourceType;
import com.github.bean.showcase.entity.account.Resource;
import com.github.bean.showcase.service.account.AccountManager;

/**
 * 资源管理Controller
 * 
 * @author maurice
 *
 */
@Controller
@OperatingAudit("资源管理")
@RequestMapping(value="/account/resource")
public class ResourceController {

	@Autowired
	private AccountManager accountManager;
	
	@Autowired
	private AbstractShiroFilter shiroFilter;
	
	/**
	 * 获取资源列表,返回account/resource/view.html页面
	 * 
	 * @return List
	 */
	@RequestMapping("view")
	public List<Resource> view() {
		return accountManager.getMergeResources();
	}
	
	/**
	 * 
	 * 保存或更新资源,保存成功后重定向到:account/resource/view
	 * 
	 * @param entity 实体信息
	 * @param parentId 所对应的父类id
	 * @param redirectAttributes spring mvc 重定向属性
	 * 
	 * @return String
	 */
	@RequestMapping("save")
	@OperatingAudit(function="保存或更新资源")
	public String save(@ModelAttribute("entity") @Valid Resource entity,
					   String parentId,
					   RedirectAttributes redirectAttributes) {
		
		if (StringUtils.isEmpty(parentId)) {
			entity.setParent(null);
		} else {
			Resource parent = accountManager.getResource(parentId);
			entity.setParent(parent);
		}
		
		accountManager.saveResource(entity);
		redirectAttributes.addFlashAttribute("success", "保存成功");
		return "redirect:/account/resource/view";
	}
	
	/**
	 * 
	 * 读取资源信息,返回account/resource/read.html页面
	 * 
	 * @param id 主键id
	 * @param model Spring mvc的Model接口，主要是将model的属性返回到页面中
	 * @param entity 实体信息
	 * 
	 * @return {@link Resource}
	 */
	@RequestMapping("read")
	public void read(String id, Model model,@ModelAttribute("entity")Resource entity) {
		model.addAttribute("resourceType", SystemVariableUtils.getVariables(ResourceType.class));
		model.addAttribute("resourcesList", accountManager.getResources(id));
		if (StringUtils.isEmpty(id)) {
			entity.setSort(accountManager.getResourceCount() + 1);
		}
	}
	
	/**
	 * 通过主键id集合删除资源,删除成功后重定向到:account/resource/view
	 * 
	 * @param ids 主键id集合
	 * @param redirectAttributes spring mvc 重定向属性
	 * 
	 * @return String
	 */
	@RequestMapping("delete")
	@OperatingAudit(function="删除资源")
	public String delete(@RequestParam("ids")List<String> ids,RedirectAttributes redirectAttributes) {
		
		accountManager.deleteResources(ids);
		redirectAttributes.addFlashAttribute("success", "删除" + ids.size() + "条信息成功");
		
		return "redirect:/account/resource/view";
	}
	
	/**
	 * 绑定实体数据，如果存在id时获取后从数据库获取记录，进入到相对的C后在将数据库获取的记录填充到相应的参数中
	 * 
	 * @param id 主键ID
	 * 
	 */
	@ModelAttribute("entity")
	public Resource bindingModel(String id) {

		Resource resource = new Resource();
		
		if (StringUtils.isNotEmpty(id)) {
			resource = accountManager.getResource(id);
		}

		return resource;
	}
	
}
