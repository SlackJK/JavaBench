import java.util.ArrayList;

public class RandomStringCreator
{
    public ArrayList<String> GenerateWordArraylist()
    {

        ArrayList<String> OutArrayList = new ArrayList<String>(100);
        for(int i = 0; i < 100;i++)
        {
            OutArrayList.add(GenRandomWord(2,10));
        }
        return OutArrayList;
    }
    public int GenRandomNum(int min, int max)
    {
        int OutInt = (int)(Math.random()*(max-min+1)+min);  ;
        return OutInt;
    }
    private String GenRandomChar()
    {
        String OutString ="";
        String Alphabet = "qwertyuiopasdfghjklzxcvbnmn";
        int x = GenRandomNum(0,25);
        OutString += Alphabet.substring(x,x+1);
        return OutString;
    }
    private String GenRandomWord(int min,int max)
    {
        String OutString ="";
        for (int i = 0; i < GenRandomNum(min, max); i++)
        {
            OutString += GenRandomChar();
        }
        return OutString;
    }
    public String CreateText(ArrayList<String> Words,int WordCount)
    {
        String OutString = "";
        for (int i = 0; i < WordCount; i++)
        {
            OutString += (Words.get(GenRandomNum(0,Words.size()-1))+" ");
            /*
            if(i%100==0)
            {
                System.out.println(OutString);
            }

             */
        }
        return OutString;
    }
}
