public class InsertSort extends Sorting {

    public InsertSort(){
        this.name = "InsertSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) {
        String[] JumpSort = new String[array.length];
        for (int i = 1, k; i < array.length; i++) {
        Vertex tmp = array[i];
        for (k = i; k > 0 && tmp.getVName().compareTo(array[k - 1].getVName()) < 0; k--) {
              array[k] = array[k - 1];
            }
            array[k] = tmp;
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
        for (int i = 1, k; i < array.length; i++) {
            Vertex tmp = array[i];
            for (k = i; k > 0 && tmp.getVName().compareTo(array[k - 1].getVName()) > 0; k--) {
                  array[k] = array[k - 1];
                }
                array[k] = tmp;
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
}
