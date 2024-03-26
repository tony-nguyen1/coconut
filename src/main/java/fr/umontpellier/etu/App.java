package fr.umontpellier.etu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {

        System.out.println( "Hello World!" );

        for (int i=0; i<4*5; i++) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            TimeUnit.SECONDS.sleep(15);
        }

        System.out.println( "Goodbye World!" );
    }
}
