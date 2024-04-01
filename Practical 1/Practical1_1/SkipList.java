import java.util.Random;

// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<T>> {
    // private int maxLevel;
    // private SkipListNode<T>[] root;
    public int maxLevel;
    public SkipListNode<T>[] root;
    private int[] powers;
    // Do not change the seed. This is used to generate the same values every run
    private Random randomGenerator = new Random(123456);

    public SkipList(int maxLevel) {
        maxLevel = maxLevel;
        root = new SkipListNode[maxLevel];
        powers = new int[maxLevel];
        for (int i = 0; i < maxLevel; i++) {
            root[i] = null;
        }
        choosePowers();
    }

    public void choosePowers() {
        powers[maxLevel - 1] = (2 << (maxLevel - 1)) - 1;
        for (int i = maxLevel - 2, j = 0; i >= 0; i--, j++) {
            powers[i] = powers[i + 1] - (2 << j);
        }
    }

    public int chooseLevel() {
        int i, r = Math.abs(rd.nextInt()) % powers[maxLevel - 1] + 1;
        for (i = 1; i < maxLevel; i++) {
            if (r < powers[i])
                return i - 1;
        }
        return i - 1;
    }

    public void insert(T key) {
        SkipListNode<T>[] curr = new SkipListNode[maxLevel];
        SkipListNode<T>[] prev = new SkipListNode[maxLevel];
        SkipListNode<T> newNode;
        int lvl, i;
        curr[maxLevel - 1] = root[maxLevel - 1];
        prev[maxLevel - 1] = null;
        for (lvl = maxLevel - 1; lvl >= 0; lvl--) {
            while (curr[lvl] != null && curr[lvl].key.compareTo(key) < 0) {
                prev[lvl] = curr[lvl];
                curr[lvl] = curr[lvl].next[lvl];
            }

            if (lvl > 0)
                if (prev[lvl] == null) {
                    curr[lvl - 1] = root[lvl - 1];
                    prev[lvl - 1] = null;
                } else {
                    curr[lvl - 1] = prev[lvl].next[lvl - 1];
                    prev[lvl - 1] = prev[lvl];
                }
        }
        lvl = chooseLevel();
        newNode = new SkipListNode<T>(key, lvl + 1);
        for (i = 0; i <= lvl; i++) {
            newNode.next[i] = curr[i];
            if (prev[i] == null)
                root[i] = newNode;
            else
                prev[i].next[i] = newNode;
        }
    }

    public boolean isEmpty() {
        return false;
    }

    public SkipListNode<T> search(T key) {
        // return null;
        int lvl;
        SkipListNode<T> prev, curr;
        for (lvl = maxLevel - 1; lvl >= 0 && root[lvl] == null; lvl--)
            ;
        prev = curr = root[lvl];
        while (true) {
            if (key.equals(curr.key))
                return (SkipListNode<T>) curr.key;
            else if (key.compareTo(curr.key) < 0) {
                if (lvl == 0)
                    return null;
                else if (curr == root[lvl])
                    curr = root[--lvl];
                else
                    curr = prev.next[--lvl];
            } else {
                prev = curr;
                if (curr.next[lvl] != null)
                    curr = curr.next[lvl];
                else {
                    for (lvl--; lvl >= 0 && curr.next[lvl] == null; lvl--)
                        ;
                    if (lvl >= 0)
                        curr = curr.next[lvl];
                    else
                        return null;
                }
            }
        }
    }

    @Override
    public String toString() {

        if (root == null) {
            return "";
        }

        String result = "";
        // for (SkipListNode<T> node = root; node != null; node = node.next) {
        // result = result.concat("[lvl " + node.key + "-" + node.data + "]");

        // if (node.next != null) {
        // result = result.concat("-> ");
        // }
        // }

        return result;
    }

    public boolean delete(T key) {

        return false;
    }

    public void printSearchPath(T key) {
        if (root == null) {
            return;
        }

        String result = "";
        // for (SkipListNode<T> node = root; node != null; node = node.next) {
        // result = result + "[" + node.key + "]";

        // if (node.next != null) {
        // result = result.concat(" ");
        // }
        // }
        // return result;
    }
}
