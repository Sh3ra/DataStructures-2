package eg.edu.alexu.csd.filestructure.sort.SortingAlgorithms;

import java.util.ArrayList;

public class BubbleSort {
    public void sort(ArrayList a)
    {
        for(int i=0;i<a.size();i++)
        {
            for (int j=0;j<a.size()-1;j++)
            {
                if((int)a.get(j)>(int)a.get(j+1))
                {
                    Object temp=a.get(j);
                    a.set(j,a.get(j+1));
                    a.set(j+1,temp);
                }
            }
        }
    }
}
