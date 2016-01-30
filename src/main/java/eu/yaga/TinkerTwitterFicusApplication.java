package eu.yaga;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TinkerTwitterFicusApplication {

    private static final String CONFIG_FILE = "config.properties";

    private static final Logger LOGGER = Logger.getLogger(TinkerTwitterFicusApplication.class.getName());
    private static Properties prop = new Properties();

    private static String HOST;
    private static int PORT;
    private static String UID;

    private static String OWNER;
    private static int BORDER;

    private int moisture = 0;
    private ScheduledExecutorService scheduledExecutorService;
    private MoistureAnalyzer moistureAnalyzer;

    public static void main(String[] args) {
        loadProperties();
        new TinkerTwitterFicusApplication().start();
    }

    private static void loadProperties() {
        LOGGER.info("loading config.properties");
        InputStream input = null;

        try {
            input = TinkerTwitterFicusApplication.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
            prop.load(input);

            HOST = prop.getProperty("host");
            PORT = Integer.parseInt(prop.getProperty("port"));
            UID = prop.getProperty("uid");

            OWNER = prop.getProperty("owner");
            BORDER = Integer.parseInt(prop.getProperty("border"));
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Unable to load config.properties", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading properties from config.properties", e);
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
        LOGGER.info("Application started");

        moistureAnalyzer = new MoistureAnalyzer(BORDER, OWNER);

        scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduleMoistureMeasuring();
        scheduleRegularTwitterUpdates();

        LOGGER.info("Closing Application");
    }

    private void scheduleMoistureMeasuring() {
        ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                MoistureMeasurer moistureMeasurer = new MoistureMeasurer(HOST, PORT, UID);
                moisture = moistureMeasurer.getMoistureValue();
                LOGGER.info("The current moisture value is: " + moisture);

                String message = moistureAnalyzer.analyze(moisture);
                if (message != null) {
                    // Tweet it
                    LOGGER.info("tweeting message...");
                    TwitterClient twitterClient = new TwitterClient();
                    //twitterClient.tweet(message);
                }

                moistureMeasurer.close();
            }
        }, 0, 30, TimeUnit.SECONDS);
    }

    private void scheduleRegularTwitterUpdates() {
        ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

            public void run() {
                TwitterClient twitterClient = new TwitterClient();
                String message = "Yo, my current moisture value is " + moisture;
                LOGGER.info("Sending twitter update: " + message);
                //twitterClient.tweet(message);
            }
        }, 1, 8, TimeUnit.HOURS);
    }
}
