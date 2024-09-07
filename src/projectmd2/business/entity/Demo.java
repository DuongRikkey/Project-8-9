package projectmd2.business.entity;

public class Demo {
    public String test(){
        try{
            return "ABC";
        }catch (Exception e){

        }finally {
            System.out.println("Final");
        }
        return "BC";
    }
}
