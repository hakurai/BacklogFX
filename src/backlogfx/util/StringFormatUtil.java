package backlogfx.util;

import backlog4j.Category;
import backlog4j.Milestone;
import backlog4j.Version;

import java.util.List;

/**
 * @author eguchi
 */
public final class StringFormatUtil {
    private StringFormatUtil() {
    }

    //20090731
    public static String formatShortDate(String prefix, String text) {
        if (text == null || text.isEmpty()) {
            return prefix + "-";
        }
        return prefix + text.substring(0, 4) + "/" + text.substring(4, 6) + "/" + text.substring(6, 8);
    }

    //20090731151859
    public static String formatDate(String prefix, String text) {
        if (text == null || text.isEmpty()) {
            return prefix + "-";
        }
        return prefix + text.substring(0, 4) + "/" + text.substring(4, 6) + "/" + text.substring(6, 8) +
                " " + text.substring(8, 10) + ":" + text.substring(10, 12) + ":" + text.substring(12, 14);
    }

    public static String formatComponents(List<Category> categories) {
        if (categories.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(categories.get(0).getName());
        for (int i = 1, len = categories.size(); i < len; i++) {
            sb.append(',').append(categories.get(i).getName());
        }

        return sb.toString();
    }

    public static String formatVersions(List<Version> versions) {
        if (versions.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(versions.get(0).getName());
        for (int i = 1, len = versions.size(); i < len; i++) {
            sb.append(',').append(versions.get(i).getName());
        }

        return sb.toString();
    }

    public static String formatMilestones(List<Milestone> milestones) {
        if (milestones.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(milestones.get(0).getName());
        for (int i = 1, len = milestones.size(); i < len; i++) {
            sb.append(',').append(milestones.get(i).getName());
        }

        return sb.toString();
    }
}
