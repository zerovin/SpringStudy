package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
	<aop:aspectj-autoproxy/> <!-- aop를 어노테이션으로 제작 -->
	<context:component-scan base-package="com.sist.*"/>
	
	<mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
 */
@Configuration //application-*.xml 대체 xml대신 자바로만 설정
//<context:component-scan base-package="com.sist.*"/>
@ComponentScan(basePackages= {"com.sist.*"})
//<aop:aspectj-autoproxy/> 
@EnableAspectJAutoProxy
//<mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
@MapperScan(basePackages= {"com.sist.mapper"})
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
		//HandlerMapping을 메모리 할당 => Model 안에서 RequestMapping, GetMapping, PostMapping
	}
	
	/*
	 <bean id="viewResolver"
	 	class="org.springframework.web.servlet.view.InternalResourceViewResolver"
	 	p:prefix="/"
	 	p:suffix=".jsp"
	 /> 
	 */
	@Bean("viewResolver")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver wr=new InternalResourceViewResolver();
		wr.setPrefix("/");
		wr.setSuffix(".jsp");
		return wr;
	}
	
	/*
	<bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
		p:driverClassName="oracle.jdbc.driver.OracleDriver"
		p:url="jdbc:oracle:thin:@localhost:1521:XE"
		p:username="hr"
		p:password="happy"
	/>
	*/
	@Bean("ds")
	public DataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		return ds;
	}
	
	/*
	<bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
	/> 
	 */
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}
}
