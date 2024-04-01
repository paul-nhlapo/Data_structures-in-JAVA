public class CombSort extends Sorting {
    public CombSort(){
        this.name ="CombSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) {
        String[] JumpSort = new String[array.length];
        int n = array.length;
 
        int gap = n;
 
        boolean swapped = true;
 
        while (gap != 1 || swapped == true)
        {
            gap = getNextGap(gap);
 
            swapped = false;
 
            for (int i=0; i<n-gap; i++)
            {
                if (array[i].getVName().compareTo(array[i+gap].getVName()) > 0)
                {
                    Vertex temp = array[i];
                    array[i] = array[i+gap];
                    array[i+gap] = temp;
 
                    swapped = true;
                }
                
            }
            for(int p = 0; p<array.length; p++){
                JumpSort[p] = array[p].getVName();
                if(p<array.length-1){
                   System.out.print(JumpSort[p]+ ";"); 
                }
                else{
                   System.out.print(JumpSort[p]); 
                }
            }
            System.out.println(" ");
        }
        return JumpSort;
    }

    @Override
    public String[] sortDsc(Vertex[] array) {
        String[] JumpSort = new String[array.length];
        int n = array.length;
 
        int gap = n;
 
        boolean swapped = true;
 
        while (gap != 1 || swapped == true)
        {
            gap = getNextGap(gap);
 
           
            swapped = false;
 
            for (int i=0; i<n-gap; i++)
            {
                if (array[i].getVName().compareTo(array[i+gap].getVName()) < 0)
                {
                    Vertex temp = array[i];
                    array[i] = array[i+gap];
                    array[i+gap] = temp;
 
                    swapped = true;
                }
                
            }
            for(int p = 0; p<array.length; p++){
                JumpSort[p] = array[p].getVName();
                if(p<array.length-1){
                   System.out.print(JumpSort[p]+ ";"); 
                }
                else{
                   System.out.print(JumpSort[p]); 
                }
            }
            System.out.println(" ");
        }
        return JumpSort;
    }

    int getNextGap(int gap)
    {
        gap = (gap*10)/13;
    
        if (gap < 1)
            return 1;
        return gap;
    }
}
