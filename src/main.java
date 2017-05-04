import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Joey on 4/28/2017.
 */
public class main {
    public static void main(String[] args) throws IOException {
        int dataReads = 0;
        int dataWrites = 0;
        int dataAccess;
        int byteBlocks = 16;
        int bitMemAddress = 32;
        int offset;
        int totDataReadMisses;
        int totDataWriteMisses;
        int numDataMisses;
        int dirtyDataReadMisses;
        int dirtyWriteMisses;
        int bytesReadFrmMem;
        int bytesWrittenToMem;
        int totReadAccessTime;
        int totWriteAccessTime;
        int overallDataCacheMissRate;
        String tempString;
        line tempLine;


        String path = args[0];                                              //taking arguments as the file path
        Scanner txtFile = new Scanner(new File(path));                      //put the file to be scanned

        String StringCacheSize = args[1];                                   //taking the 2nd arguments as the cache size
        int intCacheSize = 1024 * (Integer.parseInt(StringCacheSize));      //converting it into integer and multiply w/
                                                                            //1kb = 1024 bytes

        offset = (int) (Math.log(byteBlocks) / Math.log(2));                //logBase2 the byteblocks to get offset

        int numBlocks = intCacheSize / byteBlocks;                          //#blocks in cache
        int numIndexes = (int) (Math.log(numBlocks) / Math.log(2));         //logbase2(#blocks) to get the index
        int tagBits = bitMemAddress - (numIndexes + offset);                //get tag by subtracting 32 -(index+offset)


        ArrayList<line> cache = new ArrayList<line>();

        while (txtFile.hasNext()) {
            String[] lineSplit = txtFile.nextLine().split("[: ]+");  //to remove colon and  multiple spaces

            if (lineSplit[1].equals("W")) {                                //if 2nd element W then count write else-
                dataWrites++;                                              //-count read
            } else {
                dataReads++;
            }


            tempString = lineSplit[2].substring(2);
            tempLine = new line(hexToBinary(tempString), offset, numIndexes, tagBits);
            System.out.println(hexToBinary(tempString));

            System.out.println(tempLine.getIndexBin());
            System.out.println(tempLine.getTagBin());


            /*System.out.println(lineSplit[2].substring(2));
            tempString = lineSplit[2].substring(2);
            System.out.println(hexToBinary(tempString));*/

        }

        dataAccess = dataReads + dataWrites;

        System.out.println("number of data reads:\t" + dataReads
                + "\nnumber of data writes:\t" + dataWrites
                + "\nnumber of accesses:\t\t" + dataAccess);


        System.out.println("\noffset:\t\t\t\t\t" + offset
                + "\nnumber of lines:\t\t" + numBlocks
                + "\nnumber of indexes:\t\t" + numIndexes
                + "\ntag bits:\t\t\t\t" + tagBits);

    }

    public static String hexToBinary(String s)
    {
        String digits = "0123456789ABCDEF";
        String binary;
        s = s.toUpperCase();
        int val = 0;

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
         binary = Integer.toBinaryString(val);
        return binary;
    }

}

