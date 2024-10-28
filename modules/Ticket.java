package modules;
public class Ticket {
    private final int barcodeId;
    private final boolean isWinning;
    
    public Ticket(int barcodeId,boolean isWinning) {
        this.barcodeId = barcodeId;
        this.isWinning = isWinning;
    }

    public int getBarcodeId() {
        return this.barcodeId;
    }

    public boolean isWinning() {
            return this.isWinning;
    }

    public String getRecord(){
        return this.barcodeId + "," + (this.isWinning ? "True" : "False") + "\n";
    }
}
