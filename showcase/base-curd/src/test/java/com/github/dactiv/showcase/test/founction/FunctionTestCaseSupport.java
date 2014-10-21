package com.github.dactiv.showcase.test.founction;

import java.util.Properties;

import javax.sql.DataSource;

import org.eclipse.jetty.server.Server;
import com.github.dactiv.common.spring.SpringContextHolder;
import com.github.dactiv.common.unit.JettyFactory;
import com.github.dactiv.common.unit.selenium.Selenium2;
import com.github.dactiv.common.unit.selenium.WebDriverFactory;
import com.github.dactiv.common.utils.PropertiesUtils;
import com.github.dactiv.showcase.test.LaunchJetty;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

/**
 * 使用自动化selenium做功能测试的基类
 * 
 * @author maurice
 *
 */
public class FunctionTestCaseSupport {
	
	//selenium settings, options include firefox,ie,chrome,remote:localhost:4444:firefox
	public static final String SELENIUM_DRIVER="firefox";
	public static final String URL="http://localhost:8080/dactiv-base-curd";
	
	protected static DataSource dataSource;
	protected static Server jettyServer;
	protected static JdbcTemplate jdbcTemplate;
	protected static ResourceLoader resourceLoader = new DefaultResourceLoader();
	protected static String testProperties = "application.test.properties";
	protected static Selenium2 s;
	
	/**
	 * 构建测试环境
	 * @throws Exception
	 */
	@BeforeClass
	public static void install() throws Exception {
		
		/*
		 * 由于使用到了ChainDefinitionSectionMetaSource，在spring加载配置文件时
		 * ChainDefinitionSectionMetaSource的执行优先与:
		 * 
		 * <jdbc:initialize-database data-source="dataSource" ignore-failures="ALL">
		 * 	<jdbc:script location="classpath:data/h2/create-table.sql" />
		 * </jdbc:initialize-database>
		 * 
		 * 如果数据库没有初始化，执行accountManager.getResources()，会报找不到表异常。
		 * 所以，先使用一个临时的dataSouce初始化数据，完成后在使用测试环境的dataSouce。
		 */
		if (dataSource == null) {
			Properties properties = PropertiesUtils.loadProperties(testProperties);
			org.apache.tomcat.jdbc.pool.DataSource source = new org.apache.tomcat.jdbc.pool.DataSource();
			
			source.setUsername(properties.getProperty("jdbc.username"));
			source.setPassword(properties.getProperty("jdbc.password"));
			source.setUrl(properties.getProperty("jdbc.url"));
			source.setDriverClassName(properties.getProperty("jdbc.driver"));
			jdbcTemplate = new JdbcTemplate(source);
	
			executeScript("classpath:data/h2/create-table.sql","classpath:data/h2/insert-data.sql");
			source.close();
		}
		
		//如果jetty没启动，启动jetty
		if (jettyServer == null) {
			// 设定Spring的profile
			System.setProperty(LaunchJetty.ACTIVE_PROFILE, "test");
			
			jettyServer = JettyFactory.createServerInSource(LaunchJetty.PORT, LaunchJetty.CONTEXT);
			//JettyFactory.setTldJarNames(jettyServer, LaunchJetty.TLD_JAR_NAMES);
			System.out.println("[HINT] Don't forget to set -XX:MaxPermSize=128m");
			jettyServer.start();
		}
		
		//如果当前dataSource没初始化，初始化dataSource
		if (dataSource == null) {
			dataSource = SpringContextHolder.getBean(DataSource.class);
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		
		//如果selenium没初始化，初始化selenium
		if (s == null) {
			//System.setProperty ( "webdriver.firefox.bin" , "E:/Firefox/firefox.exe" );
			WebDriver driver = WebDriverFactory.createDriver(SELENIUM_DRIVER);
			s = new Selenium2(driver, URL);
			s.setStopAtShutdown();
		}
		
		//载入新的数据
		executeScript("classpath:data/h2/cleanup-data.sql","classpath:data/h2/insert-data.sql");
		
		//打开浏览器，并登录
		s.open("/");
		s.click(By.xpath("//button[@type='submit']"));
	}
	
	@AfterClass
	public static void uninstall() {
		s.check(By.xpath("//a[@class='thumbnail dropdown dropdown-toggle']"));
		s.click(By.xpath("//a[@href='/dactiv-base-curd/logout']"));
	}
	
	/**
	 * 批量执行sql文件
	 * 
	 * @param sqlResourcePaths sql文件路径
	 * 
	 * @throws DataAccessException
	 */
	public static void executeScript(String... sqlResourcePaths) throws DataAccessException {

		for (String sqlResourcePath : sqlResourcePaths) {
			JdbcTestUtils.executeSqlScript(jdbcTemplate, resourceLoader, sqlResourcePath, true);
		}
	}
}
