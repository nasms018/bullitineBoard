package www.dream.bbs.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:3000")
		.exposedHeaders("x-auth-token")
		.allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
		//.allowedHeaders("x-auth-token", "Access-Control-Allow-Origin")
		.allowCredentials(true);
		 
/*		
		 WebConfigProperties.Cors cors = webConfigProperties.getCors();
         registry.addMapping("/**")
           .allowedOrigins(cors.getAllowedOrigins())
           .allowedMethods(cors.getAllowedMethods())
           .maxAge(cors.getMaxAge())
           .allowedHeaders(cors.getAllowedHeaders())
           .exposedHeaders(cors.getExposedHeaders());
*/
	}

}
