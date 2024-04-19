package api.workingWithFieldPage.pojo.formMethod;

public class FormRequest {
    public String number;
    public String word;

    public FormRequest() {
    }

    public FormRequest(String number, String word) {
        this.number = number;
        this.word = word;
    }

    public String getNumber() {
        return number;
    }

    public String getWord() {
        return word;
    }
}
