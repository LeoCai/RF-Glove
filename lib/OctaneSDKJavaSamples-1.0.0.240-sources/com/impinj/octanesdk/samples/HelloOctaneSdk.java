package com.impinj.octanesdk.samples;

import java.util.Scanner;

import com.impinj.octanesdk.ImpinjReader;
import com.impinj.octanesdk.OctaneSdkException;
import com.impinj.octanesdk.Settings;
import com.impinj.octanesdk.samples.helpers.TagReportListenerImplementation;
import java.util.Properties;
import java.util.Enumeration;
import com.impinj.octanesdk.samples.helpers.SampleProperties;


public class HelloOctaneSdk {

    public static void main(String[] args) {

        try {
            String hostname = System.getProperty(SampleProperties.hostname);                       
            
            if (hostname == null) {
                throw new Exception("Must specify the '" 
                        + SampleProperties.hostname + "' property");
            }
            
            ImpinjReader reader = new ImpinjReader();

            // Connect
            System.out.println("Connecting to " + hostname);
            reader.connect(hostname);

            // Get the default settings
            Settings settings = reader.queryDefaultSettings();

            // Apply the new settings
            reader.applySettings(settings);

            // connect a listener
            reader.setTagReportListener(new TagReportListenerImplementation());

            // Start the reader
            reader.start();

            System.out.println("Press Enter to exit.");
            Scanner s = new Scanner(System.in);
            s.nextLine();

            System.out.println("Stopping  " + hostname);
            reader.stop();

            System.out.println("Disconnecting from " + hostname);
            reader.disconnect();

            System.out.println("Done");
        } catch (OctaneSdkException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }
}
