package com.impinj.octanesdk.samples;

import java.util.Scanner;

import com.impinj.octanesdk.ImpinjReader;
import com.impinj.octanesdk.OctaneSdkException;
import com.impinj.octanesdk.Settings;
import com.impinj.octanesdk.samples.helpers.ConnectionLostListenerImplementation;
import com.impinj.octanesdk.samples.helpers.KeepAliveListenerImplementation;
import com.impinj.octanesdk.samples.helpers.SampleProperties;


public class Keepalives {

    public static void main(String[] args) {
        try {

            String hostname = System.getProperty(SampleProperties.hostname);                       
            
            if (hostname == null) {
                throw new Exception("Must specify the '" 
                        + SampleProperties.hostname + "' property");
            }
            
            ImpinjReader reader = new ImpinjReader();
            
            System.out.println("Connecting");
            reader.connect(hostname);

            Settings settings = reader.queryDefaultSettings();

            // turn on the keepalives
            settings.getKeepalives().setEnabled(true);
            settings.getKeepalives().setPeriodInMs(3000);

            // turn on automatic link monitoring
            settings.getKeepalives().setEnableLinkMonitorMode(true);
            settings.getKeepalives().setLinkDownThreshold(5);

            // set up a listener for keepalives
            reader.setKeepaliveListener(new KeepAliveListenerImplementation());

            // set up a listener for connection Lost
            reader.setConnectionLostListener(
                    new ConnectionLostListenerImplementation());

            // apply the settings to enable keepalives
            reader.applySettings(settings);

            System.out.println("Press Enter to exit.");
            Scanner s = new Scanner(System.in);
            s.nextLine();

            reader.disconnect();
        } catch (OctaneSdkException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }
}
