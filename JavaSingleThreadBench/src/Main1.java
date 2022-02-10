import java.io.IOException;
import java.util.ArrayList;

public class Main1
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
        CreateWordsTXT CW = new CreateWordsTXT();
        String Text = CW.TXTtoString();
        ArrayList Words = CW.CSVtoArrayList();
        long avgduration = 0;
        long startTime = System.nanoTime();
        int NumOfTestRuns = 30;
        for (int i = 0; i < NumOfTestRuns; i++)
        {
            avgduration+=RunTest(Text,Words,RunCount);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        duration = duration/1000000;
        avgduration = avgduration/NumOfTestRuns;
        double avgurinmilliseconds = avgduration;
        String SingleThreadOut1 = "Average during multi-thread test per test run in milliseconds: "+avgurinmilliseconds;
        String SingleThreadOut2 = "This threads testing in milliseconds took: "+duration;
        System.out.println(SingleThreadOut1);
        System.out.println(SingleThreadOut2);
        CW.WriteToDataTXT("\n"+SingleThreadOut1+"\n"+SingleThreadOut2+"\n");

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
