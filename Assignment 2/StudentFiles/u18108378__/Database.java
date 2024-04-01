@SuppressWarnings("unchecked")
public class Database {
    String[][] database;
    String[] columnNames;
    Treap<Cell>[] indexes;
    int maxSize;
    int currentSize;

    public Database(String[] cols, int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.columnNames = cols;
        this.database = new String[maxSize][cols.length];
        this.indexes = new Treap[cols.length];
    }

    public void insert(String[] newRowDetails) throws DatabaseException {
        if (newRowDetails.length != columnNames.length) {
            throw DatabaseException.invalidNumberOfColums();
        }
        if (currentSize == maxSize) {
            throw DatabaseException.databaseFull();
        }
        for (int i = 0; i < newRowDetails.length; i++) {
            database[currentSize][i] = newRowDetails[i];
            if (indexes[i] != null) {
                Cell cell = new Cell(i, null);
                cell.databaseRow = currentSize;
                cell.value = newRowDetails[i];
                try {
                    indexes[i].insert(cell);
                } catch (DatabaseException e) {
                    System.out.println(e);
                }
            }
        }
        currentSize++;
    }

    public String[] removeFirstWhere(String col, String data) throws DatabaseException {
        int colIndex = -1;
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].equals(col)) {
                colIndex = i;
                break;
            }
        }
        if (colIndex == -1) {
            throw DatabaseException.invalidColumnName(col);
        }

        int rowIndex = -1;
        if (indexes[colIndex] != null) {
            Node<Cell> node = indexes[colIndex].access(new Cell(rowIndex, data));
            if (node != null) {
                rowIndex = node.data.databaseRow;
            }
        } else {
            for (int i = 0; i < currentSize; i++) {
                if (database[i][colIndex].equals(data)) {
                    rowIndex = i;
                    break;
                }
            }
        }

        if (rowIndex == -1) {
            return new String[0];
        }

        String[] removedRow = new String[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            removedRow[i] = database[rowIndex][i];
            if (indexes[i] != null) {
                indexes[i].remove(new Cell(i, database[rowIndex][i]));
            }
            for (int j = rowIndex; j < currentSize - 1; j++) {
                database[j][i] = database[j + 1][i];
            }
            database[currentSize - 1][i] = null;
        }

        currentSize--;
        return removedRow;

    }

    // One
    public String[][] removeAllWhere(String col, String data) throws DatabaseException {
        String[][] removedRows = new String[0][columnNames.length];
        int numRowsRemoved = 0;
        String[] removedRow;
        do {
            removedRow = removeFirstWhere(col, data);
            if (removedRow.length > 0) {
                numRowsRemoved++;
                String[][] newRemovedRows = new String[numRowsRemoved][columnNames.length];
                for (int i = 0; i < numRowsRemoved - 1; i++) {
                    newRemovedRows[i] = removedRows[i];
                }
                newRemovedRows[numRowsRemoved - 1] = removedRow;
                removedRows = newRemovedRows;
            }
        } while (removedRow.length > 0);
        return removedRows;
    }

    public String[] findFirstWhere(String col, String data) throws DatabaseException {
        int colIndex = -1;
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].equals(col)) {
                colIndex = i;
                break;
            }
        }
        if (colIndex == -1) {
            throw DatabaseException.invalidColumnName(col);
        }

        if (indexes[colIndex] != null) {
            Node<Cell> node = indexes[colIndex].access(new Cell(colIndex, data));
            if (node != null) {
                return database[node.data.databaseRow];
            }
        } else {
            for (String[] row : database) {
                if (row[colIndex].equals(data)) {
                    return row;
                }
            }
        }

        return new String[0];
    }

    public String[][] findAllWhere(String col, String data) throws DatabaseException {
        int colIndex = -1;
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].equals(col)) {
                colIndex = i;
                break;
            }
        }
        if (colIndex == -1) {
            throw DatabaseException.invalidColumnName(col);
        }

        String[][] foundRows = new String[0][columnNames.length];
        int numRowsFound = 0;
        if (indexes[colIndex] != null) {
            numRowsFound = findAllWhere(indexes[colIndex].root, colIndex, data, foundRows);
        } else {
            for (int i = 0; i < currentSize; i++) {
                if (database[i][colIndex].equals(data)) {
                    numRowsFound++;
                    String[][] newFoundRows = new String[numRowsFound][columnNames.length];
                    for (int j = 0; j < numRowsFound - 1; j++) {
                        newFoundRows[j] = foundRows[j];
                    }
                    newFoundRows[numRowsFound - 1] = database[i];
                    foundRows = newFoundRows;
                }
            }
        }

        return foundRows;
    }

    private int findAllWhere(Node<Cell> node, int colIndex, String data, String[][] foundRows) {
        if (node == null) {
            return 0;
        }
        int numRowsFound = 0;
        int cmp = data.compareTo(node.data.value);
        if (cmp == 0) {
            numRowsFound++;
            String[][] newFoundRows = new String[numRowsFound][columnNames.length];
            for (int j = 0; j < numRowsFound - 1; j++) {
                newFoundRows[j] = foundRows[j];
            }
            newFoundRows[numRowsFound - 1] = database[node.data.databaseRow];
            foundRows = newFoundRows;
            numRowsFound += findAllWhere(node.left, colIndex, data, foundRows);
            numRowsFound += findAllWhere(node.right, colIndex, data, foundRows);
        } else if (cmp < 0) {
            numRowsFound += findAllWhere(node.left, colIndex, data, foundRows);
        } else {
            numRowsFound += findAllWhere(node.right, colIndex, data, foundRows);
        }
        return numRowsFound;
    }

    public String[] updateFirstWhere(String col, String updateCondition, String data) throws DatabaseException {
        int colIndex = -1;
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].equals(col)) {
                colIndex = i;
                break;
            }
        }
        if (colIndex == -1) {
            throw DatabaseException.invalidColumnName(col);
        }

        int rowIndex = -1;
        if (indexes[colIndex] != null) {
            Node<Cell> node = indexes[colIndex].access(new Cell(rowIndex, updateCondition));
            if (node != null) {
                rowIndex = node.data.databaseRow;
                indexes[colIndex].remove(node.data);
                node.data.value = data;
                try {
                    indexes[colIndex].insert(node.data);
                } catch (DatabaseException e) {
                    System.out.println(e);
                }
            }
        } else {
            for (int i = 0; i < currentSize; i++) {
                if (database[i][colIndex].equals(updateCondition)) {
                    rowIndex = i;
                    break;
                }
            }
        }

        if (rowIndex == -1) {
            return new String[0];
        }

        database[rowIndex][colIndex] = data;
        return database[rowIndex];
    }

    // Two
    public String[][] updateAllWhere(String col, String updateCondition, String data) throws DatabaseException {
        String[][] updatedRows = new String[0][columnNames.length];
        int numRowsUpdated = 0;
        String[] updatedRow;
        do {
            updatedRow = updateFirstWhere(col, updateCondition, data);
            if (updatedRow.length > 0) {
                numRowsUpdated++;
                String[][] newUpdatedRows = new String[numRowsUpdated][columnNames.length];
                for (int i = 0; i < numRowsUpdated - 1; i++) {
                    newUpdatedRows[i] = updatedRows[i];
                }
                newUpdatedRows[numRowsUpdated - 1] = updatedRow;
                updatedRows = newUpdatedRows;
            }
        } while (updatedRow.length > 0);
        return updatedRows;
    }

    public Treap<Cell> generateIndexOn(String col) throws DatabaseException {
        int colIndex = -1;
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].equals(col)) {
                colIndex = i;
                break;
            }
        }
        if (colIndex == -1) {
            throw DatabaseException.invalidColumnName(col);
        }

        if (indexes[colIndex] == null) {
            indexes[colIndex] = new Treap<>();
            for (int i = 0; i < currentSize; i++) {
                Cell cell = new Cell(i, col);
                cell.databaseRow = i;
                cell.value = database[i][colIndex];
                try {
                    indexes[colIndex].insert(cell);
                } catch (DatabaseException e) {
                    indexes[colIndex] = null;
                    throw e;
                }
            }
        }

        return indexes[colIndex];
    }

    public Treap<Cell>[] generateIndexAll() throws DatabaseException {
        for (int i = 0; i < columnNames.length; i++) {
            try {
                generateIndexOn(columnNames[i]);
            } catch (DatabaseException e) {
                // Swallow exception and continue to next index
            }
        }
        return indexes;
    }

    public int countOccurences(String col, String data) throws DatabaseException {
        int colIndex = -1;
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].equals(col)) {
                colIndex = i;
                break;
            }
        }
        if (colIndex == -1) {
            throw DatabaseException.invalidColumnName(col);
        }

        int count = 0;
        if (indexes[colIndex] != null) {
            count = countOccurences(indexes[colIndex].root, data);
        } else {
            for (int i = 0; i < currentSize; i++) {
                if (database[i][colIndex].equals(data)) {
                    count++;
                }
            }
        }

        return count;

    }

    private int countOccurences(Node<Cell> node, String data) {
        if (node == null) {
            return 0;
        }
        int cmp = data.compareTo(node.data.value);
        if (cmp == 0) {
            return 1 + countOccurences(node.left, data) + countOccurences(node.right, data);
        } else if (cmp < 0) {
            return countOccurences(node.left, data);
        } else {
            return countOccurences(node.right, data);
        }
    }

}
