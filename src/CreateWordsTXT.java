import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateWordsTXT
{
    public static void CreateWordCSV(ArrayList<String> Words) throws IOException {
        String Data = "";
        for (String Word:Words)
        {
            Data+= Word+",";
        }
        Data = Data.substring(0,Data.length()-1);
        CreateTextTXT(Data,"WordBank.txt");
        System.out.println("Finished creating csv.");

    }
    public static void CreateTextTXT(String text) throws IOException
    {
        FileWriter myWriter = new FileWriter("BenchText.txt");
        myWriter.write(text);
        myWriter.close();
        System.out.println("Finished creating Text txt.");
    }
    public static void CreateTextTXT(String text,String File) throws IOException
    {
        FileWriter myWriter = new FileWriter(File);
        myWriter.write(text);
        myWriter.close();
    }
    public String TXTtoString()
    {
        String Out ="";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("BenchText.txt"))))
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
    public String TXTtoString(String File)
    {
        String Out ="";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(File))))
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
            System.out.println(Out);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return Out;
    }
    /*
    public String CSVtoString()
    {
        String Out ="";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("WordBank.txt")))
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
            System.out.println(Out);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return Out;
    }
     */
    public static ArrayList<String> CSVtoArrayList()
    {
        CreateWordsTXT CW = new CreateWordsTXT();
        String temp = CW.TXTtoString("WordBank.txt");
        String[] x = temp.split(",");
        ArrayList<String> al = new ArrayList<String>(Arrays.asList(x));
        System.out.println(al);
        return al;
    }
    public static void CreateATXTFile()
    {
        try {
            File myObj = new File("TestDataFile.txt");
            if (myObj.createNewFile()) {
                System.out.println("Testing file created: " + myObj.getName());
            } else {
                System.out.println("Testing file already exists and will be used instead.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void WriteToDataTXT(String Data)
    {
        try {
            FileWriter myWriter = new FileWriter("TestDataFile.txt",true);
            myWriter.write(Data);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
