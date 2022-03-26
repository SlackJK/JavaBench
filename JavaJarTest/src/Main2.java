import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class Main2
{
    public static String jarDir ="";
    public static void main(String[] args) throws InterruptedException, URISyntaxException
    {
        CodeSource codeSource = Main2.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        jarDir = jarFile.getParentFile().getPath();
        WriteToDataTXT(jarDir+"\\JarCounter.txt","ThreadNum:"+args[0]);
        DoMath.MathMe();
    }
    public static void WriteToDataTXT(String file,String Data)
    {
        try {
            FileWriter myWriter = new FileWriter(file,true);
            myWriter.write(Data);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
