package com.impinj.octanesdk.samples.helpers;

import com.impinj.octanesdk.ReaderStopListener;
import com.impinj.octanesdk.ReaderStopEvent;
import com.impinj.octanesdk.ImpinjReader;

public class ReaderStopListenerImplementation implements ReaderStopListener {

    @Override
    public void onReaderStop(ImpinjReader reader, ReaderStopEvent e) {
        System.out.println("Reader_Stopped");
    }
}
