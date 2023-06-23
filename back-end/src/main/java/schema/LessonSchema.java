package schema;

public class LessonSchema {

    private String name, videoUrl;
    private Integer time;
    private Boolean isFree;
    private Integer courseid;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getTime() {
        return this.time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Boolean isIsFree() {
        return this.isFree;
    }

    public Boolean getIsFree() {
        return this.isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    public Integer getCourseid() {
        return this.courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }
}
