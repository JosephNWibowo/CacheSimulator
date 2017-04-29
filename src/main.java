import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Joey on 4/28/2017.
 */
public class main {
    public static void main(String[] args) throws IOException {
       int dataReads,
            dataWrites,
            dataAccess,
            totDataReadMisses,
            totDataWriteMisses,
            numDataMisses;


        String path = args[0];                          //taking arguments as the file path
        Scanner txtFile = new Scanner(new File(path));  //put the file to be scanned


        while (txtFile.hasNext()) {
            String[] lineSplit = txtFile.nextLine().split(" :");
            for(int i = 0; i < lineSplit.length; i++) {
                System.out.println(lineSplit[i] + " ");
            }
            
        }



    }
}
