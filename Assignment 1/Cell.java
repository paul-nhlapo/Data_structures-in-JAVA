public class Cell {
    public int numRows, numCols, r, c, b;
    public Cell below, right, block;
    public Integer value;
    public List<Integer> possibleValues;

    public String toString() {
        if (value == null) {

            String res = " ";
            for (int i = 0; i < String.valueOf(numRows * numCols).length(); i++) {
                res += "-";
            }
            res += " ";
            return res;
        }
        return " " + String.format("%" + String.valueOf(numRows * numCols).length() + "d", value).replace(" ", "0")
                + " ";
    }

    /*
     * Don't change anything above this line
     */

    public Cell(int nR, int nC, String inp) {
        numCols = nC;
        numRows = nR;
        possibleValues = new List<Integer>();
        if (inp.equals("-")) {
            value = null;
            for (int i = 1; i <= numRows * numCols; i++) {
                possibleValues.append(i);
            }
        } else {
            value = Integer.parseInt(inp);
            possibleValues = null;
        }
        below = null;
        right = null;
        block = null;

    }

    public void removeVal(int val) {
        if (possibleValues != null) {
            possibleValues.remove(val);
        } else {
            return;
        }
    }

    public void setVal(int val) {
        value = val;
    }

}
