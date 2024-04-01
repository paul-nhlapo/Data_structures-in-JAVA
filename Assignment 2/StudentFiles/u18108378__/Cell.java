// public class Cell implements Comparable<Cell> {
//     int databaseRow;
//     String value;

//     public Cell(String string) {

//         this.value = string;

//     }

//     public Cell() {
//         this.value = null;
//     }

//     @Override
//     public int compareTo(Cell o) {
//         return value.compareTo(o.value);
//     }

//     @Override
//     public String toString() {
//         return value + "{" + databaseRow + "}";
//     }
// }
public class Cell implements Comparable<Cell> {
    int databaseRow;
    String value;

    public Cell(int databaseRow, String value) {
        this.databaseRow = databaseRow;
        this.value = value;
    }

    @Override
    public int compareTo(Cell o) {
        return value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object obj) {
        return value.equals(((Cell) obj).value);
    }

    @Override
    public String toString() {
        return value + "{" + databaseRow + "}";
    }

    public int getRow() {
        return this.databaseRow;
    }

    public String getValue() {
        return this.value;
    }
}
