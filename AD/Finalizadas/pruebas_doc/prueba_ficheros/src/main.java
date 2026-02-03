import java.io.File;
import java.io.IOException;

class FileCopy {
    /*
    Class to test FileInputStream and FileOutputStream.

    Copy byte to byte of files

    Sintaxis:
    FileCopy sourceFile destinationFile.

    */
    public static void main(String[] args) throws Exception {
// Byte readed from source
        int bytes;
// Bytes (effectively) writen to dest 
        long bytesCopied=0;

// Streams

        FileInputStream fis= null;
        FileOutputStream fundido=null;

// To provide information about source
        File f;


// Are the arguments ok?
        if(args.length!=2){
            System.out.println("Número de argumentos erróneo. Sintaxis:\n FileCopy archivoOrigen archivoDesti");
            return;
        }

        try{

// show source size
            f=new File(args[0]);
            System.out.println("Total: "+f.length()+" bytes");

// Create streams
            fis=new FileInputStream(args[0]);
            fos=new FileOutputStream(args[1]);

            do {
// read one byte from source
                bytes=fis.read();
// write in destination
                if (bytes!=-1)
                    fos.write(bytes);
// Update number of bytes
                bytesCopied++;

// Show progress (think alternativas as exercise)
                System.out.print("\rCopiados "+(bytesCopied-1)+" bytes...");
            }while (bytes!=-1);
            System.out.println("Done it!");


        }catch (IOException exc){
            System.out.println("Error de entrada y salida: "+exc);
        }finally {
// En el final, no hay que terminar las filas, sino en error existentes o no.
            try {
                if (fis!=null) fis.close();
            }catch (IOException exc){
                System.out.println("Error al cerrar el archivo de origen.");
            }
            try {
                if(fos!=null) fos.close();
            }catch (IOException exc){
                System.out.println("Error al cerrar el archivo destino.");
            }
        }
    }
}