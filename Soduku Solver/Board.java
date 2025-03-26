public class Board {
    private int numRows, numCols;
    private Cell cells[], rows[], cols[], blocks[];

    public String toString() {
        String res = "";
        for (int r = 0; r < numRows * numCols; r++) {
            if (r % numRows == 0) {
                res += horizLine() + "\n";
            }
            for (int c = 0; c < numRows * numCols; c++) {
                if (c % numCols == 0) {
                    res += "|";
                }
                res += cells[r * numRows * numCols + c];
            }
            res += "|\n";
        }

        res += horizLine();
        return res;
    }

    public String horizLine() {
        String res = "";
        for (int i = 0; i < numRows + 1 + (numRows * numCols * (String.valueOf(numRows * numCols).length() + 2)); i++) {
            res += "-";
        }
        return res;
    }

    public void testLinks() {
        System.out.println("Rows forward");

        for (int r = 0; r < numRows * numCols; r++) {
            System.out.print("Row " + r + "\t");
            Cell ptr = rows[r];
            while (ptr != null) {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.right;
            }
            System.out.println();
        }

        System.out.println("Cols forward");

        for (int c = 0; c < numRows * numCols; c++) {
            System.out.print("Col " + c + "\t");
            Cell ptr = cols[c];
            while (ptr != null) {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.below;
            }
            System.out.println();
        }

        System.out.println("Blocks");
        for (int b = 0; b < numRows * numCols; b++) {
            System.out.print("Block " + b + "\t");
            Cell ptr = blocks[b];
            while (ptr != null) {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.block;
            }
            System.out.println();
        }
    }

    public void testCells() {
        System.out.println("Cell\tCoord\tBlock\ttoString");
        for (Cell c : cells) {
            System.out.println(indexOf(c) + "\t(" + c.r + "," + c.c + ")\t" + c.b + "\t" + c);
        }
    }

    public int indexOf(Cell c) {
        for (int i = 0; i < numRows * numRows * numCols * numCols; i++) {
            if (cells[i].equals(c)) {
                return i;
            }
        }
        return -1;
    }

    public Cell cellAt(int r, int c) {
        if (r < 0 || r >= numRows * numCols || c < 0 || c >= numRows * numCols) {
            return null;
        }
        return cells[r * numRows * numCols + c];
    }

    /*
     * Don't change anything above this line
     */

    public Board(int r, int c, String boardString) {
        numRows = r;
        numCols = c;
        cells = new Cell[(numRows * numCols) * (numRows * numCols)];
        String[] boardStringArray = boardString.split(" ");

        int k = 0;
        for (int i = 0; i < numRows * numCols; i++) {
            for (int j = 0; j < numRows * numCols; j++) {
                cells[k] = new Cell(r, c, boardStringArray[k]);
                cells[k].r = i;
                cells[k++].c = j;
            }

        }

        for (int i = 0; i < numRows * numCols; i++) {
            for (int j = 0; j < numRows * numCols; j++) {
                cells[i * numRows * numCols + j].r = i;
                cells[i * numRows * numCols + j].c = j;
            }
        }

        setLinks();

    }

    public void setLinks() {
        rows = new Cell[numRows * numCols];
        cols = new Cell[numRows * numCols];
        blocks = new Cell[numRows * numCols];

        for (int i = 0; i < numRows * numCols; i++) {
            rows[i] = cells[i * numRows * numCols + 0];
            cols[i] = cells[0 * numRows * numCols + i];
        }
        for (int i = 0; i < numRows * numCols; i++) {
            Cell ptr = rows[i];
            Cell ptrCols = cols[i];
            for (int j = 0; j < numRows * numCols; j++) {
                // rows[i] = cells[i * numRows * numCols + j];
                if (j < numRows * numCols) {
                    ptr.right = cells[i * numRows * numCols + j];
                    ptrCols.below = cells[j * numRows * numCols + i];

                } else {
                    ptr.right = null;
                    ptrCols.below = null;

                }
                ptr = ptr.right;
                ptrCols = ptrCols.below;
            }
        }

        int blockIndex = 0;
        for (int i = 0; i < numRows * numCols; i += numRows) {
            for (int j = 0; j < numRows * numCols; j += numCols) {
                Cell ptr = cells[i * numRows * numCols + j];
                blocks[blockIndex] = ptr;
                for (int r = i; r < i + numRows; r++) {
                    for (int c = j; c < j + numCols; c++) {
                        if (r != i || c != j) {
                            ptr.block = cells[r * numRows * numCols + c];
                            ptr = ptr.block;
                        } else {
                            ptr.block = null;
                        }
                    }
                }
                blockIndex++;
            }
        }
        for (int i = 0; i < numRows * numCols; i += 1) {
            Cell ptr = blocks[i];
            while (ptr != null) {
                ptr.b = i;
                ptr = ptr.block;
            }
        }

    }

    public void fullProp() {
        for (Cell cell : cells) {
            propCell(cell);
        }
    }

    public void propCell(Cell cell) {
        if (cell == null || cell.value == null) {
            return;
        } else {

            if (cell.value != null) {
                Cell rowPtr = rows[cell.r];
                while (rowPtr != null) {
                    rowPtr.removeVal(cell.value);
                    rowPtr = rowPtr.right;
                }

                Cell colPtr = cols[cell.c];
                while (colPtr != null) {
                    colPtr.removeVal(cell.value);
                    colPtr = colPtr.below;
                }

                Cell blockPtr = blocks[cell.b];
                while (blockPtr != null) {
                    blockPtr.removeVal(cell.value);
                    blockPtr = blockPtr.block;
                }
            }
        }

    }

    public void solve() {
        int counter = 0;
        while (soleCandidate() || uniqueCandidate() || duplicateCells()) {
            counter++;
        }
        System.out.println("Number of moves: " + counter);
    }

    public boolean soleCandidate() {
        for (Cell c : cells) {
            if (c.possibleValues != null && c.possibleValues.length == 1) {
                Node<Integer> value = c.possibleValues.head;
                c.setVal(value.data);
                propCell(c);
                return true;
            }
        }
        return false;
    }

    public boolean uniqueCandidate() {
        for (Cell row : rows) {
            int counts[] = new int[numRows * numCols];
            for (int i = 0; i < numRows * numCols; i++) {
                counts[i] = 0;
            }
            Cell rowPtr = row;
            while (rowPtr != null) {
                if (rowPtr.possibleValues != null) {
                    Node<Integer> nodePtr = rowPtr.possibleValues.head;
                    while (nodePtr != null) {
                        counts[nodePtr.data - 1]++;
                        nodePtr = nodePtr.next;
                    }
                }
                rowPtr = rowPtr.right;
            }
            for (int i = 0; i < numRows * numCols; i++) {
                if (counts[i] == 1) {
                    rowPtr = row;
                    while (rowPtr != null) {
                        if (rowPtr.possibleValues != null && rowPtr.possibleValues.contains(i + 1)) {
                            rowPtr.setVal(i + 1);
                            propCell(rowPtr);
                        }
                        rowPtr = rowPtr.right;
                    }
                    return true;
                }
            }
        }

        for (Cell col : cols) {
            int counts[] = new int[numRows * numCols];
            for (int i = 0; i < numRows * numCols; i++) {
                counts[i] = 0;
            }
            Cell colPtr = col;
            while (colPtr != null) {
                if (colPtr.possibleValues != null) {
                    Node<Integer> nodePtr = colPtr.possibleValues.head;
                    while (nodePtr != null) {
                        counts[nodePtr.data - 1]++;
                        nodePtr = nodePtr.next;
                    }
                }
                colPtr = colPtr.below;
            }
            for (int i = 0; i < numRows * numCols; i++) {
                if (counts[i] == 1) {
                    colPtr = col;
                    while (colPtr != null) {
                        if (colPtr.possibleValues != null && colPtr.possibleValues.contains(i + 1)) {
                            colPtr.setVal(i + 1);
                            propCell(colPtr);
                        }
                        colPtr = colPtr.below;
                    }
                    return true;
                }
            }
        }

        for (Cell block : blocks) {
            int counts[] = new int[numRows * numCols];
            for (int i = 0; i < numRows * numCols; i++) {
                counts[i] = 0;
            }
            Cell blockPtr = block;
            while (blockPtr != null) {
                if (blockPtr.possibleValues != null) {
                    Node<Integer> nodePtr = blockPtr.possibleValues.head;
                    while (nodePtr != null) {
                        counts[nodePtr.data - 1]++;
                        nodePtr = nodePtr.next;
                    }
                }
                blockPtr = blockPtr.block;
            }
            for (int i = 0; i < numRows * numCols; i++) {
                if (counts[i] == 1) {
                    blockPtr = block;
                    while (blockPtr != null) {
                        if (blockPtr.possibleValues != null && blockPtr.possibleValues.contains(i + 1)) {
                            blockPtr.setVal(i + 1);
                            propCell(blockPtr);
                        }
                        blockPtr = blockPtr.block;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean duplicateCells() {
        for (Cell row : rows) {
            Cell rowPtr = row;
            while (rowPtr != null) {
                if (rowPtr.possibleValues != null && rowPtr.possibleValues.length == 2) {
                    Cell secondPtr = rowPtr.right;
                    while (secondPtr != null) {
                        if (rowPtr.possibleValues.equals(secondPtr.possibleValues) == true) {
                            Cell thirdPtr = row;
                            boolean change = false;
                            while (thirdPtr != null) {
                                if (thirdPtr.equals(secondPtr) == false && thirdPtr.equals(rowPtr) == false
                                        && thirdPtr.possibleValues != null) {
                                    change = change || (thirdPtr.possibleValues.remove(rowPtr.possibleValues));
                                }
                                thirdPtr = thirdPtr.right;
                            }
                            if (change == true) {
                                return true;
                            }
                        }
                        secondPtr = secondPtr.right;
                    }
                }
                rowPtr = rowPtr.right;
            }
        }

        for (Cell col : cols) {
            Cell colPtr = col;
            while (colPtr != null) {
                if (colPtr.possibleValues != null && colPtr.possibleValues.length == 2) {
                    Cell secondPtr = colPtr.below;
                    while (secondPtr != null) {
                        if (colPtr.possibleValues.equals(secondPtr.possibleValues) == true) {
                            Cell thirdPtr = col;
                            boolean change = false;
                            while (thirdPtr != null) {
                                if (thirdPtr.equals(secondPtr) == false && thirdPtr.equals(colPtr) == false
                                        && thirdPtr.possibleValues != null) {
                                    change = change || (thirdPtr.possibleValues.remove(colPtr.possibleValues));
                                }
                                thirdPtr = thirdPtr.below;
                            }
                            if (change == true) {
                                return true;
                            }
                        }
                        secondPtr = secondPtr.below;
                    }
                }
                colPtr = colPtr.below;
            }
        }

        for (Cell block : blocks) {
            Cell blockPtr = block;
            while (blockPtr != null) {
                if (blockPtr.possibleValues != null && blockPtr.possibleValues.length == 2) {
                    Cell secondPtr = blockPtr.block;
                    while (secondPtr != null) {
                        if (blockPtr.possibleValues.equals(secondPtr.possibleValues) == true) {
                            Cell thirdPtr = block;
                            boolean change = false;
                            while (thirdPtr != null) {
                                if (thirdPtr.equals(secondPtr) == false && thirdPtr.equals(blockPtr) == false
                                        && thirdPtr.possibleValues != null) {
                                    change = change || (thirdPtr.possibleValues.remove(blockPtr.possibleValues));
                                }
                                thirdPtr = thirdPtr.block;
                            }
                            if (change == true) {
                                return true;
                            }
                        }
                        secondPtr = secondPtr.block;
                    }
                }
                blockPtr = blockPtr.block;
            }
        }
        return false;
    }

    // public void solve() {
    // int counter = 0;
    // while (soleCandidate() || uniqueCandidate() || duplicateCells()) {
    // counter++;
    // }
    // System.out.println("Number of moves: " + counter);
    // }

    // public boolean soleCandidate() {
    // for (Cell c : cells) {
    // if (c.possibleValues != null && c.possibleValues.length == 1) {
    // int value = c.possibleValues.head.data;
    // c.setVal(value);
    // propCell(c);
    // return true;
    // }
    // }
    // return false;
    // }

    // public boolean uniqueCandidate() {

    // for (Cell row : rows) {
    // int[] counts = new int[numRows * numCols];
    // for (int y = 0; y < counts.length; y++) {
    // counts[y] = 0;
    // }
    // Cell rowPtr = row;
    // while (rowPtr != null) {
    // if (rowPtr.possibleValues != null) {
    // Node<Integer> nodePtr = rowPtr.possibleValues.head;
    // while (nodePtr != null) {
    // counts[nodePtr.data - 1]++;
    // nodePtr = nodePtr.next;
    // }
    // }
    // rowPtr = rowPtr.right;
    // }
    // for (int i = 0; i < numRows * numCols; i++) {
    // if (counts[i] == 1) {
    // rowPtr = row;
    // while (rowPtr != null) {
    // if (rowPtr.possibleValues != null && rowPtr.possibleValues.contains(i + 1)) {
    // rowPtr.setVal(i + 1);
    // propCell(rowPtr);
    // }
    // rowPtr = rowPtr.right;
    // }
    // return true;
    // }
    // }
    // }
    // // For cols
    // for (Cell col : cols) {
    // int[] counts = new int[numRows * numCols];
    // for (int y = 0; y < counts.length; y++) {
    // counts[y] = 0;
    // }
    // Cell colPtr = col;
    // while (colPtr != null) {
    // if (colPtr.possibleValues != null) {
    // Node<Integer> nodePtr = colPtr.possibleValues.head;
    // while (nodePtr != null) {
    // counts[nodePtr.data - 1]++;
    // nodePtr = nodePtr.next;
    // }
    // }
    // colPtr = colPtr.below;
    // }
    // for (int i = 0; i < numRows * numCols; i++) {
    // if (counts[i] == 1) {
    // colPtr = col;
    // while (colPtr != null) {
    // if (colPtr.possibleValues != null && colPtr.possibleValues.contains(i + 1)) {
    // colPtr.setVal(i + 1);
    // propCell(colPtr);
    // }
    // colPtr = colPtr.below;
    // }
    // return true;
    // }
    // }
    // }
    // // for blocks
    // for (Cell bloc : blocks) {
    // int[] counts = new int[numRows * numCols];
    // for (int y = 0; y < counts.length; y++) {
    // counts[y] = 0;
    // }
    // Cell blockPtr = bloc;
    // while (blockPtr != null) {
    // if (blockPtr.possibleValues != null) {
    // Node<Integer> nodePtr = blockPtr.possibleValues.head;
    // while (nodePtr != null) {
    // counts[nodePtr.data - 1]++;
    // nodePtr = nodePtr.next;
    // }
    // }
    // blockPtr = blockPtr.block;
    // }
    // for (int i = 0; i < numRows * numCols; i++) {
    // if (counts[i] == 1) {
    // blockPtr = bloc;
    // while (blockPtr != null) {
    // if (blockPtr.possibleValues != null && blockPtr.possibleValues.contains(i +
    // 1)) {
    // blockPtr.setVal(i + 1);
    // propCell(blockPtr);
    // }
    // blockPtr = blockPtr.block;
    // }
    // return true;
    // }
    // }
    // }

    // return false;
    // }

    // public boolean duplicateCells() {
    // for (Cell row : rows) {
    // Cell rowPtr = row;
    // while (rowPtr != null) {
    // if (rowPtr.possibleValues != null && rowPtr.possibleValues.length == 2) {
    // Cell secondPtr = rowPtr.right;
    // while (secondPtr != null) {
    // if (rowPtr.possibleValues.equals(secondPtr.possibleValues)) {
    // Cell thirdPtr = row;
    // boolean change = false;
    // while (thirdPtr != null) {
    // if (thirdPtr != secondPtr && thirdPtr != rowPtr && thirdPtr.possibleValues !=
    // null) {
    // change = change || thirdPtr.possibleValues.remove(rowPtr.possibleValues);
    // }
    // thirdPtr = thirdPtr.right;
    // }
    // if (change) {
    // return true;
    // }
    // }
    // secondPtr = secondPtr.right;
    // }
    // }
    // rowPtr = rowPtr.right;
    // }
    // }
    // // for cols
    // for (Cell col : cols) {
    // Cell colPtr = col;
    // while (colPtr != null) {
    // if (colPtr.possibleValues != null && colPtr.possibleValues.length == 2) {
    // Cell secondPtr = colPtr.below;
    // while (secondPtr != null) {
    // if (colPtr.possibleValues.equals(secondPtr.possibleValues)) {
    // Cell thirdPtr = col;
    // boolean change = false;
    // while (thirdPtr != null) {
    // if (thirdPtr != secondPtr && thirdPtr != colPtr && thirdPtr.possibleValues !=
    // null) {
    // change = change || thirdPtr.possibleValues.remove(colPtr.possibleValues);
    // }
    // thirdPtr = thirdPtr.below;
    // }
    // if (change) {
    // return true;
    // }
    // }
    // secondPtr = secondPtr.below;
    // }
    // }
    // colPtr = colPtr.below;
    // }
    // }
    // // for blocks
    // for (Cell bloc : blocks) {
    // Cell blockPtr = bloc;
    // while (blockPtr != null) {
    // if (blockPtr.possibleValues != null && blockPtr.possibleValues.length == 2) {
    // Cell secondPtr = blockPtr.block;
    // while (secondPtr != null) {
    // if (blockPtr.possibleValues.equals(secondPtr.possibleValues)) {
    // Cell thirdPtr = bloc;
    // boolean change = false;
    // while (thirdPtr != null) {
    // if (thirdPtr != secondPtr && thirdPtr != blockPtr && thirdPtr.possibleValues
    // != null) {
    // change = change || thirdPtr.possibleValues.remove(blockPtr.possibleValues);
    // }
    // thirdPtr = thirdPtr.block;
    // }
    // if (change) {
    // return true;
    // }
    // }
    // secondPtr = secondPtr.block;
    // }
    // }
    // blockPtr = blockPtr.block;
    // }
    // }
    // return false;
    // }
}