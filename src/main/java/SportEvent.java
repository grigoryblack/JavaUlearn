public class SportEvent {
    private final int participants, year;
    private final String section, country, title, subsection;

    public SportEvent(
            int participants,
            String section,
            int year,
            String country,
            String title,
            String subsection) {
        this.participants = participants;
        this.section = section;
        this.year = year;
        this.country = country;
        this.title = title;
        this.subsection = subsection;
    }

    public int getParticipants() {
        return participants;
    }

    public String getSection() {
        return section;
    }

    public int getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public String getTitle() {
        return title;
    }

    public String getSubsection() {
        return subsection;
    }
}