package com.impinj.octanesdk.samples.helpers;

import com.impinj.octanesdk.BufferOverflowEvent;
import com.impinj.octanesdk.BufferOverflowListener;
import com.impinj.octanesdk.ImpinjReader;

public class BufferOverflowListenerImplementation implements
        BufferOverflowListener {

    @Override
    public void onBufferOverflow(ImpinjReader reader, BufferOverflowEvent e) {
        System.out.println("Buffer_Overflow-- ");
    }
}
