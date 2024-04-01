import java.util.ArrayList;

public class User {
    String userName;
    int userID;
    int saturationDegre = 0;
    int uncolouredDegre = 0;
    int index;
    ArrayList<Relationship> friends = new ArrayList<>();

    @Override
    public String toString() {
        return userName + "[" + userID + "]";
    }

    public User(String userName, int userID) {
        this.userName = userName;
        this.userID = userID;
    }

    public Relationship[] getFriends() {
        return friends.toArray(new Relationship[0]);
    }

    public Relationship addFriend(User friend, double friendshipValue) {

        if (friend == null) {
            return null;
        } else {
            for (int i = 0; i < friends.size(); i++) {
                if (friends.get(i).friendA == friend || friends.get(i).friendB == friend) {
                    return this.friends.get(i);
                }
            }

            addFriend(new Relationship(this, friend, friendshipValue));
            friend.addFriend(new Relationship(friend, this, friendshipValue));

        }
        return new Relationship(this, friend, friendshipValue);

    }

    public void addFriend(Relationship relationship) {
        this.friends.add(relationship);
        uncolouredDegre++;
    }
}
