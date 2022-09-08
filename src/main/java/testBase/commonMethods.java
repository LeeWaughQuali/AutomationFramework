package testBase;

public class commonMethods {

    public String parseString(String toBeParsed){
        String parsed = null;
        if(toBeParsed.contains(",")){
            parsed = toBeParsed.split(" ")[0].replace(",", "");
        }else{
            parsed = toBeParsed.split(" ")[0];
        }
        return parsed;
    }

}
