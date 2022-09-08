package testBase;

import com.aventstack.extentreports.ExtentTest;

public class extentFactory {

    private extentFactory(){

    }
    private static extentFactory instance = new extentFactory();

    public static extentFactory getInstance(){
        return instance;
    }

    ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();

    public void setExtent(ExtentTest extentTestObject){
        extent.set(extentTestObject);
    }

    public ExtentTest getExtent(){
        return extent.get();
    }

    public void removeExtentObject(){
        extent.remove();
    }
}
