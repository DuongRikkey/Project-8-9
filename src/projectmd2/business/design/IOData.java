package projectmd2.business.design;

import java.util.List;
import java.util.Scanner;

public interface IOData<T,E>{
    void inputData(Scanner sc, boolean isAdd);
    void displayData();
    int getNewId(List<T> list , E path);
}
