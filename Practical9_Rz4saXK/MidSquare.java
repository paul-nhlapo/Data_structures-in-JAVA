public class MidSquare extends HashFunction {

    public MidSquare(int n) {
        this.num = n;
    }

    @Override
    public int hash(String input) {
        String numString = stringToNum(input);
        long numLong = Long.parseLong(numString);
        long squared = numLong * numLong;
        String squaredString = Long.toString(squared);
        if (squaredString.length() <= num) {
            return Integer.parseInt(squaredString);
        } else {
            while (squaredString.length() % 2 != num % 2) {
                squaredString += "0";
            }
            int midStart = (squaredString.length() - num) / 2;
            String midString = squaredString.substring(midStart, midStart + num);
            return Integer.parseInt(midString);
        }
    }
}
