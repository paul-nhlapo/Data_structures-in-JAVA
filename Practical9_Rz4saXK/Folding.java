public class Folding extends HashFunction {
    public boolean shift;

    public Folding(int n, boolean s) {
        this.num = n;
        this.shift = s;
    }

    @Override
    public int hash(String input) {
        String numString = stringToNum(input);
        while (numString.length() % num != 0) {
            numString += "0";
        }
        int sum = 0;
        for (int i = 0; i < numString.length(); i += num) {
            String partition = numString.substring(i, i + num);
            if (!shift && (i / num) % 2 == 1) {
                partition = new StringBuilder(partition).reverse().toString();
            }
            sum += Integer.parseInt(partition);
        }
        return sum;
    }
}
