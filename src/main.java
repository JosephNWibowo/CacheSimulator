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
        int totDataReadMisses = 0;
        int totDataWriteMisses = 0;
        int totalMisses;
        int dirtyReadMisses = 0;
        int dirtyWriteMisses = 0;
        int bytesRead = 0;
        int bytesWritten = 0;
        int missPenalty = 80;
        int totReadTime = 0;
        int totWriteTime = 0;
        int totTime;
        double MissRate;
        int tempIntHolder;
        int tempByteIntHolder;
        String tempTagHolder;
        String tempString;
        String tempBinaryString;
        String tempStringHolder;
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

        ArrayList<line> cache = new ArrayList<line>(64);       //creating 64 blocks representing the cache-
        for(int i = 0; i < 64; i++) {                                       //-indexes
            tempLine = new line();
            cache.add(tempLine);
        }


        while (txtFile.hasNext()) {
            String[] lineSplit = txtFile.nextLine().split("[: ]+");  //to remove colon and  multiple spaces

            if (lineSplit[1].equals("W")) {                                //if 2nd element W then count write else-
                dataWrites++;                                              //-count read and the bytes as well
                tempByteIntHolder = Integer.parseInt(lineSplit[3]);
                bytesWritten += tempByteIntHolder;
            } else {
                dataReads++;
                tempByteIntHolder = Integer.parseInt(lineSplit[3]);
                bytesRead += tempByteIntHolder;
            }



            tempString = lineSplit[2].substring(2);                        //getting the hexadecimal address
            tempBinaryString = hexToBinary(tempString);                    //convert hex to binary
            tempLine = new line(tempBinaryString, offset, numIndexes);     //create object line that splits the binary
            tempStringHolder = tempLine.getIndexBin();                     //get the index binary, and convert it to dec
            tempIntHolder = binaryToDec(tempStringHolder);                 //getting index binary in decimal
            tempTagHolder = tempLine.getTagBin();

            if ((cache.get(tempIntHolder).isValidBit()) == 0) {            //check if valid bit is 0

                if (lineSplit[1].equals("W")) {                            //since valid bit is 0 it will be a miss
                    totDataWriteMisses++;
                    cache.get(tempIntHolder).addTag(tempTagHolder);        //add  the tag
                    cache.get(tempIntHolder).setValidBit(1);               //change the valid bit
                    totWriteTime = totWriteTime + (1 + missPenalty);
                } else {
                    totDataReadMisses++;
                    totReadTime = totReadTime + (1 + missPenalty);
                }

            } else {
                cache.get(tempIntHolder).compareTag(tempTagHolder);        //compare tag; add if no same tag
                if (!cache.get(tempIntHolder).isHit())
                    if (lineSplit[1].equals("W")) {
                        totDataWriteMisses++;
                        dirtyWriteMisses++;
                        totWriteTime = totWriteTime + (1 + (2 * missPenalty));
                    } else {
                        totDataReadMisses++;
                        dirtyReadMisses++;
                        totReadTime = totReadTime + (1 + (2 * missPenalty));
                    }
                if (cache.get(tempIntHolder).isHit()) {
                    if (lineSplit[1].equals("W")) {
                        totWriteTime++;
                    } else {
                        totReadTime++;
                    }
                }

            }
            cache.get(tempIntHolder).setHit(false);
        }

        dataAccess = dataReads + dataWrites;
        totalMisses = totDataReadMisses + totDataWriteMisses;
        totTime = totReadTime + totWriteTime;
        MissRate = (double) totalMisses / dataAccess;


        System.out.println("Direct Mapped Cache, writeback, with size: " + StringCacheSize);

        System.out.println("data reads: " + dataReads
                + ", data writes: " + dataWrites
                + ", total accesses: " + dataAccess
                + "\nread misses: " + totDataReadMisses
                + ", write misses: " + totDataWriteMisses
                + ", total misses: " + totalMisses
                + "\ndirty read misses: " + dirtyReadMisses
                + ", dirty write misses: " + dirtyWriteMisses
                + "\nbytes read: " + bytesRead
                + ", bytes written: " + bytesWritten
                + "\nread time: " + totReadTime
                + ", write time: " + totWriteTime
                + ", total time: " + totTime
                + "\nmiss rate: " + MissRate);



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

    public static int binaryToDec(String s) {
        int tempNum;
        tempNum = Integer.parseInt(s, 2);
        return tempNum;
    }
}

