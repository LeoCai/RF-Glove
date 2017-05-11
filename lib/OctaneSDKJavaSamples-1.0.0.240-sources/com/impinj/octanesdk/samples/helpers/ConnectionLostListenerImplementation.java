package com.impinj.octanesdk.samples.helpers;

import com.impinj.octanesdk.ConnectionLostListener;
import com.impinj.octanesdk.ImpinjReader;
import com.impinj.octanesdk.OctaneSdkException;

public class ConnectionLostListenerImplementation implements
        ConnectionLostListener {

    @Override
    public void onConnectionLost(ImpinjReader reader) {
        System.out.println("Connection Lost: Disconnecting");
        reader.disconnect();
        System.exit(0);
    }
}
