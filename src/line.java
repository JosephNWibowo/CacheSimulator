import java.util.ArrayList;

/**
 * Created by Joey on 5/3/2017.
 */
public class line {
    boolean validBit = false;
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

        //todo: placement of this for each definitely wrong
        /*for (tag eachTag : tagsInIndex) {
            if (tempTagBin.equals(eachTag.getTagBinary())) {
                System.out.println("hit!!");
            } else {
                System.out.println("miss!!");
                tag tempTag = new tag(tempTagBin);
                tagsInIndex.add(tempTag);
            }
        }*/
    }

    public line() {
        validBit = isValidBit();
        binary = getBinary();
        indexBin = getIndexBin();
        tempTagBin= getTagBin();
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
        return tempTagBin;
    }

    public void setTagBin(String tagBin) {
        this.tempTagBin= tagBin;
    }
}
