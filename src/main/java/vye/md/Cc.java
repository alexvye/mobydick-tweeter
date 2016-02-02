package vye.md;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cc {
	
	private static Logger LOG = LoggerFactory.getLogger(Cc.class);

	public static void main(String[] args) throws Exception {
		LOG.info("Starting post");
		String chunk = SourceReader.readSource("md.txt");
		Poster.post(chunk);
		LOG.info("Completed post");
	}
}
