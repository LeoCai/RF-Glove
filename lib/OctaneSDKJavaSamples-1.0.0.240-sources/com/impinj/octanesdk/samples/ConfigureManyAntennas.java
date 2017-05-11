package com.impinj.octanesdk.samples;

import java.util.Scanner;

import com.impinj.octanesdk.AntennaConfigGroup;
import com.impinj.octanesdk.ImpinjReader;
import com.impinj.octanesdk.OctaneSdkException;
import com.impinj.octanesdk.ReportMode;
import com.impinj.octanesdk.Settings;
import com.impinj.octanesdk.FeatureSet;
import com.impinj.octanesdk.samples.helpers.TagReportListenerImplementation;
import com.impinj.octanesdk.samples.helpers.SampleProperties;

public class ConfigureManyAntennas {

    // This examples shows the different ways to configure antennas.
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

            FeatureSet features = reader.queryFeatureSet();


            // Get the default settings
            Settings settings = reader.queryDefaultSettings();

            // send a tag report for every tag read
            settings.getReport().setMode(ReportMode.Individual);
            settings.getReport().setIncludeAntennaPortNumber(true);

            // disable all antennas, then enable our special set
            AntennaConfigGroup ac = settings.getAntennas();
            
            ac.disableAll();

            // is it an xarray 
            if ((features.getModelNumber() == 2001004)
                    || (features.getModelNumber() == 2001006)
					|| (features.getModelNumber() == 2001007)) {
                // in xarray, you can enable by sector or ring as well   
                System.out.println("enabling ring 4 and 7");                                
                ac.enableByRing(new short[]{4, 7});
                System.out.println("enabling sector 3,4 and 5");                                
                ac.enableBySector(new short[]{3, 4, 5});
            } else {
                long max = features.getAntennaCount();
                System.out.println("enabling antenna 1 and antenna " + max);                
                ac.getAntenna((short) 1).setEnabled(true);
                ac.getAntenna((short) max).setEnabled(true);
            }

            // set all to max power
            ac.setIsMaxTxPower(true);
            ac.setIsMaxRxSensitivity(true);

            // or set them to a specific power
            String power = System.getProperty(SampleProperties.powerDbm);

            if (power != null) {
                double pwr = Double.parseDouble(power);
                ac.setIsMaxTxPower(false);
                ac.setTxPowerinDbm(pwr);
            }

            String rxSens = System.getProperty(SampleProperties.sensitivityDbm);

            if(rxSens != null) {
                double rx = Double.parseDouble(rxSens);                
                ac.setIsMaxRxSensitivity(false);
                ac.setRxSensitivityinDbm(rx);
            }


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
