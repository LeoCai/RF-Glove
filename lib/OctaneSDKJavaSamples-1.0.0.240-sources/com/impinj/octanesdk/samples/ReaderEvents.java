package com.impinj.octanesdk.samples;

import java.util.Scanner;

import com.impinj.octanesdk.ImpinjReader;
import com.impinj.octanesdk.OctaneSdkException;
import com.impinj.octanesdk.ReaderMode;
import com.impinj.octanesdk.ReportMode;
import com.impinj.octanesdk.SearchMode;
import com.impinj.octanesdk.Settings;
import com.impinj.octanesdk.samples.helpers.AntennaChangeListenerImplementation;
import com.impinj.octanesdk.samples.helpers.BufferOverflowListenerImplementation;
import com.impinj.octanesdk.samples.helpers.BufferWarningListenerImplementation;
import com.impinj.octanesdk.samples.helpers.ConnectionAttemptListenerImplementation;
import com.impinj.octanesdk.samples.helpers.ConnectionLostListenerImplementation;
import com.impinj.octanesdk.samples.helpers.GpiChangeListenerImplementation;
import com.impinj.octanesdk.samples.helpers.KeepAliveListenerImplementation;
import com.impinj.octanesdk.samples.helpers.ReaderStartListenerImplementation;
import com.impinj.octanesdk.samples.helpers.ReaderStopListenerImplementation;
import com.impinj.octanesdk.samples.helpers.TagReportListenerImplementation;
import com.impinj.octanesdk.samples.helpers.SampleProperties;


public class ReaderEvents {

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
            
            // this will eventually cause buffer events 
            settings.getReport().setMode(ReportMode.Individual);
            settings.setReaderMode(ReaderMode.MaxThroughput);
            settings.setSearchMode(SearchMode.DualTarget);
            settings.setSession(2);
            
          
            settings.getAntennas().enableAll();
            
            // Apply the new settings
            reader.applySettings(settings);

            // turn me on to listen for antenna connect and disconnect
            reader.setAntennaChangeListener(
                    new AntennaChangeListenerImplementation());

            // turn me on to listen for GPI changes
            reader.setGpiChangeListener(
                    new GpiChangeListenerImplementation());

            // turn me on to be notified when the connection to the reader is
            // list
            reader.setConnectionLostListener(
                    new ConnectionLostListenerImplementation());

            // turn me on to be notified when we receive a keep alive from the
            // reader
            reader.setKeepaliveListener(
                    new KeepAliveListenerImplementation());

            // turn me on to be notified when the internal reader buffer is
            // filling
            // indicating that there is a backlog on the reader
            reader.setBufferWarningListener(
                    new BufferWarningListenerImplementation());

            // turn me on to be notified when the internal reader buffer has
            // overflowed
            // and reports and events are being discarded
            reader.setBufferOverflowListener(
                    new BufferOverflowListenerImplementation());

            // turn me on to learn when another application tries to connect to
            // this reader
            reader.setConnectionAttemptListener(
                    new ConnectionAttemptListenerImplementation());

            // turn me on to learn when the reader starts and stops inventory
            reader.setReaderStopListener(
                    new ReaderStopListenerImplementation());
            
            reader.setReaderStartListener(
                    new ReaderStartListenerImplementation());

            // don't turn on this handler as we don't want to see all the 
            // tag prinouts 
            //reader.setTagReportListener(
            //        new TagReportListenerImplementation());            
            
            // Start the reader
            reader.start();

            System.out.println("Press Enter to stop.");
            Scanner s = new Scanner(System.in);
            s.nextLine();

            System.out.println("Stopping  " + hostname);
            reader.stop();                      
            
            System.out.println("Press Enter to exit.");
            s.nextLine();    
             
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
