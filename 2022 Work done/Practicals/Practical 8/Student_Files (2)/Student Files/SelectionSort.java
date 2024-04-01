public class SelectionSort extends Sorting {
    
    public SelectionSort(){
        this.name ="SelectionSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) {
        String[] JumpSort = new String[array.length];
        for (int i = 0, k, least; i < array.length; i++) {
            for (k = i + 1, least = i; k < array.length; k++) {
              if (array[k].getVName().compareTo(array[least].getVName()) < 0) {
                least = k;
              }
            }
            swap(array, least, i);
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
        for (int i = 0, k, least; i < array.length; i++) {
            for (k = i + 1, least = i; k < array.length; k++) {
              if (array[k].getVName().compareTo(array[least].getVName()) > 0) {
                least = k;
              }
            }
            swap(array, least, i);
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
    private void swap(Vertex[] array, int a, int b) {
        Vertex tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
      }
}
