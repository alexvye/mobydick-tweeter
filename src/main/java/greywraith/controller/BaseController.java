package greywraith.controller;

import greywraith.domain.rest.Sample;
import greywraith.services.BusinessService;
import greywraith.utilities.RestErrorHandler;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/greywraith")
public class BaseController extends RestErrorHandler  {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	BusinessService businessService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/business", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Sample> getlocalBusinesss() {
		logger.info("Call to /business");
		
		
		return new ResponseEntity<Sample>(businessService.getLocalBusiness(1, 2), HttpStatus.OK);
	}
}
