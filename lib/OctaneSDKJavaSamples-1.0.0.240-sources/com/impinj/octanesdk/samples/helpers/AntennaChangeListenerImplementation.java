package com.impinj.octanesdk.samples.helpers;

import com.impinj.octanesdk.AntennaChangeListener;
import com.impinj.octanesdk.AntennaEvent;
import com.impinj.octanesdk.ImpinjReader;

public class AntennaChangeListenerImplementation implements
        AntennaChangeListener {

    @Override
    public void onAntennaChanged(ImpinjReader reader, AntennaEvent e) {
        System.out.println("Antenna Change--port: " + e.getPortNumber()
                + " state: " + e.getState().toString());
    }
}
