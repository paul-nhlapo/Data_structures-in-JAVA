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

    public String toStringRec(int i, String pre) {
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

        this.d = d;
        this.nodes = (T[]) new Comparable[0];

    }

    public void insert(T val) {
        if (d >= 1) {

            if (nodes.length == 0) {
                nodes = (T[]) new Comparable[1];
                nodes[0] = val;
                return;
            } else {
                T[] temp = (T[]) new Comparable[nodes.length + 1];
                for (int i = 0; i < nodes.length; i++) {
                    temp[i] = nodes[i];
                }
                temp[nodes.length] = val;
                nodes = temp;
                int i = nodes.length - 1;
                while (i > 0 && nodes[i].compareTo(nodes[(i - 1) / d]) < 0) {
                    T temp2 = nodes[i];
                    nodes[i] = nodes[(i - 1) / d];
                    nodes[(i - 1) / d] = temp2;
                    i = (i - 1) / d;
                }
            }
        } else {
            return;
        }

    }

    // public void remove(T val) {
    // if (nodes.length == 0) {
    // return;
    // }
    // int i = 0;
    // while (i < nodes.length && nodes[i].compareTo(val) != 0) {
    // i++;
    // }
    // if (i == nodes.length) {
    // return;
    // }
    // nodes[i] = nodes[nodes.length - 1];
    // T[] temp = (T[]) new Comparable[nodes.length - 1];
    // for (int j = 0; j < temp.length; j++) {
    // temp[j] = nodes[j];
    // }
    // nodes = temp;
    // int k = i;
    // while (k > 0 && nodes[k].compareTo(nodes[(k - 1) / d]) < 0) {
    // T temp2 = nodes[k];
    // nodes[k] = nodes[(k - 1) / d];
    // nodes[(k - 1) / d] = temp2;
    // k = (k - 1) / d;
    // }
    // while (k < nodes.length) {
    // int min = k;
    // for (int j = 1; j <= d; j++) {
    // if (d * k + j < nodes.length && nodes[d * k + j].compareTo(nodes[min]) < 0) {
    // min = d * k + j;
    // }
    // }
    // if (min != k) {
    // T temp2 = nodes[k];
    // nodes[k] = nodes[min];
    // nodes[min] = temp2;
    // k = min;
    // } else {
    // break;
    // }
    // }
    // }
    public void remove(T val) {
        if (nodes.length == 0) {
            return;
        }
        int i = 0;
        while (i < nodes.length && nodes[i].compareTo(val) != 0) {
            i++;
        }
        if (i == nodes.length) {
            return;
        }
        nodes[i] = nodes[nodes.length - 1];
        T[] temp = (T[]) new Comparable[nodes.length - 1];
        for (int j = 0; j < nodes.length - 1; j++) {
            temp[j] = nodes[j];
        }
        nodes = temp;
        int k = i;
        while (k < nodes.length && nodes[k].compareTo(nodes[(k - 1) / d]) < 0) {
            T temp2 = nodes[k];
            nodes[k] = nodes[(k - 1) / d];
            nodes[(k - 1) / d] = temp2;
            k = (k - 1) / d;
        }
        while (k < nodes.length) {
            int min = k;
            for (int j = 1; j <= d; j++) {
                if (d * k + j < nodes.length && nodes[d * k + j].compareTo(nodes[min]) < 0) {
                    min = d * k + j;
                }
            }
            if (min != k) {
                T temp2 = nodes[k];
                nodes[k] = nodes[min];
                nodes[min] = temp2;
                k = min;
            } else {
                break;
            }
        }
    }

    public void changeD(int newD) {
        if (newD >= 1) {
            d = newD;
            for (int i = (nodes.length - 2) / d; i >= 0; i--) {
                int k = i;
                while (k * d + 1 < nodes.length) {
                    int minChild = k * d + 1;
                    int maxChild = Math.min(minChild + d - 1, nodes.length - 1);
                    int min = minChild;
                    for (int j = minChild + 1; j <= maxChild; j++) {
                        if (nodes[j].compareTo(nodes[min]) < 0) {
                            min = j;
                        }
                    }
                    if (nodes[k].compareTo(nodes[min]) > 0) {
                        T temp = nodes[k];
                        nodes[k] = nodes[min];
                        nodes[min] = temp;
                        k = min;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public T min(int i) {

        if (nodes.length == 0 || i >= nodes.length) {
            return null;
        }

        T min = nodes[i];
        for (int j = 1; j <= d; j++) {
            if (d * i + j < nodes.length) {
                T temp = min(d * i + j);
                if (temp.compareTo(min) < 0) {
                    min = temp;
                }
            }
        }
        return min;

    }

    public T max(int i) {

        if (nodes.length == 0 || i >= nodes.length) {
            return null;
        }

        T max = nodes[i];
        for (int j = 1; j <= d; j++) {
            if (d * i + j < nodes.length) {
                T temp = max(d * i + j);
                if (temp.compareTo(max) > 0) {
                    max = temp;
                }
            }
        }
        return max;

    }

    public String pathToRoot(T val) {

        if (nodes.length == 0) {
            return "";
        }

        int i = 0;
        while (i < nodes.length && nodes[i].compareTo(val) != 0) {
            i++;
        }
        if (i == nodes.length) {
            return "";
        }

        String path = "";

        while (i > 0) {
            path = "[" + nodes[i] + "]" + path;
            i = (i - 1) / d;
        }
        path = "[" + nodes[i] + "]" + path;

        // Reverse the order of the path string
        String reversedPath = "";
        for (int j = path.length() - 1; j >= 0; j--) {
            if (path.charAt(j) == ']') {
                int start = j - 1;
                while (start >= 0 && path.charAt(start) != '[') {
                    start--;
                }
                reversedPath = reversedPath + path.substring(start, j + 1);
                j = start;
            }
        }

        return reversedPath;
    }

}
