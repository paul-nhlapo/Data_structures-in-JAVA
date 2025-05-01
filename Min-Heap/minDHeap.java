@SuppressWarnings("unchecked")
public class minDHeap<T extends Comparable<T>> {
    private int d;
    private T[] nodes;

    @Override
    public String toString() {
        if (nodes.length == 0) {
            return "";
        }

        return "[" + nodes[0] + "]\n" + toStringRec(0, "");
    }

    public String toStringRec(int i, String pre) {// This method is used to print the tree
        if (i >= nodes.length) {
            return "";
        }
        String res = "";
        for (int k = 0; k < d; k++) {
            int c = d * i + k + 1;
            if (c < nodes.length) {
                if (k == d - 1 || c + 1 >= nodes.length) {
                    res += pre + "└── " + "[" + nodes[c] + "]\n" + toStringRec(c, pre + "    ");
                } else {
                    res += pre + "├── " + "[" + nodes[c] + "]\n" + toStringRec(c, pre + "│   ");
                }
            }
        }
        return res;
    }

    public T[] getNodes() {
        return nodes;
    }

    /*
     * Don't change anything above this line
     */

    public minDHeap(int d) {

        this.d = d;// Initailize d to the value passed in
        this.nodes = (T[]) new Comparable[0];// Initialize nodes to an empty array of type T

    }

    public void insert(T val) {

        if (nodes.length == 0) {// If the array is empty, add the value to the array
            nodes = (T[]) new Comparable[1];// Create a new array of size 1
            nodes[0] = val;// Add the value to the array
            return;// Return
        } else {// If the array is not empty
            T[] temp = (T[]) new Comparable[nodes.length + 1];// Create a new array of size 1 larger than the current
                                                              // array
            for (int i = 0; i < nodes.length; i++) {// Copy the values from the current array to the new array
                temp[i] = nodes[i];// Copy the value
            }
            temp[nodes.length] = val;// Add the new value to the end of the new array
            nodes = temp;// Set the current array to the new array
            int i = nodes.length - 1;// This is the index of the last element
            while (i > 0 && nodes[i].compareTo(nodes[(i - 1) / d]) < 0) {// While the value at the current index is less
                                                                         // than the value at the parent index and the
                                                                         // current index is not 0
                T temp2 = nodes[i];// Create a temporary variable to hold the value at the current index
                nodes[i] = nodes[(i - 1) / d];// Set the value at the current index to the value at the parent index
                nodes[(i - 1) / d] = temp2;// Set the value at the parent index to the value at the current index
                i = (i - 1) / d;// Set the current index to the parent index
            }
        }

    }

