package ztemp;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-12-15 16:33
 */
import java.lang.reflect.Method;

public class MethodDemo {

    public static void main(String[] args) {
        Method[] methods = SampleClass.class.getMethods();
        Class declaringClass = methods[0].getDeclaringClass();
        System.out.println(declaringClass.getName());
        System.out.println(Object.class.equals(declaringClass));

        Method[] methodsInterface = SampleInterface.class.getMethods();
        Class interfaceClass = methodsInterface[0].getDeclaringClass();
        System.out.println(interfaceClass.getName());
        System.out.println(Object.class.equals(interfaceClass));

    }
}

interface SampleInterface {
    String getSampleField();
}

class SampleClass {
    private String sampleField;

    public String getSampleField() {
        return sampleField;
    }

    public void setSampleField(String sampleField) {
        this.sampleField = sampleField;
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/javareflect/javareflect_method_getdeclaringclass.html

