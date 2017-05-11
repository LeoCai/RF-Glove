package com.impinj.octanesdk.samples.helpers;

import com.impinj.octanesdk.ConnectionAttemptEvent;
import com.impinj.octanesdk.ConnectionAttemptListener;
import com.impinj.octanesdk.ImpinjReader;

public class ConnectionAttemptListenerImplementation implements
        ConnectionAttemptListener {

    @Override
    public void onConnectionAttempt(ImpinjReader reader,
            ConnectionAttemptEvent e) {
        System.out.println("Connection_Attempt ");
    }
}
