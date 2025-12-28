import java.util.*;

public class Recommendation {

    public static void main(String[] args) {

        Map<String, List<String>> userLikes = new HashMap<>();

        userLikes.put("mdhasif", Arrays.asList("Laptop", "Phone"));
        userLikes.put("User2", Arrays.asList("Laptop", "Tablet"));
        userLikes.put("User3", Arrays.asList("Phone", "Headphones", "Tablet"));

        String targetUser = "mdhasif";

        System.out.println("Hello " + targetUser + " 👋");
        System.out.println("Based on users with similar interests, we recommend:");

        Set<String> recommendations = recommendItems(targetUser, userLikes);

        for (String item : recommendations) {
            System.out.println("• " + item);
        }
    }

    private static Set<String> recommendItems(
            String targetUser,
            Map<String, List<String>> userLikes) {

        Set<String> result = new HashSet<>();

        List<String> targetLikes = userLikes.get(targetUser);

        for (String user : userLikes.keySet()) {

            if (user.equals(targetUser)) continue;

            for (String item : userLikes.get(user)) {
                if (targetLikes.contains(item)) {
                    result.addAll(userLikes.get(user));
                    break;
                }
            }
        }

        result.removeAll(targetLikes);

        return result;
    }
}
