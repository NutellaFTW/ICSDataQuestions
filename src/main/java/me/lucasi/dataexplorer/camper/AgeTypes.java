package me.lucasi.dataexplorer.camper;

public enum AgeTypes {

    RO(10),
    FR(12),
    RA(13),
    VO(15);

    private final int ageGroup;

    AgeTypes(int ageGroup) {
        this.ageGroup = ageGroup;
    }

    public int getAgeGroup() {
        return ageGroup;
    }

}
