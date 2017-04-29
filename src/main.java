import java.io.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Joey on 4/28/2017.
 */
public class main {
    public static void main(String[] args) throws IOException {
        int dataReads = 0;
        int dataWrites = 0;
        int dataAccess;
        int totDataReadMisses;
        int totDataWriteMisses;
        int numDataMisses;


        String path = args[0];                                              //taking arguments as the file path
        Scanner txtFile = new Scanner(new File(path));                      //put the file to be scanned


        while (txtFile.hasNext()) {
            String[] lineSplit = txtFile.nextLine().split("[: ]+");  //to remove colon and  multiple spaces

            if (lineSplit[1].equals("W")) {                                //if 2nd element W then count write else-
                dataWrites++;                                              //-count read 
            } else {
                dataReads++;
            }
           /* for(int i = 0; i < lineSplit.length; i++) {
                System.out.println(lineSplit[i]);
            }*/
        }

        dataAccess = dataReads + dataWrites;

        System.out.println("number of data reads:\t\t" + dataReads
                + "\nnumber of data writes:\t\t" + dataWrites
                + "\nnumber of accesses:\t\t\t" + dataAccess);

    }
}
