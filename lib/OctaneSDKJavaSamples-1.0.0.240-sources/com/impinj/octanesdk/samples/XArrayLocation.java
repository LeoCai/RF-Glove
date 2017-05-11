package com.impinj.octanesdk.samples;

import java.util.Scanner;

import com.impinj.octanesdk.ImpinjReader;
import com.impinj.octanesdk.OctaneSdkException;
import com.impinj.octanesdk.LocationConfig;
import com.impinj.octanesdk.PlacementConfig;
import com.impinj.octanesdk.ReaderMode;
import com.impinj.octanesdk.Settings;
import com.impinj.octanesdk.XArrayMode;
import com.impinj.octanesdk.samples.helpers.LocationReportListenerImplementation;
import com.impinj.octanesdk.samples.helpers.SampleProperties;

public class XArrayLocation {

    public static void main(String[] args) {
        try {
            String hostname = System.getProperty(SampleProperties.hostname);                       

            if (hostname == null) {
                throw new Exception("Must specify the '" 
                        + SampleProperties.hostname + "' property");
            }    
               
            ImpinjReader reader = new ImpinjReader();     
            
            reader.connect(hostname);

            // set up the listener for special location reports
            reader.setLocationReportListener(
                    new LocationReportListenerImplementation());

            Settings settings = reader.queryDefaultSettings();

            // set xarray into location mode
            settings.getXArray().setMode(XArrayMode.Location);

            // Set xArray placement parameters
            PlacementConfig pc = settings.getXArray().getPlacement();
            
            // The mounting height of the xArray, in centimeters
            pc.setHeightCm((short) 457);

            // These settings aren't required in a single xArray environment
            // They can be set to zero (which is the default)
            pc.setFacilityXLocationCm(0);
            pc.setFacilityYLocationCm(0);
            pc.setOrientationDegrees((short) 0);

            LocationConfig lc = settings.getXArray().getLocation();
            
            // set up filtering and aging on the tgs
            lc.setComputeWindowSeconds((short) 10);
            lc.setTagAgeIntervalSeconds((short) 20);

            // set up how often we want to get update reports
            lc.setUpdateIntervalSeconds((short) 5);

            // enable all three reports
            lc.setEntryReportEnabled(true);
            lc.setExitReportEnabled(true);
            lc.setUpdateReportEnabled(true);

            // set up some general reader settings
            settings.setSession(2);
            settings.setReaderMode(ReaderMode.AutoSetDenseReader);

            reader.applySettings(settings);

            reader.start();

            System.out.println("Press enter to continue.");
            Scanner s = new Scanner(System.in);
            s.nextLine();

            reader.stop();

            reader.disconnect();
        } catch (OctaneSdkException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }
}
