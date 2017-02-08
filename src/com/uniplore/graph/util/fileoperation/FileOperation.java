package com.uniplore.graph.util.fileoperation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class FileOperation {
	 public static String readFileContent(InputStream in) { 
         StringBuffer sb = new StringBuffer(); 
         try { 
                 Reader r = new InputStreamReader(in); 
                 int length = 0; 
                 for (char[] c = new char[1024]; (length = r.read(c)) != -1;) { 
                         sb.append(c, 0, length); 
                 } 
                 r.close(); 
         } catch (UnsupportedEncodingException e) { 
                 e.printStackTrace(); 
         } catch (FileNotFoundException e) { 
                 e.printStackTrace(); 
         } catch (IOException e) { 
                 e.printStackTrace(); 
         } 
         return sb.toString(); 
 } 
}
