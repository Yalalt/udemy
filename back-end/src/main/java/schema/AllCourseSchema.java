package schema;

import java.time.LocalDate;

import com.mongol.model.Course;

public class AllCourseSchema {

    private Long userId;
    private Integer courseId;
    private String name, imgUrl, teacherName;
    private Integer price, realPrice;
    private LocalDate createdDate;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRealPrice() {
        return this.realPrice;
    }

    public void setRealPrice(Integer realPrice) {
        this.realPrice = realPrice;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setCourse(Course course) {
        this.setName(course.getName());
        this.setImgUrl(course.getImgUrl());
        this.setPrice(course.getPrice());
        this.setRealPrice(course.getRealPrice());
        this.setCreatedDate(course.getCreatedDate());
    }
}
