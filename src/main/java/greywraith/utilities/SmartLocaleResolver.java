package greywraith.utilities;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

public class SmartLocaleResolver extends AcceptHeaderLocaleResolver   {
	
	private static Logger LOG = LoggerFactory.getLogger(SmartLocaleResolver.class);

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String acceptLanguage = request.getHeader("Accept-Language");
        
        if (acceptLanguage == null || acceptLanguage.trim().isEmpty()) {
            return super.resolveLocale(request);
        } else {
        	return new Locale(acceptLanguage);
        }
    }

}
