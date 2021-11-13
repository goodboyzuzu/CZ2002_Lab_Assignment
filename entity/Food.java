package entity;

import java.io.Serializable;

public class Food extends MenuItem implements Serializable{

    public enum CourseType {MAIN_COURSE, DESSERT, DRINKS};

    private CourseType courseType;

    public Food(String foodName, String desc, double price, CourseType courseType) {
        super(foodName,desc,price);
        this.courseType = courseType;
    }

    public CourseType getCourseType() {
        return courseType;
    }
    
    @Override
    public void setCourseType(int courseTypeInt) {
    	CourseType courseType = CourseType.values()[courseTypeInt - 1];
    	this.courseType = courseType;
    }

}
