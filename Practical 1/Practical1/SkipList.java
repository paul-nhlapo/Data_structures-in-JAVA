import java.util.Random;

// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<T>> {
    private int maxLevel;
    private SkipListNode<T>[] root;
    private int[] powers;
    // Do not change the seed. This is used to generate the same values every run
    private Random randomGenerator = new Random(123456);

    public SkipList(int maxLevel) {
        this.maxLevel = maxLevel;
        this.root = new SkipListNode[maxLevel];
        this.powers = new int[maxLevel];

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
        int i, r = Math.abs(randomGenerator.nextInt()) % powers[maxLevel - 1] + 1;
        for (i = 1; i < maxLevel; i++) {
            if (r < powers[i]) {
                return i - 1;
            }
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
            while (curr[lvl] != null && (curr[lvl].key.compareTo(key) < 0 || curr[lvl].key.equals(key))) {
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
        return root[0] == null;
    }

    public SkipListNode<T> search(T key) {
        // return null;
        // if (root == null || root[0] == null) {
        // return null;
        // }
        int lvl;
        SkipListNode<T> prev, curr;
        for (lvl = maxLevel - 1; lvl >= 0 && root[lvl] == null; lvl--)
            ;
        prev = curr = root[lvl];
        while (true) {

            if (key.compareTo(curr.key) == 0)
                return curr;
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
        String[] levels = new String[maxLevel];

        // Initialize each string to "[LvlX]"
        for (int i = 0; i < maxLevel; i++) {
            levels[i] = "[Lvl " + i + "]";
        }

        SkipListNode<T> ptr = root[0];

        while (ptr != null) {
            for (int i = 0; i < maxLevel && ptr != null; i += 1) {
                if (i < ptr.next.length) {
                    levels[i] += "->" + ptr.toString();
                } else {
                    levels[i] += "--" + ptr.emptyString();
                }

            }
            ptr = ptr.next[0];
        }

        for (int i = 0; i < maxLevel; i++) {
            while (levels[i].charAt(levels[i].length() - 1) == '-') {
                levels[i] = levels[i].substring(0, levels[i].length() - 1);
            }
        }

        String result = "";
        for (int i = maxLevel - 1; i >= 0; i--) {
            result += levels[i] + "\n";

        }
        return result;
    }

    public boolean delete(T key) {
        SkipListNode<T> node = this.search(key);
        if (node == null) {
            return false;
        }
        SkipListNode<T>[] preNode = new SkipListNode[maxLevel];
        SkipListNode<T> ptr = root[node.next.length - 1];
        for (int i = Math.min(node.next.length - 1, maxLevel - 1); i >= 0; i--) {
            while (ptr != null && ptr.next[i] != node) {
                ptr = ptr.next[i];
            }
            preNode[i] = ptr;
        }
        for (int i = 0; i < node.next.length; i++) {
            if (preNode[i] != null) {
                preNode[i].next[i] = node.next[i];
            }
        }
        return true;
    }

    public void printSearchPath(T key) {
        if (root == null || root[0] == null) {
            return;
        }
        int lvl;
        SkipListNode<T> prev, curr;
        String pathStr = "";
        for (lvl = maxLevel - 1; lvl >= 0 && root[lvl] == null; lvl--)
            ;
        prev = curr = root[lvl];
        pathStr += curr.toString();
        while (true) {

            if (key.equals(curr.key)) {
                System.out.print(pathStr + "\n");
                return;
            } else if (key.compareTo(curr.key) < 0) {
                if (lvl == 0)
                    return;
                else if (curr == root[lvl]) {
                    curr = root[--lvl];
                    pathStr += curr.toString();
                } else {
                    curr = prev.next[--lvl];
                    pathStr += curr.toString();
                }
            } else {
                prev = curr;
                if (curr.next[lvl] != null) {
                    curr = curr.next[lvl];
                    pathStr += curr.toString();
                } else {
                    for (lvl--; lvl >= 0 && curr.next[lvl] == null; lvl--)
                        ;
                    if (lvl >= 0) {
                        curr = curr.next[lvl];
                        pathStr += curr.toString();
                    } else
                        return;
                }
            }
        }

    }
}
