package backlogfx;

import backlog4j.Activity;
import backlog4j.BacklogClient;
import com.google.inject.Inject;
import javafx.concurrent.Task;

import java.util.List;

/**
 * @author eguchi
 */
public class GetTimelineTask extends Task<List<Activity>> {

    @Inject
    private BacklogFxContext context;

    public GetTimelineTask() {
    }

    @Override
    protected List<Activity> call() throws Exception {
        BacklogClient client = context.getClient();
        return client.getTimeline().execute();
    }
}
