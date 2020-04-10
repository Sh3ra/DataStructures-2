package eg.edu.alexu.csd.filestructure.sort.SortingAlgorithms;

import java.util.ArrayList;

public class BubbleSort <T extends Comparable<T>>{
    public void sort(ArrayList<T> a)
    {
        for(int i=0;i<a.size();i++)
        {
            for (int j=0;j<a.size()-1;j++)
            {
                if(a.get(j).compareTo(a.get(j+1)) > 0)
                {
                    T temp=a.get(j);
                    a.set(j,a.get(j+1));
                    a.set(j+1,temp);
                }
            }
        }
    }
}
