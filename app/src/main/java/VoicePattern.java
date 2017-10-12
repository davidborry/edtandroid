/**
 * Created by david on 12/10/17.
 */

public class VoicePattern {
    private String str;

    private final String NEXT_COURSE = "prochain cours";

    public VoicePattern(String str){
        this.str = str;
    }

    public boolean isNextCours(){
        return this.str.contains(NEXT_COURSE);
    }
}
