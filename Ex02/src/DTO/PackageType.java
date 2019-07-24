package DTO;

public enum PackageType {
    Modern("A"),
    Medium("B"),
    Old("C");
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
