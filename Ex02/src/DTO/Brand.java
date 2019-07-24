package DTO;

public enum Brand {
    TOYOTA(0,"Toyota"),BMW(1,"BMW"),HUYNDAI(2,"Huyndai");
    private int id;
    private String name;

    Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
