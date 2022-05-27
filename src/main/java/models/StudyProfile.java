package Models;

public enum StudyProfile {
    MEDICINE("медицина")
    , ECONOMIC("экономика")
    , PHILOSOPHY("философия")
    , IT( "ИТ");

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
