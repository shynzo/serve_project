package teste1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class VerificaAberto {

	public static boolean verf(String mend){
		String line;
	    int instances = 0;
	    try {
	        Process processo = Runtime.getRuntime().exec("wmic.exe");
	        try (BufferedReader br = new BufferedReader(new InputStreamReader(processo.getInputStream()))) {
	            OutputStreamWriter osw = new OutputStreamWriter(processo.getOutputStream());
	            osw.write("process where name='"+mend+".exe'");
	            osw.flush();
	            osw.close();
	            while ((line = br.readLine()) != null) {
	                if (line.contains(mend+".exe")) {
	                    instances++;
	                }
	            }
	           
	        }
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	    
	    if(instances>0)
        	return true;
        else
        	return false;
	}
}

