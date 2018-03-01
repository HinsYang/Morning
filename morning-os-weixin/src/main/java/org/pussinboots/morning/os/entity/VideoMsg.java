package org.pussinboots.morning.os.entity;

/**
 * Created by Hins on 2017/12/19.
 */
public class VideoMsg extends BaseMsg{
    private long mediaId;
    private long thumbMediaId;

    public long getMediaId() {
        return mediaId;
    }

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }

    public long getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(long thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
