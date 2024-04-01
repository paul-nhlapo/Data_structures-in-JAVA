public class Cell implements Comparable<Cell> {
    int databaseRow;
    String value;

    @Override
    public int compareTo(Cell o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return value + "{" + databaseRow + "}";
    }

    public Cell(String value, int databaseRow) {
        this.value = value;
        this.databaseRow = databaseRow;
    }

    public int getDatabaseRow() {
        return databaseRow;
    }

    public String getValue() {
        return value;
    }

    public void setDatabaseRow(int databaseRow) {
        this.databaseRow = databaseRow;
    }
}
