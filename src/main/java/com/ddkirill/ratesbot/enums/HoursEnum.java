package com.ddkirill.ratesbot.enums;

public enum HoursEnum {

    H00("00:00"),
    H01("01:00"),
    H02("02:00"),
    H03("03:00"),
    H04("04:00"),
    H05("05:00"),
    H06("06:00"),
    H07("07:00"),
    H08("08:00"),
    H09("09:00"),
    H10("10:00"),
    H11("11:00"),
    H12("12:00"),
    H13("13:00"),
    H14("14:00"),
    H15("15:00"),
    H16("16:00"),
    H17("17:00"),
    H18("18:00"),
    H19("19:00"),
    H20("20:00"),
    H21("21:00"),
    H22("22:00"),
    H23("23:00"),
    H24("23:59");

    public final String hours;

    HoursEnum(String hours) {
        this.hours = hours;
    }

    public String getHours() {
        return hours;
    }

    public static boolean contains(String hours) {
        for (HoursEnum hour : values()) {
            if (hour.hours.equals(hours)) {
                return true;
            }
        }
        return false;
    }
}
