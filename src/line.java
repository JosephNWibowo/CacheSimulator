/**
 * Created by Joey on 5/3/2017.
 */
public class line {
    boolean validBit = false;
    String binary;
    String indexBin;
    String tagBin;


    public line(String binaryInput, int offset, int indexBits, int tagBits) {
        binary = binaryInput;
        int startBinIndex = binaryInput.length() - (offset + indexBits);
        int endBinIndex = binaryInput.length() - offset;
        indexBin = binaryInput.substring(startBinIndex, endBinIndex);
        tagBin = binaryInput.substring(0, startBinIndex);
    }

    public boolean isValidBit() {
        return validBit;
    }

    public void setValidBit(boolean validBit) {
        this.validBit = validBit;
    }

    public String getBinary() {
        return binary;
    }

    public void setBinary(String binary) {
        this.binary = binary;
    }

    public String getIndexBin() {
        return indexBin;
    }

    public void setIndexBin(String indexBin) {
        this.indexBin = indexBin;
    }

    public String getTagBin() {
        return tagBin;
    }

    public void setTagBin(String tagBin) {
        this.tagBin = tagBin;
    }
}
