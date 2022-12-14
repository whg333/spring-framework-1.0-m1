
package samples;
import net.sf.cglib.*;
import java.util.*;
/**
 *
 * @author  baliuka
 */
public class Trace extends BeforeAfterInterceptor {
    
    int ident = 1;
    static Trace callback = new Trace();
    
    /** Creates a new instance of Trace */
    private Trace() {
    }
    
    public static  Object newInstance( Class clazz ){
      try{  
        return Enhancer.enhance( clazz, null, callback );
      }catch( Throwable e ){
         e.printStackTrace(); 
         throw new Error(e.getMessage());
      }  
    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List list = (List)newInstance(Vector.class);
        Object value = "TEST";
        list.add(value);
        list.contains(value);
        try{
         list.set(2, "ArrayIndexOutOfBounds" );
        }catch( ArrayIndexOutOfBoundsException ignore ){
        
        }
       list.add(value + "1");
       list.add(value + "2");
       list.toString(); 
       list.set( 0, null ); 
       list.toString();
       list.add(list);
       list.get(1);
       list.toArray();
       list.remove(list);
       list.remove("");
       list.containsAll(list);
       list.lastIndexOf(value);
    }
    
    /** this method is invoked after execution
     * @param obj this
     * @param method Method
     * @param args Arg array
     * @param retValFromBefore value returned from beforeInvoke
     * @param invokedSuper value returned from invoke super
     * @param retValFromSuper value returner from super
     * @param e Exception thrown by super
     * @throws Throwable any exeption
     * @return value to return from generated method
     */
    public Object afterReturn(Object obj, java.lang.reflect.Method method, Object[] args, boolean invokedSuper, Object retValFromSuper, java.lang.Throwable e) throws java.lang.Throwable {
       
        ident--;
         if(e != null){
           printIdent(ident);   
           System.out.println("throw " + e );  
           System.out.println();
           throw e.fillInStackTrace();
         }
        printIdent(ident); 
        System.out.print("return " );
        if( obj == retValFromSuper)
            System.out.println("this");
        else System.out.println(retValFromSuper);
        
        if(ident == 1)
             System.out.println();
        
        return retValFromSuper;
    }
    
    /** Generated code calls this method before invoking super
     * @param obj this
     * @param method Method
     * @param args Arg array
     * @param retValFromBefore value returned from beforeInvoke
     * @throws Throwable any exeption to stop execution
     * @return true if need to invoke super
     */
    public boolean invokeSuper(Object obj, java.lang.reflect.Method method, Object[] args) throws java.lang.Throwable {
        
        
        printIdent(ident);
        System.out.println( method );
        for( int i = 0; i < args.length; i++ ){
          printIdent(ident);   
          System.out.print( "arg" + (i + 1) + ": ");
          if( obj == args[i])
              System.out.println("this");
          else
              System.out.println(args[i]);
        }
        ident++;
        return true;
    }
    
   void printIdent( int ident ){
       
    
       while( --ident > 0 ){
         System.out.print(".......");
       }
      System.out.print("  ");
   }
    
}
