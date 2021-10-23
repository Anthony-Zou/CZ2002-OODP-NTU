package Controller;
import Entity.*;
import java.io.*;
import java.util.*;
public class Database_Controller {
    /**
     * constant to store the file path for storing the values in the object file .
     */
    private final static String testPath="src/group6/Data/Test.Dat";
    private final static String tablePath="src/group6/Data/Table.Dat";
    public static void main( String[] args ) {
        try {
            FileWriter     fwStream = new FileWriter(   testPath );
            BufferedWriter bwStream = new BufferedWriter( fwStream  );
            PrintWriter    pwStream = new PrintWriter(    bwStream  );
            int num ;
            for ( num = 0 ; num < 5 ; num++ )
                pwStream.println( "Number = " + num * 5 );
            pwStream.close();
        }
        catch ( IOException e ) {
            System.out.println( "IO Error!" + e.getMessage() );
            e.printStackTrace();
            System.exit( 0 );
        }
    }
}
