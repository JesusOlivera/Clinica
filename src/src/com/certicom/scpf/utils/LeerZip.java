package src.com.certicom.scpf.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class LeerZip {
	List listaArchivos;
//    private static final String ARCHIVO_ZIP = "C:/SFS_v1.2/sunat_archivos/sfs/RPTA/R20603298714-01-F001-00000008.zip";
//    private static final String RUTA_SALIDA = "C:/SFS_v1.2/SALIDA/";
//    public static void main(String[] args) {
//        LeerZip leerZip = new LeerZip();
//        leerZip.unZip(ARCHIVO_ZIP, RUTA_SALIDA);
//    }
    public  void unZip(String archivoZip, String rutaSalida) {
        byte[] buffer = new byte[1024];
        try {
        	
        	System.out.println("unZip: archivoZip ==> "+archivoZip);
        	System.out.println("unZip: rutaSalida ==> "+rutaSalida);
        	
        	
            File folder = new File(rutaSalida);
            if (!folder.exists()) {
                folder.mkdir();
            }
            ZipInputStream zis = new ZipInputStream(new FileInputStream(archivoZip));
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String nombreArchivo = ze.getName();
                File archivoNuevo = new File(rutaSalida + File.separator + nombreArchivo);
                System.out.println("archivo descomprimido : " + archivoNuevo.getAbsoluteFile());
                new File(archivoNuevo.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(archivoNuevo);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            System.out.println("Listo");
        } catch (IOException ex) {
            ex.printStackTrace();
           
        } 
    }
}
