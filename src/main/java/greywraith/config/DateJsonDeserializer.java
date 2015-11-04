package greywraith.config;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class DateJsonDeserializer extends StdScalarDeserializer<Date> {
	
	private static Logger LOG = LoggerFactory.getLogger(DateJsonDeserializer.class);
	
	private static DateFormat dateFormatter;
	
    protected DateJsonDeserializer() {
    	super(Date.class);
    	
    	dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    	dateFormatter.setLenient(false);
	}

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
        String date = jp.getText();
        try{
            return dateFormatter.parse(date);
        }catch(ParseException e){
        	LOG.error(e.toString());
        	return null;
        }

	} 

}
