package com.vick.xiu.enums;

public enum GradeEnum {

    NURSERY_SCHOOL_JUNIOR_CLASS(-2, "小班"),
    NURSERY_SCHOOL_MIDDLE_CLASS(-1, "中班"),
    NURSERY_SCHOOL_SENIOR_CLASS(0, "大班"),
    FIRST_GRADE(1, "一年级"),
    SECOND_GRADE(2, "二年级"),
    THIRD_GRADE(3, "三年级"),
    FOURTH_GRADE(4, "四年级"),
    FIFTH_GRADE(5, "五年级"),
    SIXTH_GRADE(6, "六年级"),
    SEVENTH_GRADE(7, "七年级"),
    EIGHTH_GRADE(8, "八年级"),
    NINTH_GRADE(9, "九年级"),
    SENIOR_ONE(10, "高一"),
    SENIOR_TWO(11, "高二"),
    SENIOR_THREE(12, "高三"),
    FRESHMAN_YEAR(13, "大一"),
    SOPHOMORE_YEAR(14, "大二"),
    JUNIOR_YEAR(15, "大三"),
    SENIOR_YEAR(16, "大四");

    private Integer key;
    private String name;

    GradeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }
}
