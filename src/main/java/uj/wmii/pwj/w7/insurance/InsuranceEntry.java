package uj.wmii.pwj.w7.insurance;

public class InsuranceEntry {
    private long policyID;
    private String statecode;
    private String county;
    private double eqSiteLimit;
    private double huSiteLimit;
    private double flSiteLimit;
    private double frSiteLimit;
    private double tiv2011;
    private double tiv2012;
    private double eqDeductible;
    private double huDeductible;
    private double flDeductible;
    private double frDeductible;
    private double latitude;
    private double longitude;
    private String line;
    private String construction;
    private int pointGranularity;

    public double getTiv2011() {
        return tiv2011;
    }

    public InsuranceEntry(long policyID, String statecode, String county,
                          double eqSiteLimit, double huSiteLimit, double flSiteLimit, double frSiteLimit,
                          double tiv2011, double tiv2012,
                          double eqDeductible, double huDeductible,
                          double flDeductible, double frDeductible,
                          double latitude, double longitude,
                          String line, String construction, int pointGranularity) {
        this.policyID = policyID;
        this.statecode = statecode;
        this.county = county;
        this.eqSiteLimit = eqSiteLimit;
        this.huSiteLimit = huSiteLimit;
        this.flSiteLimit = flSiteLimit;
        this.frSiteLimit = frSiteLimit;
        this.tiv2011 = tiv2011;
        this.tiv2012 = tiv2012;
        this.eqDeductible = eqDeductible;
        this.huDeductible = huDeductible;
        this.flDeductible = flDeductible;
        this.frDeductible = frDeductible;
        this.latitude = latitude;
        this.longitude = longitude;
        this.line = line;
        this.construction = construction;
        this.pointGranularity = pointGranularity;
    }

    public String getCounty() {
        return county;
    }

    public double getTiv2012() {
        return tiv2012;
    }


}
