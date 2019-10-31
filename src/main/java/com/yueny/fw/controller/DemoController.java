package com.yueny.fw.controller;

import com.yueny.fw.manager.IUserLoginManager;
import com.yueny.rapid.lang.agent.UserAgentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年2月16日 下午8:23:11
 *
 */
@RestController
// 跨域处理，加下类上则对所有方法生效
@CrossOrigin(origins = "*", maxAge = 3600)
public class DemoController {
	@Autowired
	private IUserLoginManager userLoginManager;

	/**
	 *
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String bar(HttpServletRequest request, UserAgentResource currentUserAgentResource) {
		String token = request.getHeader("token");
		boolean rs = userLoginManager.login("hello");

		UserAgentResource userAgent = (UserAgentResource)request.getAttribute("currentUserAgentResource");

//		// allocate 4M space
//		byte[] b = new byte[4 * 1024 * 1024];
//		System.out.println("first allocate");


		return "welcome:" + rs;
	}

}