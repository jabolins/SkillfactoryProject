package models;

public enum StudyProfile {
    MEDICINE("медицина")
    , LINGUISTICS("лингвистика")
    , PHYSICS("физика")
    , MATHEMATICS( "математика")
    , DEFAULT("по умолчанию");

    private String profileName;

    StudyProfile(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileName() {
        return profileName;
    }

    @Override
    public String toString() {
        return "StudyProfile{" +
                "profileName='" + profileName + '\'' +
                '}';
    }
}
