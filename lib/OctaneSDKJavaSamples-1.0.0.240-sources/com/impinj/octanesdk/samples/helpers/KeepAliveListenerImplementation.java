package com.impinj.octanesdk.samples.helpers;

import com.impinj.octanesdk.ImpinjReader;
import com.impinj.octanesdk.KeepaliveListener;
import com.impinj.octanesdk.KeepaliveEvent;

public class KeepAliveListenerImplementation implements KeepaliveListener {

    @Override
    public void onKeepalive(ImpinjReader reader, KeepaliveEvent e) {
        System.out.println("!KeepAlive!");
    }
}
