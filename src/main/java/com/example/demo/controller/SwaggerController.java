package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.JSONResult;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 描述：Swagger测试<br>
 * 作者：dolojia <br>
 * 修改日期：2017年6月11日下午2:01:01 <br>
 * E-mail: dolojia@gmail.com<br>
 */
@Controller
@RequestMapping("/swagger")
public class SwaggerController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "获取用户列表", notes = "")
	@RequestMapping(value = { "/gerUsers" }, method = RequestMethod.GET)
	@ResponseBody
	public String getUserList() {
		List<User> users = userService.getUsers();
		return JSONResult.fillResultString(JSONResult.STATUS_SUCCESS, "/swagger/gerUsers", 200, users);
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@ResponseBody
	public String postUser(@RequestBody User user) {
		userService.insertUser(user);
		return "success";
	}

	// paramType = "query" http://url?id=10
	// paramType = "path" http://url/10
	@ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User") })
	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public String putUser(@PathVariable Long id, @RequestBody User user) {
		user.setId(id);
		userService.updateUserById(user);
		return "success";
	}

	@ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@PathVariable Long id) {
		userService.delUserById(id);
		return "success";
	}

}
