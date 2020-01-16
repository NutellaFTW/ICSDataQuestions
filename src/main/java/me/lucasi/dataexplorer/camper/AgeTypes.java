package me.lucasi.dataexplorer.camper;

public enum AgeTypes {

    RO(new int[]{10, 11}),
    FR(new int[]{12}),
    RA(new int[]{13}),
    VO(new int[]{14, 15});

    private final int[] ageGroup;

    AgeTypes(int[] ageGroup) {
        this.ageGroup = ageGroup;
    }

    public int[] getAgeGroup() {
        return ageGroup;
    }

}
