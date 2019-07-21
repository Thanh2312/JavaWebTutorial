package DTO;

public enum PackageType {
    A("Modern Car"),
    B("Medium Car"),
    C("Old Car");
    String typeP;
    PackageType(String typeP){
        this.typeP=typeP;
    }

    public String getTypeP() {
        return typeP;
    }

    public void setTypeP(String typeP) {
        this.typeP = typeP;
    }
}
