import java.util.ArrayList;

/**
 * Created by Joey on 5/3/2017.
 */
public class line {
    int validBit = 0;
    boolean hit = false;
    String binary;
    String indexBin;
    String tempTagBin;
    ArrayList<tag> tagsInIndex = new ArrayList<tag>();




    public line(String binaryInput, int offset, int indexBits) {
        binary = binaryInput;
        int startBinIndex = binaryInput.length() - (offset + indexBits);
        int endBinIndex = binaryInput.length() - offset;
        indexBin = binaryInput.substring(startBinIndex, endBinIndex);
        tempTagBin = binaryInput.substring(0, startBinIndex);

    }

    public line() {
        validBit = isValidBit();
        binary = getBinary();
        indexBin = getIndexBin();
        tempTagBin= getTagBin();
    }

    public void addTag(String tempTagBin) {
        tag tempTag = new tag(tempTagBin);
        tagsInIndex.add(tempTag);
    }

    public void compareTag(String tempTagBin) {
        for (int i = 0; i < tagsInIndex.size();i++) {
            if (tempTagBin.equals(tagsInIndex.get(i).getTagBinary())) {
                hit = true;
            }
        }
        if(!hit) {

            tag tempTag = new tag(tempTagBin);
            tagsInIndex.add(tempTag);
        }

    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public int isValidBit() {
        return validBit;
    }

    public void setValidBit(int validBit) {
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
        return tempTagBin;
    }

    public void setTagBin(String tagBin) {
        this.tempTagBin= tagBin;
    }
}
