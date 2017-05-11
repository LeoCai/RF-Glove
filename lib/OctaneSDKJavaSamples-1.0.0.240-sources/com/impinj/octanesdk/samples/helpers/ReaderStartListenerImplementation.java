package com.impinj.octanesdk.samples.helpers;

import com.impinj.octanesdk.ReaderStartListener;
import com.impinj.octanesdk.ReaderStartEvent;
import com.impinj.octanesdk.ImpinjReader;

public class ReaderStartListenerImplementation implements ReaderStartListener {

    @Override
    public void onReaderStart(ImpinjReader reader, ReaderStartEvent e) {
        System.out.println("Reader_Started");
    }
}
