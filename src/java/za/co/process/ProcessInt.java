
package za.co.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProcessInt {
   void processTheRequest(HttpServletRequest request, HttpServletResponse response);
   void processTheResponse(HttpServletRequest request, HttpServletResponse response);
}
