public class HashMap<T, U> {
    private Object[] keyArray;
    private Object[] valueArray;
    private Object[] keyCellar;
    private Object[] valueCellar;

    public HashMap(int ArraySize, int CellarSize) {
        valueCellar = new Object[CellarSize];
        valueArray = new Object[ArraySize];
        keyArray = new Object[ArraySize];
        keyCellar = new Object[CellarSize];

    }

    public boolean put(T key, U value) {
    }

    @SuppressWarnings("unchecked")
    public U get(T key) {
    }

    @SuppressWarnings("unchecked")
    public HashMap<T, U> rehash(int ArraySize, int CellarSize) {

    }

    public int arrayHash(T key) {
        int KeyAs = keyArray.length,
                keyVal = convertTtoInt(key);
        return Math.abs(keyVal % KeyAs);
    }

    public int cellarHash(T key) {
        int KeyAs = keyArray.length,
                keyVal = convertTtoInt(key),
                KeyCs = keyCellar.length;
        return Math.abs(KeyAs - keyVal) % KeyCs;
    }

    public static int linearProbing(int i, int hashValue, int modVal) {
        return (hashValue + i) % modVal;
    }

    public static int quadraticProbing(int i, int hashValue, int modVal) {
        return ((int) Math.abs(hashValue + Math.round(Math.pow(-1, i - 1)) * Math.pow(Math.floor((i + 1) / 2), 2)))
                % modVal;
    }

    public int count() {

    }

    public boolean isContained(T key, U value) {

    }

    public Object[] getKeyArray() {
        return keyArray;
    }

    public Object[] getKeyCellar() {
        return keyCellar;
    }

    public Object[] getValueArray() {
        return valueArray;
    }

    public Object[] getValueCellar() {
        return valueCellar;
    }

    // Its not advised to change this *wink wink*
    private int convertTtoInt(T key) {
        if (key == null)
            return -1;
        return key.hashCode();
    }
}
