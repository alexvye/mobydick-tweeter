package greywraith.config;

import greywraith.utilities.SmartLocaleResolver;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"greywraith"})
public class MVCConfig extends WebMvcConfigurerAdapter {
	
	 @Override
	 public void configureMessageConverters(final List<HttpMessageConverter<?>> converters)
	 {   
		 converters.add(jsonConverter());
	 }
	 

	@Bean
	 public MappingJackson2HttpMessageConverter jsonConverter()
	 {
		 final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	     converter.setObjectMapper(new CustomJacksonObjectMapper());
	     return converter;
	 }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}

	@Bean
	public LocaleResolver localeResolver() {
		SmartLocaleResolver slr = new SmartLocaleResolver();
		return slr;
	}
}