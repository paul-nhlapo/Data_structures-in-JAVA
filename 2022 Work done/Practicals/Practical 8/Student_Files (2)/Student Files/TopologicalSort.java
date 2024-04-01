public class TopologicalSort extends Sorting {
    public TopologicalSort(){
    }

    @Override
    public String[] sortAcs(Vertex[] array) throws CycleException {
        String[] JumpSort = new String[array.length];
        return JumpSort;
    }

    @Override
    public String[] sortDsc(Vertex[] array) throws CycleException{
        String[] JumpSort = new String[array.length];
        return JumpSort;
    }

}

class CycleException extends Exception{
    public String message = "Cycle has been detected";
}
