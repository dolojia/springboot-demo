package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplicationTests.class)
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
        String filePath = DemoApplicationTests.class.getClassLoader().getResource("mybatis-generator.xml").getPath(); 
        System.out.println(filePath);
	}

}
