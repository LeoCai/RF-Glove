package com.impinj.octanesdk.samples.helpers;

import com.impinj.octanesdk.BufferWarningEvent;
import com.impinj.octanesdk.BufferWarningListener;
import com.impinj.octanesdk.ImpinjReader;

public class BufferWarningListenerImplementation implements
        BufferWarningListener {

    @Override
    public void onBufferWarning(ImpinjReader reader, BufferWarningEvent e) {
        System.out.println("Buffer_Warning--percent_full: "
                + e.getPercentFull());
    }
}
