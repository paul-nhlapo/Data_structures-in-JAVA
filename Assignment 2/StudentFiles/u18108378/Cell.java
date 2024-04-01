public class Cell implements Comparable<Cell> {
    int databaseRow;
    String value;

    public Cell(String string) {

        this.value = string;

    }

    public Cell() {
        this.value = null;
    }

    @Override
    public int compareTo(Cell o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return value + "{" + databaseRow + "}";
    }
}
