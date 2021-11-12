package entity;

public class Food extends MenuItem {

    public enum CourseType {MAIN_COURSE, DESSERT, DRINKS};

    private CourseType coursetype;

    public Food(String foodName, String desc, double price, CourseType coursetype) {
        super(foodName,desc,price);
        this.coursetype = coursetype;
    }

    public CourseType getCoursetype() {
        return coursetype;
    }

}
