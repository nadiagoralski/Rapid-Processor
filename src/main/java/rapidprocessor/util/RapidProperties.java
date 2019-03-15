package rapidprocessor.util;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Rapid Properties
 * Holds custom properties from the rapid.properties file
 *
 * rapid.properties allows users to specify custom file paths to
 * the location of the files for available tickets, transaction log,
 * and users.
 */
public class RapidProperties extends Properties {
    Logger logger = LogManager.getLogger(this.getClass().getName());
    private final String PROPERTIES_FILE_PATH = "/rapid.properties";

    public RapidProperties() {
        try {
            InputStream is = getClass().getResourceAsStream(PROPERTIES_FILE_PATH);

            // if no stream open, create a new one
            if (is == null) {
                is = new FileInputStream(PROPERTIES_FILE_PATH);
            }

            try {
                // try and load the properties
                load(is);
            } finally {
                // close the file
                is.close();
            }

        } catch (IOException e) {
            logger.error(Constants.ERROR_PREFIX, e);
        }
    }

}
