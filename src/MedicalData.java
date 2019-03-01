public class MedicalData {
    String first;
    String last;
    int id;
    String notes;

    public MedicalData(String first, String last, int id, String notes){
        this.first = first;
        this.last = last;
        this.id = id;
        this.notes = notes;
    }

    public String getInfo(){
        return Integer.toString(this.id) + " " + this.first + " " + this.last + " " + this.notes;
    }


}
