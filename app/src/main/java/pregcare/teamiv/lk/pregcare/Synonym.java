package pregcare.teamiv.lk.pregcare;

/**
 * Created by wanninayake on 5/14/2017.
 */

public class Synonym {
    private static String category;
    private static String synonyms;

    public static String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static String  getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    @Override
    public String toString() {
        return "Synonym [Category=" + category + ", Synonyms = " + synonyms + "]";
    }
}
