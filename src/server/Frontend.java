/**
 * Java Server - Frontend index utility.
 *
 * @ 25.10.2019 # Aleksandr Vorkunov <developing@nodes-tech.ru>
 */

package server;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Frontend {
    private String request;
    Map<String, byte[]> files;
    private static final Map<String, String> fileExtensionMap;
    static {
        fileExtensionMap = new HashMap<String, String>();
        fileExtensionMap.put("html", "text/html");
        fileExtensionMap.put("txt", "text/plain");
        fileExtensionMap.put("rtf", "application/rtf");
        fileExtensionMap.put("pdf", "application/pdf");
    }
    //------------------------------------------------------------------------------------------------------------------
    public Frontend() {
        this.files = new HashMap<String, byte[]>();
        int files = this.readDir("");
        System.out.println("Loading completed. "+files+" files loaded.");
    }
    //------------------------------------------------------------------------------------------------------------------
    public byte[] fout(String request){
        this.request = request;
        if(this.files.containsKey(request)){
            return this.files.get(request);
        }else{
            return null;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    public String getMimeType(String fileName) {
        FileNameMap mimeTypes = URLConnection.getFileNameMap();
        String contentType = mimeTypes.getContentTypeFor(fileName);
        if (contentType == null) {
            String extension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());;
            contentType = fileExtensionMap.get(extension);
        }
        if(contentType == null){
            contentType = "text/plain";
        }
        return contentType;
    }
    //------------------------------------------------------------------------------------------------------------------
    int readDir(String dir){
        int files = 0;
        File folder = new File("front"+dir);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String filename = dir+"/"+listOfFiles[i].getName();
                try {
                    byte[] fileContent = Files.readAllBytes(Paths.get("front"+filename));
                    this.files.put(dir + "/" + listOfFiles[i].getName(), fileContent);
                    files++;
                }catch(IOException e){
                    System.out.println(e.toString());
                }
            } else if (listOfFiles[i].isDirectory()) {
                files += this.readDir(dir+"/"+listOfFiles[i].getName());
            }
        }
        return files;
    }
}