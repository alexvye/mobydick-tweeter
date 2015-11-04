package greywraith.config;

import greywraith.services.BusinessService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration 
@ComponentScan(basePackages = "greywraith") 
@EnableTransactionManagement
@EnableWebMvc
@PropertySource({"classpath:application.properties"})

public class AppConfig {

    private static Logger LOG = LoggerFactory.getLogger(AppConfig.class);
    
    @Resource
    private Environment environment;
    
	@Autowired
	private ApplicationContext applicationContext;
	
    /*
    * Services
    */
    @Bean
    public BusinessService businessService() {
        return new BusinessService();
    }
}