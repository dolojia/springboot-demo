package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;

/**
 * 描述：使用自定义身份验证组件<br>
 * 作者：dolojia <br>
 * 修改日期：2017年6月9日上午10:46:18 <br>
 * E-mail: dolojia@gmail.com<br>
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	Logger logger = LogManager.getLogger(MyUserDetailsService.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) {

		if (StringUtils.isBlank(username)) {
			throw new UsernameNotFoundException("用户名为空");
		}

		User user = userService.findByUsername(username);
		if (user == null) {
			new UsernameNotFoundException("用户不存在");
		}

		logger.info("==========================username is " + user);
		Set<GrantedAuthority> authorities = new HashSet<>();
		// 数据库中role字段需加上ROLE_前缀，不要问我为什么，踩坑经验
		roleService.getRoles(user.getRoleId()).forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));
		logger.info("==========================authorities is " + authorities);
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, // 是否可用
				true, // 是否过期
				true, // 证书不过期为true
				true, // 账户未锁定为true
				authorities);
	}
}
