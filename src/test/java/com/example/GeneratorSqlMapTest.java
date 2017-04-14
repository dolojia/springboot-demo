package com.example;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**描述：自动生成Mapper配置文件的单元测试类<br>
 * 作者：100196 <br>
 * 修改日期：2017年4月12日下午3:20:01 <br>
 * E-mail:  dengjiaxing@dafycredit.com<br> 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplicationTests.class)
public class GeneratorSqlMapTest {

	@Before
	public void init() {
		System.out.println("开始生成配置文件！！");
	}

	@Test
	@Rollback(false)
	public void testDemo() {

		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		String filePath = DemoApplicationTests.class.getClassLoader().getResource("mybatis/mybatis-generator.xml").getPath();
		System.out.println(filePath);
		File configFile = new File(filePath);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = null;
		try {
			config = cp.parseConfiguration(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLParserException e) {
			e.printStackTrace();
		}
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = null;
		try {
			myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		try {
			myBatisGenerator.generate(null);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@After
	public void destory() {
	}

}
