package backlogfx.kanban;

import backlogfx.BacklogFxContext;
import backlogfx.BacklogFxModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.image.Image;

/**
 * @author eguchi
 */
public class IssueDescriptionModel {

    @Inject
    private BacklogFxContext context;

    public IssueDescriptionModel() {
    }

    public ReadOnlyObjectProperty<Image> getUserIcon(Integer id) {
        GetUserIconTask task = Guice.createInjector(new BacklogFxModule()).getInstance(GetUserIconTask.class);
        task.setUserId(id);

        context.getThreadPool().execute(task);

        return task.valueProperty();
    }
}
