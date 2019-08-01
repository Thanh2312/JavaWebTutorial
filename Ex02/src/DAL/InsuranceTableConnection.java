package DAL;

import DTO.Insurance;
import DTO.PackageType;

import java.util.ArrayList;

public class InsuranceTableConnection {
    private static String query;
    private ConnectDB connectDB;
    public InsuranceTableConnection(){
        connectDB = new ConnectDB();
    }

    public Insurance[] getListPackNotAssign() {
        Insurance[] insurances;
        query = "select * from Insurance where Assigned = 'false'";
        ArrayList<ArrayList<byte[]>> listArrayList = connectDB.getResultQuery(query);
        insurances = new Insurance[listArrayList.size()];
        for (int i = 0; i<listArrayList.size();i++){
            ArrayList<byte[]> row = listArrayList.get(i);// lay ra 1 hang
            String insurancePackage = new String(row.get(0));
            String packTypeString = (new String(row.get(1))).replaceAll("\\P{Print}","");
            packTypeString = packTypeString.trim();
            PackageType packageType = null;
            if (packTypeString.equals("A")){
                packageType = PackageType.Modern;
            }else if (packTypeString.equals("B")){
                packageType = PackageType.Medium;
            }else packageType = PackageType.Old;
            boolean isAssigned = new Boolean(new String(row.get(2)));
            insurances[i] = new Insurance(insurancePackage,packageType,isAssigned);
        }
        return insurances;
    }

    public int getCount(){
        int count = 0;
        query = "select count(*) from InsuRance";
        ArrayList<byte[]> bytes = connectDB.getResultQuery(query).get(0);
        count = byteArrayToInt(bytes.get(0));
        return count;
    } // count insurance package from Insurance(table)

    public void addPack(Insurance insurance) {
        query = "INSERT INTO Insurance VALUES ('" + insurance.getInsurancePackage() + "', (select PackageType from PackType where PackageType = '" + insurance.getPackageType().getTypeP() + "'),'"+insurance.isAssigned()+ "')";
        connectDB.dataHandle(query);
    } // add 1 insurance to Insurance(table)


    public static int byteArrayToInt(byte[] b) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i] & 0x000000FF) << shift;
        }
        return value;
    }
    public static void main(String[] args) {
        InsuranceTableConnection insuranceTableConnection = new InsuranceTableConnection();
//        insuranceTableConnection.addPack(new Insurance("AB4", PackageType.Medium,false));
        System.out.println(insuranceTableConnection.getCount());
        Insurance[] insurances = insuranceTableConnection.getListPackNotAssign();
        for (int i = 0; i< insurances.length; i++){
            System.out.println(insurances[i].toString());
        }
    }
}
