package DAL;

import DTO.Insurance;

public class PackManage {
    private static String query;
    private ConnectDB connectDB;
    public PackManage(){ connectDB = new ConnectDB();
    }

    public void addPack(Insurance insurance) {
        query = "INSERT INTO Car VALUES ('" + insurance.getInsurancePackage() + "', '" + insurance.getPackageType() + "')";
        connectDB.DataHandle(query);
    }
}
