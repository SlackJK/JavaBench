import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        CodeSource codeSource = Main.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();
        System.out.println("Pick java benchmark, write the corresponding number for the benchmark:");
        int WhichTest = -1;
        Scanner sc = new Scanner(System.in);
        while(WhichTest != 0 && WhichTest != 1)
        {
            System.out.println("JarBench (testing how many jars can be opened at once) [0], CPU test(just stress test) [1]");
            WhichTest = sc.nextInt();
        }
        if(WhichTest == 0)
        {
            JarJavaBench(jarDir);
        }
        if(WhichTest == 1)
        {
            HeavyJavaBenchMain(jarDir);
        }
    }
    public static void JarJavaBench(String dir) throws InterruptedException, IOException
    {
        int i = 0;
        boolean test = false;
        String WhereIWantIT = CreateDirectory(dir,"\\JarTestBats\\");;
        System.out.println(WhereIWantIT);
        CreateWordsTXT cw = new CreateWordsTXT();
        cw.TruncateCreateTXT(dir+"\\JarCounter.txt");
        cw.TruncateCreateTXT(dir+"\\StopJar.txt");
        while(true)
        {
            String RunME = WriteCMDFile(dir+"\\JavaJarTest.jar", String.valueOf(i),WhereIWantIT);
            //System.out.println(RunME);
            Process runtime = Runtime.getRuntime().exec("cmd.exe /c start call "+RunME);
            //System.out.println(runtime);
            TimeUnit.MILLISECONDS.sleep(100);
            boolean temp = testthread(dir,i);
            if(temp == false)
            {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            else{
                boolean saved = false;
                for (int j = 0; j < 3; j++)
                {
                    TimeUnit.SECONDS.sleep(1);
                    temp = testthread(dir,i);
                    if(temp == false)
                    {
                        saved = true;
                        break;
                    }
                }
                if(saved ==false)
                {
                    break;
                }

            }
            i = i +1;
        }
        cw.WriteToDataTXT(dir+"\\StopJar.txt","Stop");
        System.out.println("Maximum amount of jars:"+i);
    }
    public static boolean testthread(String dir,int i)
    {
        CreateWordsTXT cw = new CreateWordsTXT();
        String JarCounter = cw.TXTtoString(dir+"\\JarCounter.txt");
        System.out.println(JarCounter);
        if(!JarCounter.contains("ThreadNum:"+i))
        {
            return true;
        }
        return false;
    }
    public static String WriteCMDFile(String JarPath,String ThreadID,String FolderWhereYouWantIT)
    {
        String filename = FolderWhereYouWantIT+ThreadID+".bat";
        System.out.println(filename);
        try {

            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("java -jar "+JarPath+" "+ThreadID+"\nexit");//
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return filename;
    }
    public static void HeavyJavaBenchMain(String dir) throws IOException {

        int RunCount = 10000;

        /*
        RandomStringCreator RSC = new RandomStringCreator();
        ArrayList<String> Words = RSC.GenerateWordArraylist();
        System.out.println(Words);
        System.out.println("Working on creating text...");
        String Text = RSC.CreateText(Words,100000);
        System.out.println("Finished Creating Text.");
        System.out.println("Working on creating word csv...");
        CreateWordsTXT.CreateWordCSV(Words);
        System.out.println("Working on creating word Text txt...");
        CreateWordsTXT.CreateTextTXT(Text);
        System.out.println("Starting testing...");

         */
        Scanner sc= new Scanner(System.in);
        /*
        while(JavaSingleThreadBenchPath == "")
        {
            System.out.println("Give the full path to the JavaSingleThreadBench.jar file, be careful as an incorrect path will fail the multithreaded bench. Path: ");
            JavaSingleThreadBenchPath = sc.nextLine();
        }

         */
        CreateWordsTXT CW = new CreateWordsTXT();
        String Text = CW.TXTtoString();
        ArrayList Words = CW.CSVtoArrayList();
        long avgduration = 0;
        long startTime = System.nanoTime();
        int NumOfTestRuns = 120;
        int UserInputNumOfTestRuns = 120;
        while(UserInputNumOfTestRuns == 120)
        {
            System.out.println("Set number of test runs, the default is 120 and is recommended.\n To keep default type in 0, otherwise pick a number between 1-As many as you would like. Num: ");
            UserInputNumOfTestRuns = sc.nextInt();
        }
        if(UserInputNumOfTestRuns!=0){
            NumOfTestRuns=UserInputNumOfTestRuns;
        }
        System.out.println("Starting single thread testing. ");
        for (int i = 0; i < NumOfTestRuns; i++)
        {
            avgduration+=RunTest(Text,Words,RunCount);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        duration = duration/1000000000;
        avgduration = avgduration/NumOfTestRuns;
        double avgurinmilliseconds = avgduration;
        String SingleThreadOut1 = "Average duration per test run in milliseconds: "+avgurinmilliseconds;
        String SingleThreadOut2 = "Single Threaded testing took "+duration+" seconds.";
        System.out.println(SingleThreadOut1);
        System.out.println(SingleThreadOut2);
        CW.CreateATXTFile();
        CW.WriteToDataTXT("\n-------------New Run-----------------\n");
        CW.WriteToDataTXT(SingleThreadOut1+"\n"+SingleThreadOut2+"\n");
        int threads = Runtime.getRuntime().availableProcessors();
        int cores = threads/2;
        int Cpuburnermode =-1;
        while(Cpuburnermode==-1)
        {
            System.out.println("Select mode by typing one of the numbers. Default Per Core Test(0),Default CPU Burner Test(1),\nSet Amount Of Threads Yourself WARNING May crash your system even at CPU total thread count(2). Num: ");
            Cpuburnermode = sc.nextInt();
            if(Cpuburnermode==1){
                cores = (int)((threads/4)*3);
            }
            if(Cpuburnermode==2){
                System.out.println("Select amount of threads to be used: ");
                cores= sc.nextInt();
            }
        }
        System.out.println("Starting multi-threaded test. Number of threads detected: "+threads);
        String ThreadCount = "Number of threads detected: "+threads;
        CW.WriteToDataTXT("\n"+ThreadCount+"\n");
        String WhereIWantIT = CreateDirectory(dir,"\\StressTestBats");
        for (int i = 0; i <cores; i++)
        {
            String RunME = WriteCMDFile(dir+"\\JavaSingleThreadBench.jar", String.valueOf(i),WhereIWantIT);
            Process runtime = Runtime.getRuntime().exec("cmd.exe /c start call "+RunME);
        }
        System.out.println("All testing complete scores should be available in the TestDataFile.txt (found under your main user folder)");
    }
    public static String CreateDirectory(String path,String name)
    {
        File theDir = new File(path+name);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        return path+name;
    }
    public static long RunTest(String Text, ArrayList<String> Words, int RunCount)
    {
        long AvgDuration = 0;
        StringAnalysis SA = new StringAnalysis();
        RandomStringCreator RSC = new RandomStringCreator();
        for (int i = 0; i < RunCount; i++)
        {
            int TillHalf = (int)RSC.GenRandomNum(0,((Words.size()-1)/2));
            int PastHalf = (int)RSC.GenRandomNum(((Words.size()-1)/2),(Words.size()-1));
            long startTime = System.nanoTime();
            String TestVar = SA.FindMethod(Words.get(TillHalf),Words.get(PastHalf),Text);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            AvgDuration+=duration;
            /*
            if(i%10==0)
            {
                System.out.println(TestVar);
                System.out.println(duration);
            }

             */
        }
        long TotalDuration = AvgDuration/1000000;
        AvgDuration = AvgDuration/RunCount;
        System.out.println("Average duration of each run in nanoseconds: "+AvgDuration);
        System.out.println("Combined duration of the runs in milliseconds: "+ TotalDuration);
        return TotalDuration;

    }

}