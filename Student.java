package app.models;

public class Student {
  
    private int id;
    private String name;
    private String module;
    private double note;


    public Student(Int id, String name, String module, double note) {
        this.id = id;
        this.name = name;
        this.module = module;
        this.note = note;
    }
    public int getId(){
      return id;
    }
  
    public String getName() {
        return name;
    }

    public String getModule() {
        return module;
    }

    public double getNote() {
        return note;
    }

    @Override
    public String toString() {
        return name + " | " + module + " | " + note;
    }
}
