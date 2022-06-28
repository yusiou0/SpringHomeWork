package tw.theone.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//相當於beans.config.xml的程式組態

@Configuration
@ComponentScan(basePackages = "tw.theone")
@EnableWebMvc
@EnableTransactionManagement
public class RootAppConfig {
	
	
	
	@Bean
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
		jndiBean.setJndiName("java:comp/env/connectSqlServerJdbc/SystemService");
		jndiBean.afterPropertiesSet();
		DataSource ds = (DataSource)jndiBean.getObject();
		return ds;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("tw.theone");
		factoryBean.setHibernateProperties(addtionalProperties());
		return factoryBean;
	}

	private Properties addtionalProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", org.hibernate.dialect.SQLServerDialect.class);
		props.put("hibernate.show_sql", Boolean.TRUE);
		props.put("hibernate.format_sql", Boolean.TRUE);
		props.put("hibernate.hbm2ddl.auto","update" );   //讓hibernate自建表格
		//props.put("hibernate.current_session_context_class", "thread");
		return props;
	}
	
	@Bean
	@Autowired  //因為方法內放了SessionFactory 要讓他自己去找
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txMgr = new HibernateTransactionManager();
		txMgr.setSessionFactory(sessionFactory);
		return txMgr;
	}
	
	
	

}
