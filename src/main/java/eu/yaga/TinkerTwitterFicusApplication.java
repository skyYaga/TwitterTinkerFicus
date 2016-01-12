package eu.yaga;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TinkerTwitterFicusApplication {

    private static final String CONFIG_FILE = "config.properties";

    private static Logger logger = Logger.getLogger(TinkerTwitterFicusApplication.class.getName());
    private static Properties prop = new Properties();

    private static String host = "localhost";
    private static int port = 4223;
    private static String uid = "uSo";

    public static void main(String[] args) {
        loadProperties();
        new TinkerTwitterFicusApplication().start();
    }

    private static void loadProperties() {
        logger.info("loading config.properties");
        InputStream input = null;

        try {
            input = TinkerTwitterFicusApplication.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
            prop.load(input);

            host = prop.getProperty("host");
            port = Integer.parseInt(prop.getProperty("port"));
            uid = prop.getProperty("uid");

        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Unable to load config.properties", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading properties from config.properties", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void start() {
        logger.info("Application started");

        MoistureMeasurer moistureMeasurer = new MoistureMeasurer(host, port, uid);
        logger.info("The current moisture value is: " + moistureMeasurer.getMoistureValue());
        moistureMeasurer.close();

        logger.info("Closing Application");
    }
}