    public void remove(T val) {// Remove the value from the array
        try {// Try to remove the value

            if (nodes.length == 0) {// If the array is empty, return
                return;
            }
            int i = 0;// This is the index of the value to be removed
            while (i < nodes.length && nodes[i].compareTo(val) != 0) {// While the value at the current index is not the
                                                                      // value to be removed and the current index is
                                                                      // not
                                                                      // the last index
                i++;// Increment the current index
            }
            if (i == nodes.length) {// If the current index is the last index, return
                return;
            }
            nodes[i] = nodes[nodes.length - 1];// Set the value at the current index to the value at the last index
            T[] temp = (T[]) new Comparable[nodes.length - 1];// Create a new array of size 1 less than the current
                                                              // array
            for (int j = 0; j < nodes.length - 1; j++) {// Copy the values from the current array to the new array
                temp[j] = nodes[j];// Copy the value
            }
            nodes = temp;// Set the current array to the new array
            int k = i;// This is the index of the value to be removed
            while (k < nodes.length && nodes[k].compareTo(nodes[(k - 1) / d]) < 0) {// While the value at the current
                                                                                    // index is less than the value at
                                                                                    // the
                                                                                    // parent index and the current
                                                                                    // index
                                                                                    // is not the last index
                T temp2 = nodes[k];// Create a temporary variable to hold the value at the current index
                nodes[k] = nodes[(k - 1) / d];// Set the value at the current index to the value at the parent index
                nodes[(k - 1) / d] = temp2;// Set the value at the parent index to the value at the current index
                k = (k - 1) / d;// Set the current index to the parent index
            }
            while (k < nodes.length) {// While the current index is not the last index
                int min = k;// This is the index of the minimum value
                for (int j = 1; j <= d; j++) {// For each child of the current index (d is the number of children)
                    if (d * k + j < nodes.length && nodes[d * k + j].compareTo(nodes[min]) < 0) {// If the value at the
                                                                                                 // current index is
                                                                                                 // less
                                                                                                 // than the value at
                                                                                                 // the
                                                                                                 // minimum index
                        min = d * k + j;// Set the minimum index to the current index
                    }
                }
                if (min != k) {// If the minimum index is not the current index
                    T temp2 = nodes[k];// Create a temporary variable to hold the value at the current index
                    nodes[k] = nodes[min];// Set the value at the current index to the value at the minimum index
                    nodes[min] = temp2;// Set the value at the minimum index to the value at the current index
                    k = min;// Set the current index to the minimum index
                } else {// If the minimum index is the current index
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {// Catch the exception if the value is not in the array
            if (nodes.length == 0) {// If the array is empty, return
                return;
            }
            int i = 0;// This is the index of the value to be removed
            while (i < nodes.length && nodes[i].compareTo(val) != 0) {// While the value at the current index is not the
                                                                      // value to be removed and the current index is
                                                                      // not
                                                                      // the last index
                i++;// Increment the current index
            }
            if (i == nodes.length) {// If the current index is the last index, return
                return;
            }
            nodes[i] = nodes[nodes.length - 1];// Set the value at the current index to the value at the last index
            T[] temp = (T[]) new Comparable[nodes.length - 1];// Create a new array of size 1 less than the current
                                                              // array
            for (int j = 0; j < temp.length; j++) {// Copy the values from the current array to the new array
                temp[j] = nodes[j];// Copy the value
            }
            nodes = temp;// Set the current array to the new array
            int k = i;// This is the index of the value to be removed
            while (k > 0 && nodes[k].compareTo(nodes[(k - 1) / d]) < 0) {// While the value at the current index is less
                                                                         // than the value at the parent index and the
                                                                         // current index is not 0
                T temp2 = nodes[k];// Create a temporary variable to hold the value at the current index
                nodes[k] = nodes[(k - 1) / d];// Set the value at the current index to the value at the parent index
                nodes[(k - 1) / d] = temp2;// Set the value at the parent index to the value at the current index
                k = (k - 1) / d;// Set the current index to the parent index
            }
            while (k < nodes.length) {// While the current index is not the last index
                int min = k;// This is the index of the minimum value
                for (int j = 1; j <= d; j++) {// For each child of the current index (d is the number of children)
                    if (d * k + j < nodes.length && nodes[d * k + j].compareTo(nodes[min]) < 0) {// If the value at the
                                                                                                 // current index is
                                                                                                 // less
                                                                                                 // than the value at
                                                                                                 // the
                                                                                                 // minimum index
                        min = d * k + j;// Set the minimum index to the current index
                    }
                }
                if (min != k) {// If the minimum index is not the current index
                    T temp2 = nodes[k];// Create a temporary variable to hold the value at the current index
                    nodes[k] = nodes[min];// Set the value at the current index to the value at the minimum index
                    nodes[min] = temp2;// Set the value at the minimum index to the value at the current index
                    k = min;// Set the current index to the minimum index
                } else {// If the minimum index is the current index
                    break;
                }
            }
        }
    }

    public void changeD(int newD) {// This method changes the number of children
        if (newD >= 1) {// If the new number of children is greater than or equal to 1
            d = newD;// Set the number of children to the new number of children
            for (int i = (nodes.length - 2) / d; i >= 0; i--) {// For each parent node (nodes.length - 2) / d is the
                                                               // index of the last parent node
                int k = i;// This is the index of the parent node
                while (k * d + 1 < nodes.length) {// While the current index is not the last index
                    int minChild = k * d + 1;// This is the index of the minimum child
                    int maxChild = Math.min(minChild + d - 1, nodes.length - 1);// This is the index of the maximum
                                                                                // child
                    int min = minChild;// This is the index of the minimum value
                    for (int j = minChild + 1; j <= maxChild; j++) {// For each child of the current index
                        if (nodes[j].compareTo(nodes[min]) < 0) {// If the value at the current index is less than the
                                                                 // value at the minimum index
                            min = j;// Set the minimum index to the current index
                        }
                    }
                    if (nodes[k].compareTo(nodes[min]) > 0) {// If the value at the current index is greater than the
                                                             // value at the minimum index
                        T temp = nodes[k];// Create a temporary variable to hold the value at the current index
                        nodes[k] = nodes[min];// Set the value at the current index to the value at the minimum index
                        nodes[min] = temp;// Set the value at the minimum index to the value at the current index
                        k = min;// Set the current index to the minimum index
                    } else {// If the value at the current index is not greater than the value at the
                            // minimum index
                        break;
                    }
                }
            }
        }
    }

    public T min(int i) {// This method returns the minimum value in the subtree rooted at the node at
                         // the given index

        if (nodes.length == 0 || i >= nodes.length) {// If the array is empty or the index is out of bounds, return null
            return null;
        }

        T min = nodes[i];// This is the minimum value
        for (int j = 1; j <= d; j++) {// For each child of the current index (d is the number of children)
            if (d * i + j < nodes.length) {// If the current index is not the last index
                T temp = min(d * i + j);// This is the minimum value in the subtree rooted at the child
                if (temp.compareTo(min) < 0) {// If the minimum value in the subtree rooted at the child is less than
                                              // the
                                              // minimum value
                    min = temp;// Set the minimum value to the minimum value in the subtree rooted at the child
                }
            }
        }
        return min;// Return the minimum value

    }

    public T max(int i) {// This method returns the maximum value in the subtree rooted at the node at
                         // the given index

        if (nodes.length == 0 || i >= nodes.length) {// If the array is empty or the index is out of bounds, return null
            return null;
        }

        T max = nodes[i];// This is the maximum value
        for (int j = 1; j <= d; j++) {// For each child of the current index (d is the number of children)
            if (d * i + j < nodes.length) {// If the current index is not the last index
                T temp = max(d * i + j);// This is the maximum value in the subtree rooted at the child
                if (temp.compareTo(max) > 0) {// If the maximum value in the subtree rooted at the child is greater than
                                              // the maximum value
                    max = temp;// Set the maximum value to the maximum value in the subtree rooted at the child
                }
            }
        }
        return max;// Return the maximum value

    }

    public String pathToRoot(T val) {// This method returns the path from the given value to the root

        if (nodes.length == 0) {// If the array is empty, return an empty string
            return "";
        }

        int i = 0;// This is the index of the given value
        while (i < nodes.length && nodes[i].compareTo(val) != 0) {// While the index is not the last index and the value
                                                                  // at the index is not the given value
            i++;// Increment the index
        }
        if (i == nodes.length) {// If the index is the last index, return an empty string
            return "";
        }

        String path = "";// This is the path from the given value to the root

        while (i > 0) {// While the index is not the root
            path = "[" + nodes[i] + "]" + path;// Add the value at the index to the path
            i = (i - 1) / d;// Set the index to the parent of the current index
        }
        path = "[" + nodes[i] + "]" + path;// Add the value at the index to the path

        // Reverse the order of the path string
        String reversedPath = "";// This is the reversed path
        for (int j = path.length() - 1; j >= 0; j--) {// For each character in the path string
            if (path.charAt(j) == ']') {// If the character is a closing bracket
                int start = j - 1;// This is the index of the opening bracket
                while (start >= 0 && path.charAt(start) != '[') {// While the index is not the beginning of the string
                                                                 // and
                                                                 // the character at the index is not an opening bracket
                    start--;// Decrement the index
                }
                reversedPath = reversedPath + path.substring(start, j + 1);// Add the substring from the opening bracket
                                                                           // to the closing bracket to the reversed
                                                                           // path
                j = start;// Set the index to the opening bracket
            }
        }

        return reversedPath;// Return the reversed path
    }

}
