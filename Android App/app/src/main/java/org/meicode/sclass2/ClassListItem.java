package org.meicode.sclass2;

public class ClassListItem {

    String classID, className, classTime;


    public ClassListItem() {

    }

    public ClassListItem(String classID, String className) {
        this.classID = classID;
        this.className = className;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }
}
