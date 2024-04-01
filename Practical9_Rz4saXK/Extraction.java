public class Extraction extends HashFunction {

    public Extraction(int n) {
        this.num = n;
    }

    @Override
    public int hash(String input) {
        String numString = stringToNum(input);
        int endIndex = numString.length();
        int startIndex = Math.max(0, endIndex - num);
        String subString = numString.substring(startIndex, endIndex);
        return Integer.parseInt(subString);
    }
}
