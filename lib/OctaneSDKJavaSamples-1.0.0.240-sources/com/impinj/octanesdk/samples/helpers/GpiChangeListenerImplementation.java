package com.impinj.octanesdk.samples.helpers;

import com.impinj.octanesdk.GpiChangeListener;
import com.impinj.octanesdk.GpiEvent;
import com.impinj.octanesdk.ImpinjReader;

public class GpiChangeListenerImplementation implements GpiChangeListener {

    @Override
    public void onGpiChanged(ImpinjReader reader, GpiEvent e) {
        System.out.println("GPI Change--port: " + e.getPortNumber()
                + " status: " + e.isState());
    }
}
