package com.impinj.octanesdk.samples.helpers;

import com.impinj.octanesdk.ImpinjReader;
import com.impinj.octanesdk.TagData;
import com.impinj.octanesdk.TransitionReport;
import com.impinj.octanesdk.TransitionReportListener;

public class TransitionReportListenerImplementation implements
        TransitionReportListener {

    @Override
    public void onTransitionReported(ImpinjReader reader,
            TransitionReport report) {

        System.out.println("Transition: " + " type: "
                + report.getReportType().toString() + " from: "
                + report.getFromZoneId() + " to: " + report.getToZoneId()
                + " confidence: " + report.getConfidence() + " time: "
                + report.getTimestamp().ToString());

        for (TagData e : report.getEpcs()) {
            System.out.println("    Includes Tag:" + e.toHexString());
        }
    }
}
