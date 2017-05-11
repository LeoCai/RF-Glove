package com.impinj.octanesdk.samples.helpers;

import com.impinj.octanesdk.ImpinjReader;
import com.impinj.octanesdk.LocationReport;
import com.impinj.octanesdk.LocationReportListener;

public class LocationReportListenerImplementation implements
        LocationReportListener {

    @Override
    public void onLocationReported(ImpinjReader reader, LocationReport report) {
        System.out.println("Location: " + " epc: "
                + report.getEpc().toHexString() + " x: "
                + report.getLocationXCm() + " y: " + report.getLocationYCm()
                + " read_count: "
                + report.getConfidenceFactors().getReadCount());
    }
}
