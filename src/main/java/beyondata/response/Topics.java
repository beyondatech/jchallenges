package beyondata.response;

import java.util.List;

public class Topics {
    private List<String> topics;

    public List<String> getTopics() {
        System.out.println(2);
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}
