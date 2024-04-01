@SuppressWarnings("unchecked")
public class Database {
    String[][] database;
    String[] columnNames;
    Treap<Cell>[] indexes;

    public Database(String[] cols, int maxSize) {
        columnNames = cols;
        database = new String[maxSize][cols.length];
        indexes = new Treap[cols.length];

        for (int i = 0; i < cols.length; i++) {
            indexes[i] = new Treap<Cell>();
        }

    }

    public void insert(String[] newRowDetails) throws DatabaseException {

        if (newRowDetails.length != columnNames.length) {
            throw DatabaseException.invalidNumberOfColums();
        }

        for (int i = 0; i < newRowDetails.length; i++) {
            if (newRowDetails[i] == null) {
                throw DatabaseException.invalidColumnName(columnNames[i]);
            }
        }

        for (int i = 0; i < database.length; i++) {
            if (database[i][0] == null) {
                database[i] = newRowDetails;
                for (int j = 0; j < indexes.length; j++) {
                    indexes[j].insert(new Cell(newRowDetails[j], i));
                }
                return;
            }
        }

        throw DatabaseException.databaseFull();

    }

    public String[] removeFirstWhere(String col, String data) throws DatabaseException {
        return null;
    }

    public String[][] removeAllWhere(String col, String data) throws DatabaseException {
        return null;
    }

    public String[] findFirstWhere(String col, String data) throws DatabaseException {
        return null;
    }

    public String[][] findAllWhere(String col, String data) throws DatabaseException {
        return null;
    }

    public String[] updateFirstWhere(String col, String updateCondition, String data) throws DatabaseException {
        return null;
    }

    public String[][] updateAllWhere(String col, String updateCondition, String data) throws DatabaseException {
        return null;
    }

    public Treap<Cell> generateIndexOn(String col) throws DatabaseException {
        return null;
    }

    public Treap<Cell>[] generateIndexAll() throws DatabaseException {
        return null;
    }

    public int countOccurences(String col, String data) throws DatabaseException {
        return 0;
    }
}
