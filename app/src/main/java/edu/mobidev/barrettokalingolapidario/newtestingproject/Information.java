package edu.mobidev.barrettokalingolapidario.newtestingproject;

/**
 * Created by mariachristinadalekalingo on 10/10/15.
 */
public class Information {

    private String informationTitle;
    private String subtitleOne;
    private String descriptionOne;
    private String subtitleTwo;
    private String descriptionTwo;
    private int icon;

    public Information(String informationTitle, String subtitleOne, String descriptionOne, String subtitleTwo, String descriptionTwo, int icon) {
        this.informationTitle = informationTitle;
        this.subtitleOne = subtitleOne;
        this.descriptionOne = descriptionOne;
        this.subtitleTwo = subtitleTwo;
        this.descriptionTwo = descriptionTwo;
        this.icon = icon;
    }

    public Information(String informationTitle, int icon) {
        this.informationTitle = informationTitle;
        this.icon = icon;
    }

    public String getSubtitleOne() {
        return subtitleOne;
    }

    public void setSubtitleOne(String subtitleOne) {
        this.subtitleOne = subtitleOne;
    }

    public String getDescriptionOne() {
        return descriptionOne;
    }

    public void setDescriptionOne(String descriptionOne) {
        this.descriptionOne = descriptionOne;
    }

    public String getSubtitleTwo() {
        return subtitleTwo;
    }

    public void setSubtitleTwo(String subtitleTwo) {
        this.subtitleTwo = subtitleTwo;
    }

    public String getDescriptionTwo() {
        return descriptionTwo;
    }

    public void setDescriptionTwo(String descriptionTwo) {
        this.descriptionTwo = descriptionTwo;
    }

    public String getInformationTitle() {
        return informationTitle;
    }

    public void setInformationTitle(String informationTitle) {
        this.informationTitle = informationTitle;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
