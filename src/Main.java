import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException {
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
        String JavaSingleThreadBenchPath = "";
        Scanner sc= new Scanner(System.in);
        while(JavaSingleThreadBenchPath == "")
        {
            System.out.println("Give the full path to the JavaSingleThreadBench.jar file, be careful as an incorrect path will fail the multithreaded bench. Path: ");
            JavaSingleThreadBenchPath = sc.nextLine();
        }
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
        for (int i = 0; i <cores; i++)
        {
            Process runtime = Runtime.getRuntime().exec("java -jar "+JavaSingleThreadBenchPath);
        }
        System.out.println("All testing complete scores should be available in the TestDataFile.txt (found under your main user folder)");
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