package ValueObjects;

public class Sample {
    private String initCD;
    private String sampleBrand;
    private String sampleTime;
    private String recordDate;

    public Sample(){
        initCD = "";
        sampleBrand = "";
        sampleTime = "";
    }

    public String getInitCD() {
        return initCD;
    }

    public String getSampleBrand() {
        return sampleBrand;
    }

    public String getSampleTime() {
        return sampleTime;
    }

    public void setInitCD(String initCD) {
        this.initCD = initCD;
    }

    public void setSampleBrand(String sampleBrand) {
        this.sampleBrand = sampleBrand;
    }

    public void setSampleTime(String sampleTime) {
        this.sampleTime = sampleTime;
    }

    @Override
    public String toString() {
        return "CD: " + this.initCD + "\nMarca: " + this.sampleBrand + "\nHora: " + this.sampleTime;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
}
