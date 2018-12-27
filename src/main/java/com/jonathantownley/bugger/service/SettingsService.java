package com.jonathantownley.bugger.service;

import java.util.List;

public interface SettingsService {
    String getAuthor();
    Boolean getShowClosed();
    Boolean getShowRejected();
    Boolean getShowDuplicates();
    List<String> getStatuses();
    List<String> getSeverities();
    void setAuthor(String author);
    void setShowClosed(boolean showClosed);
    void setShowRejected(boolean showRejected);
    void setShowDuplicates(boolean showDuplicates);
}
