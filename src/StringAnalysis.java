import java.util.ArrayList;

public class StringAnalysis
{
    public static String FindMethod(String FindMeStart,String FindMeEnd,String FindMeWhere)//inclusive on the start exlcusive on the end TODO add a loop which finds all matching ones think of some kind of removing system of the past ones
    {
        int FoundStart = FindMeWhere.indexOf(FindMeStart);
        if(FoundStart==-1) {
            FoundStart = 0;
        }
        String y = FindMeWhere.substring(FoundStart);
        int FoundEnd = y.indexOf(FindMeEnd);
        if(FindMeEnd == "")
            FoundEnd = y.length()-1;
        if(FoundEnd==-1) {
            FoundEnd = y.length() - 1;
        }
        //System.out.println(FoundStart+","+FoundEnd);
        return FindMeWhere.substring(FoundStart,FoundStart+FoundEnd);
    }
    public static String FindMethod(String FindMeStart,String FindMeEnd,String FindMeWhere,boolean EndInclusive)//inclusive on the start exlcusive on the end TODO add a loop which finds all matching ones think of some kind of removing system of the past ones
    {
        int FoundStart = FindMeWhere.indexOf(FindMeStart);
        String y = FindMeWhere.substring(FoundStart);
        int FoundEnd = y.indexOf(FindMeEnd);
        if(FindMeEnd == "")
            FoundEnd = y.length()-1;
        return FindMeWhere.substring(FoundStart+FindMeStart.length(),FoundStart+FoundEnd+1);
    }
    public static ArrayList FindAllInstancesMethod(String FindMeStart,String FindMeEnd,String FindMeWhere)//exclusive on both start and end
    {
        ArrayList<String> OutArray = new ArrayList<String>();
        int FoundStart = FindMeWhere.indexOf(FindMeStart);
        while(FoundStart != -1)
        {
            String y ="RandomSetOfChars";
            int FoundEnd = -1;
            FoundStart = FindMeWhere.indexOf(FindMeStart);
            if (FoundStart != -1)
            {
                y = FindMeWhere.substring(FoundStart);
            }
            else{
                break;
            }
            FoundEnd = y.indexOf(FindMeEnd);
            if (FoundEnd != -1)
            {
                OutArray.add(FindMeWhere.substring(FoundStart,FoundStart+FoundEnd));
            }
            else{
                break;
            }
            FindMeWhere = FindMeWhere.substring(FoundStart+FoundEnd);
        }
        return OutArray;
    }
}