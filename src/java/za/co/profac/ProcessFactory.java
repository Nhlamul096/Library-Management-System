
package za.co.profac;

import za.co.process.ProcessInt;
import za.co.process.impl.AuthorProcess;
import za.co.process.impl.BookProcess;
import za.co.process.impl.CategoryProcess;
import za.co.process.impl.LoansProcess;
import za.co.process.impl.MemberProcess;
import za.co.process.impl.ReportProcess;
import za.co.process.impl.ReserveProcess;

/**
 *
 * @author thiof
 */
public class ProcessFactory {
     public static ProcessInt createProcess(String process){
         if(process.equalsIgnoreCase("member")){
             return new MemberProcess();
         }
         if(process.equalsIgnoreCase("category")){
             return new CategoryProcess();
         }
         if(process.equalsIgnoreCase("author")){
             return new AuthorProcess();
         }
         if(process.equalsIgnoreCase("book")){
             return new BookProcess();
         }
         if(process.equalsIgnoreCase("loans")){
             return new LoansProcess();
         }
         if(process.equalsIgnoreCase("reports")){
             return new ReportProcess();
         }
         if(process.equalsIgnoreCase("reserves")){
             return new ReserveProcess();
         }

         return null;
    }
}
