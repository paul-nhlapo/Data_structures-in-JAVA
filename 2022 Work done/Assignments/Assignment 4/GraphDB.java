import java.util.ArrayList;

public class GraphDB {
    private ArrayList<User> users = new ArrayList<>();
    int index;
    private int uncoloredV = 0;

    private ArrayList<ArrayList<User>> colors_array = new ArrayList<ArrayList<User>>(); // set of colors

    public User addUser(String userName, int ID) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userID == ID) {
                return users.get(i);
            }
        }
        User user = new User(userName, ID);
        users.add(user);
        uncoloredV++;
        return user;
    }

    public User getUser(int userID) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userID == userID) {
                return users.get(i);
            }
        }
        return null;
    }

    public User getUser(String userName) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userName == userName) {
                return users.get(i);
            }
        }
        return null;
    }

    public Relationship addFriendship(int frienteeID, int friendedID, double relationshipValue) {
        User user1 = getUser(frienteeID);
        User user2 = getUser(friendedID);
        if (user1 == null || user2 == null) {
            return null;
        }
        return user1.addFriend(user2, relationshipValue);
    }

    public User[][] clusterUsers() {
        if (this.users.isEmpty()) {
            return null;
        }

        int i = users.size() - 1;
        // System.out.print("Uncolored : " + uncoloredV + "\n");

        while (uncoloredV > 0) {
            sortArrange(users);
            index = 0;
            coloring(users.get(i), i);

            colors_array.get(index).add(users.get(i));
            i--;
            uncoloredV--;
        }
        // for (i = 0; i < colors_array.size(); i++) {
        // for (int j = 0; j < colors_array.get(i).size(); j++) {
        // sort(colors_array.get(i));
        // System.out.print(
        // colors_array.get(i).get(j) + " ");
        // }
        // System.out.print("\n");
        // }

        return null;
    }

    private ArrayList<Relationship> STree = new ArrayList<>();

    public Relationship[] minSpanningTree() {
        if (this.users.isEmpty()) {
            return null;
        } else {
            SpanningHelper(this.users.get(0));
            Relationship[] minSpan = new Relationship[STree.size()];
            for (int i = 0; i < STree.size(); i++) {
                minSpan[i] = STree.get(i);
            }
            return minSpan;
        }
    }

    public User[] getUsersAtDistance(User fromUser, int distance) {
        if (this.users.isEmpty() || fromUser == null || distance == 0 || getUser(fromUser.userID) == null) {

            return null;
        } else {
            visitedUsers.add(fromUser);
            for (int i = 0; i < fromUser.friends.size(); i++) {

                if (fromUser.friends.get(i).friendA == fromUser) {
                    SpanningHelper(fromUser, fromUser.friends.get(i).friendB, distance);
                    count = 0;

                } else {
                    SpanningHelper(fromUser, fromUser.friends.get(i).friendA, distance);
                    count = 0;
                }
            }
            User[] Distance = new User[UsersAtDistance.size()];
            for (int i = 0; i < UsersAtDistance.size(); i++) {
                Distance[i] = UsersAtDistance.get(i);
            }
            // UsersAtDistance.clear();
            // for (int i = 0; i < Distance.length; i++) {
            // System.out.print(Distance[i] + "\n");
            // }
            // System.out.print(UsersAtDistance);
            return Distance;

        }

    }

    ArrayList<User> Visited = new ArrayList<>();

    public void SpanningHelper(User user) {
        Visited.add(user);
        if (user.friends.size() == 0) {
            return;
        } else {
            int index = 0;
            Relationship temp = user.friends.get(index);

            for (int j = 0; j < user.friends.size(); j++) {
                User userFreind = user.friends.get(j).friendB;
                boolean visted = false;
                if (user.friends.get(j).friendB == user) {
                    userFreind = user.friends.get(j).friendA;
                }

                for (int i = 0; i < Visited.size(); i++) {
                    if (Visited.get(i) == userFreind) {
                        visted = true;
                    }
                }

                if (visted == false) {
                    if (temp.friendshipValue >= user.friends.get(j).friendshipValue) {
                        temp = user.friends.get(j);
                    }
                } else {
                    if ((j + 1) < user.friends.size()) {
                        temp = user.friends.get(j + 1);
                    }
                }
            }

            User tempUser;
            if (user == temp.friendB) {
                tempUser = temp.friendA;
            } else {
                tempUser = temp.friendB;
            }

            boolean visted = false;
            for (int i = 0; i < Visited.size(); i++) {
                if (tempUser == Visited.get(i)) {
                    visted = true;
                }
            }

            if (visted == true) {
                return;
            } else {
                STree.add(temp);
                SpanningHelper(tempUser);
                temp = user.friends.get(index);

                for (int i = (index); i < user.friends.size(); i++) {
                    User next = user.friends.get(i).friendB;
                    if (next == user) {
                        next = user.friends.get(i).friendA;
                    }
                    boolean revisted = false;
                    for (int j = 0; j < Visited.size(); j++) {
                        if (Visited.get(j) == next) {
                            revisted = true;
                        }
                    }
                    if (revisted == false) {
                        STree.add(user.friends.get(i));
                        SpanningHelper(next);
                    }
                }
            }
        }
    }

    private ArrayList<User> visitedUsers = new ArrayList<>();
    private ArrayList<User> UsersAtDistance = new ArrayList<>();

    int count = 0;

    public void SpanningHelper(User start, User endUser, int distance) {

        visitedUsers.add(endUser);
        count++;

        if (count == distance) {

            UsersAtDistance.add(endUser);

            count--;
            return;
        } else {
            for (int i = 0; i < endUser.friends.size(); i++) {
                if (endUser.friends.get(i).friendA == endUser) {
                    boolean visted = false;
                    for (int j = 0; j < visitedUsers.size(); j++) {
                        if (visitedUsers.get(i) == endUser.friends.get(i).friendB) {
                            visted = true;
                        }
                    }

                    if (!visted) {
                        count = 0;

                        SpanningHelper(endUser, endUser.friends.get(i).friendB, distance);
                    }
                } else {
                    boolean visted = false;
                    for (int j = 0; j < visitedUsers.size(); j++) {
                        if (visitedUsers.get(i) == endUser.friends.get(i).friendA) {
                            visted = true;

                        }
                    }

                    if (!visted) {
                        count = 0;
                        SpanningHelper(endUser, endUser.friends.get(i).friendA, distance);
                    }
                }

            }

        }

        visitedUsers.remove(endUser);
        count--;
    }

    public void sort(ArrayList<User> data) {
        int large = data.get(0).userID;
        int n = data.size();

        int[] temp = new int[n];

        for (int i = 1; i < n; i++)
            if (large < data.get(i).userID)
                large = data.get(i).userID;

        int[] count = new int[large + 1];
        for (int i = 0; i <= large; i++)
            count[i] = 0;

        for (int i = 0; i < n; i++)
            count[data.get(i).userID]++;

        for (int i = 1; i <= large; i++)
            count[i] = count[i - 1] + count[i];

        for (int i = n - 1; i >= 0; i--) {
            temp[count[data.get(i).userID] - 1] = data.get(i).userID;
            count[data.get(i).userID]--;
        }

        for (int i = 0; i < n; i++)
            data.get(i).userID = temp[i];

    }

    public void sortArrange(ArrayList<User> data) {
        int large = data.get(0).uncolouredDegre;
        int n = data.size();

        int[] temp = new int[n];

        for (int i = 1; i < n; i++)
            if (large < data.get(i).uncolouredDegre)
                large = data.get(i).uncolouredDegre;

        int[] count = new int[large + 1];
        for (int i = 0; i <= large; i++)
            count[i] = 0;

        for (int i = 0; i < n; i++)
            count[data.get(i).uncolouredDegre]++;

        for (int i = 1; i <= large; i++)
            count[i] = count[i - 1] + count[i];

        for (int i = n - 1; i >= 0; i--) {
            temp[count[data.get(i).uncolouredDegre] - 1] = data.get(i).uncolouredDegre;
            count[data.get(i).uncolouredDegre]--;
        }

        for (int i = 0; i < n; i++)
            data.get(i).uncolouredDegre = temp[i];

    }

    private void coloring(User user, int i) {
        boolean presentFriend = false;
        colors_array.add(new ArrayList<User>());
        if (colors_array.get(index).size() != 0) {
            for (int j = 0; j < colors_array.get(index).size(); j++) {
                for (int k = 0; k < users.get(i).friends.size(); k++) {
                    if (colors_array.get(index).get(j) == users.get(i).friends.get(k).friendA
                            || colors_array.get(index).get(j) == users.get(i).friends.get(k).friendB) {
                        presentFriend = true;
                    }
                    if (presentFriend) {
                        break;
                    } else {
                        if (users.get(i).friends.get(k).friendA == user) {
                            users.get(i).friends.get(k).friendB.saturationDegre++;
                        } else {
                            users.get(i).friends.get(k).friendA.saturationDegre++;
                        }
                    }
                }
                if (presentFriend) {
                    index++;
                    coloring(users.get(i), i);
                    break;
                }

            }
        }

    }
}
