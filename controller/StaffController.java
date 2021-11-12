package controller;

import entity.*;
import helperFunction.PopulateDB;

import java.util.ArrayList;

public class StaffController {
    private static ArrayList<Staff> staffList = PopulateDB.staffArrayList;
    
    public String getNameFromID(int staffID) {
        for(Staff staff : staffList) {
            if (staffID == staff.getEmpId()) {
                return staff.getName();
            }
        }
        
        return "NA";
    }
    
    public boolean isStaff(int staffID) {
        for(Staff staff : staffList) {
            if (staffID == staff.getEmpId()) {
                return true;
            }
        }
        return false;
    }
}
