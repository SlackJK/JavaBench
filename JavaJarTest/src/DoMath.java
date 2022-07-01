import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DoMath
{
    public static void MathMe() throws InterruptedException
    {
        while(true)
        {
            System.out.println(Math.log(Math.pow(getRandomInt(2,11),getRandomInt(2,100))));
            TimeUnit.MILLISECONDS.sleep(100);
            if(Test())
            {
                break;
            }
        }
    }
    public static int getRandomInt(int min, int max)
    {
        min = (int) Math.ceil(min);
        max = (int) Math.floor(max);
        return (int) Math.floor(Math.random() * (max - min) + min); //The maximum is exclusive and the minimum is inclusive
    }
    public static boolean Test()
    {
        String data;
        if(Main2.os.contains("Windows"))
        {
            data = TXTtoString(Main2.jarDir+"\\StopJar.txt");
        }
        else{
            data = TXTtoString(Main2.jarDir+"/StopJar.txt");
        }
        if(data.contains("Stop"))
        {
            return true;
        }
        return false;
    }
    public static String TXTtoString(String File)
    {
        String Out ="";
        try(BufferedReader br = new BufferedReader(new FileReader(File)))
        {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            Out = everything;
            //System.out.println(Out);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return Out;
    }
}
