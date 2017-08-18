package co.phystech.aosorio.officedocgen;

import java.io.IOException;

import co.phystech.aosorio.images.QRCodeGenerator;
import co.phystech.aosorio.officedocgen.ex.SimpleDocumentWithHeader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
           
        try {
			
        	QRCodeGenerator.generate();
        	
        	SimpleDocumentWithHeader.generate();
			
	
        } catch (IOException e) {
		
        	e.printStackTrace();
		}
    
    }
     
}
