package com.gdts.selecting.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Tutorial {
	private static final transient Logger log = LoggerFactory.getLogger(Tutorial.class);

	public static void main(String[] args) {
		log.info("My First Apache Shiro Application");
		// 1.
		// 这里的SecurityManager是org.apache.shiro.mgt.SecurityManager，而不是java.lang.SecurityManager
		// 加载配置文件
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

		// 2.解析配置文件，并且返回一些SecurityManger实例
		SecurityManager securityManager = factory.getInstance();

		// 3.设置SecurityManager到静态内存区，单例模式
		SecurityUtils.setSecurityManager(securityManager);

		// 安全操作
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println("当前用户"+currentUser);
		// 在应用的当前会话中设置属性
		Session session = currentUser.getSession();
		session.setAttribute("key", "value");

		// 当前我们的用户是匿名的用户，我们尝试进行登录，
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken("aihe", "aihe");

			// this is all you have to do to support 'remember me' (no config - built in!):
			token.setRememberMe(true);

			// 尝试进行登录用户，如果登录失败了，我们进行一些处理

			try {
				currentUser.login(token);

				// 当我们获登录用户之后
				log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

				// 查看用户是否有指定的角色
				if (currentUser.hasRole("client")) {
					log.info("Look is in your role");
				} else {
					log.info(".....");
				}

				// 查看用户是否有某个权限
				if (currentUser.isPermitted("look:desk")) {
					log.info("You can look.  Use it wisely.");
				} else {
					log.info("Sorry, you can't look.");
				}

				if (currentUser.isPermitted("winnebago:drive:eagle5")) {
					log.info("You are permitted to 'drive' the 'winnebago' with license plate (id) 'eagle5'.  "
							+ "Here are the keys - have fun!");
				} else {
					log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
				}

				// 登出

				currentUser.logout();

			} catch (UnknownAccountException uae) {
				// 账户不存在的操作
			} catch (IncorrectCredentialsException ice) {
				// 密码不正确
			} catch (LockedAccountException lae) {
				// 用户被锁定了
			} catch (AuthenticationException ae) {
				// 无法判断的情形
			}

		}

		System.exit(0);
	}
}
