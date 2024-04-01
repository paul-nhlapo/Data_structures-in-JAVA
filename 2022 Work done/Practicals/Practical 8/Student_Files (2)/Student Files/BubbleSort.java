public class BubbleSort extends Sorting {
    public BubbleSort(){
        this.name ="BubbleSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) {
        String[] JumpSort = new String[array.length];
        for (int i = 0, k; i < array.length - 1; i++) {
            for (k = array.length - 1; k > i; --k) {
              if (array[k].getVName().compareTo(array[k - 1].getVName()) > 0) {
                swap(array, k, k - 1);
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
        for (int i = 0, k; i < array.length - 1; i++) {
            for (k = array.length - 1; k > i; --k) {
              if (array[k].getVName().compareTo(array[k - 1].getVName()) < 0) {
                swap(array, k, k - 1);
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
    private void swap(Vertex[] input, int a, int b) {
        Vertex tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
      }
}
