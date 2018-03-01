package org.pussinboots.morning.os.entity;

/**
 * Created by Hins on 2017/12/18.
 */
public class VoiceMsg extends BaseMsg{
    private long mediaId;
    private String format;

    public long getMediaId() {
        return mediaId;
    }

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
