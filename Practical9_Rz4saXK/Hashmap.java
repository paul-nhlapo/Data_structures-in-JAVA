public class Hashmap {
    public HashFunction[] functions;
    private String[][] data;

    public Hashmap(int length, HashFunction[] funcs) {
        this.functions = funcs;
        this.data = new String[length][];
    }

    public int hash(String val) {
        int sum = 0;
        for (HashFunction function : functions) {
            sum += function.hash(val);
        }
        return sum % data.length;
    }

    public boolean contains(String val) {
        int index = hash(val);
        if (data[index] != null) {
            for (String s : data[index]) {
                if (s.equals(val)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void insert(String s) {
        if (!contains(s)) {
            int index = hash(s);
            if (data[index] == null) {
                data[index] = new String[] { s };
            } else {
                String[] newData = new String[data[index].length + 1];
                for (int i = 0; i < data[index].length; i++) {
                    newData[i] = data[index][i];
                }
                newData[newData.length - 1] = s;
                data[index] = newData;
            }
        }
    }

    public void remove(String s) {
        int index = hash(s);
        if (data[index] != null) {
            int count = 0;
            for (String str : data[index]) {
                if (!str.equals(s)) {
                    count++;
                }
            }
            String[] newData = new String[count];
            int i = 0;
            for (String str : data[index]) {
                if (!str.equals(s)) {
                    newData[i++] = str;
                }
            }
            data[index] = newData;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                for (int j = 0; j < data[i].length; j++) {
                    sb.append(data[i][j]);
                    if (j < data[i].length - 1) {
                        sb.append(",");
                    }
                }
            }
            if (i < data.length - 1) {
                sb.append(";");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
