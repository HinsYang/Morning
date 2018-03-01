package org.pussinboots.morning.os.entity;

/**
 * Created by Hins on 2017/12/18.
 */
public class ImageMsg extends BaseMsg{
    private String picUrl;
    private long mediaId;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public long getMediaId() {
        return mediaId;
    }

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }
}
