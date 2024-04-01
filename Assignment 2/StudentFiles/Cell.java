public class Cell implements Comparable<Cell> {
    int databaseRow;
    String value;

    public Cell(String string) {

        this.value = string;

    }

    public Cell() {
        this.value = null;
    }

    public Cell(int databaseRow, String value) {
        this.databaseRow = databaseRow;
        this.value = value;
    }

    @Override
    public int compareTo(Cell other) {
        return value.compareTo(other.value);
    }

    // @Override
    // public int compareTo(Cell o) {
    // return value.compareTo(o.value);
    // }

    @Override
    public String toString() {
        return value + "{" + databaseRow + "}";
    }
}
