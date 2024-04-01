public class Division extends HashFunction {

    public Division(int n) {
        this.num = n;
    }

    @Override
    public int hash(String input) {
        String numString = stringToNum(input);
        long numLong = Long.parseLong(numString);
        return (int) (numLong % num);
    }
}
