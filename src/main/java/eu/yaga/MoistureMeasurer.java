package eu.yaga;

import com.tinkerforge.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoistureMeasurer {

    private static final Logger LOGGER = Logger.getLogger(MoistureMeasurer.class.getName());
    private IPConnection ipcon = new IPConnection();
    private BrickletMoisture m;

    public MoistureMeasurer(String host, int port, String uid) {
        connect(host, port, uid);
    }

    private void connect(String host, int port, String uid) {
        m = new BrickletMoisture(uid, ipcon);

        try {
            ipcon.connect(host, port);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error connecting to brick on " + host + ":" + port, e);
        } catch (AlreadyConnectedException e) {
            LOGGER.log(Level.WARNING, "Already connected: " + host + ":" + port, e);
        }
    }

    public void close() {
        try {
            ipcon.disconnect();
        } catch (NotConnectedException e) {
            LOGGER.log(Level.SEVERE, "Not connected error while closing IPConnection", e);
        }
    }

    public int getMoistureValue() {
        try {
            return m.getMoistureValue();
        } catch (TimeoutException e) {
            LOGGER.log(Level.SEVERE, "Timeout while getting MoistureValue on " + m, e);
        } catch (NotConnectedException e) {
            LOGGER.log(Level.SEVERE, "Not connected error while getting MoistureValue on " + m, e);
        }
        return 0;
    }
}
