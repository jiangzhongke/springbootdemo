package com.bootdemo.testsuite;

/**
 * @ClassName:     TestSuite
 * @Description:   打包测试 
 * 
 * @author         tommy
 * @version        V0.1  
 * @Date           2016/5/18 16:07 
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.bootdemo.test.*;

@RunWith(Suite.class) 
@SuiteClasses({ServiceTest.class, ParameterTest.class}) 
public class TestSuite {

}
