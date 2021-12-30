import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Algorithm
{
    public static long times;
    public static Map<String, int[]> aStar(int[] current)
    {
        PriorityQueue<State> q = new PriorityQueue<>();
        Map<String, Integer> dist = new HashMap<>();
        Map<String, int[]> parent = new HashMap<>();
        times = 0;
        dist.put(stringify(current), 0);
        q.add(new State(current, 0));
        while (!q.isEmpty())
        {
            State cur = q.poll();
            times++;
            if (Arrays.equals(cur.getBoard(), Control.Ans)) break;
            for (State child : cur.getNextStates())
            {
                if (dist.getOrDefault(stringify(child.getBoard()), Integer.MAX_VALUE) > child.getCost())
                {
                    parent.put(stringify(child.getBoard()), cur.getBoard());
                    dist.put(stringify(child.getBoard()), child.getCost());
                    q.add(child);
                }
            }
        }
        return parent;
    }
    public static String stringify(int[] arr)
    {
        String str = "";
        for (int i = 0; i < arr.length; i++) str += String.valueOf(arr[i]);
        return str;
    }
}
