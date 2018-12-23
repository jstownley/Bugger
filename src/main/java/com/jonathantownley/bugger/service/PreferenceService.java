package com.jonathantownley.bugger.service;

public interface PreferenceService {
    String getAuthor();
    Boolean getShowClosed();
    Boolean getShowRejected();
    Boolean getShowDuplicates();
    void setAuthor(String author);
    void setShowClosed(boolean showClosed);
    void setShowRejected(boolean showRejected);
    void setShowDuplicates(boolean showDuplicates);
}
