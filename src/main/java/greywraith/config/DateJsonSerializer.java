package greywraith.config;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

public class DateJsonSerializer extends StdScalarSerializer<Date> {
	
	private static Logger LOG = LoggerFactory.getLogger(DateJsonSerializer.class);
	
	private static DateFormat dateFormatter; 
	
    protected DateJsonSerializer() {
    	super(Date.class);
    	
    	dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
	}

    @Override
    public void serialize(Date date, JsonGenerator json, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        String formattedDate = dateFormatter.format(date);
        json.writeString(formattedDate);
    }

}
