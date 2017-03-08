package ValueObjects;

public class Task {
    private String brand;
    private int dailySamples;
    private int pendingSamples;

    public Task(){
        this.brand = "";
        this.dailySamples = 0;
        this.pendingSamples = 0;
    }

    public Task(String _brand, int _dailySamples, int _pendingSamples){
        this.brand = _brand;
        this.dailySamples = _dailySamples;
        this.pendingSamples = _pendingSamples;
    }

    public String getBrand() {
        return brand;
    }

    public int getDailySamples() {
        return dailySamples;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDailySamples(int dailySamples) {
        this.dailySamples = dailySamples;
    }

    public int getPendingSamples() {
        return pendingSamples;
    }

    public void setPendingSamples(int pendingSamples) {
        this.pendingSamples = pendingSamples;
    }
}
