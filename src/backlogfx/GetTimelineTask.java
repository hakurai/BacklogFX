package backlogfx;

import backlog4j.Activity;
import backlog4j.BacklogClient;
import backlogfx.core.BacklogClientProvider;
import backlogfx.core.BacklogTaskBase;
import com.google.inject.Inject;

import java.util.List;

/**
 * @author eguchi
 */
public class GetTimelineTask extends BacklogTaskBase<List<Activity>> {

    @Inject
    private BacklogClientProvider backlogClientProvider;

    public GetTimelineTask() {
    }

    @Override
    protected List<Activity> call() throws Exception {
        BacklogClient client = backlogClientProvider.get();
        return client.getTimeline().execute();
    }
}
