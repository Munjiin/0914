package org.zerock.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
//
//httpservlet을 상속받는 추상 클래스
//        한 클래스에서 모든 컨트롤 처리
//        현재 실행하고 있는 url을 안다음 그 메소드를 실항하는 코드.

public abstract class AbstractController extends HttpServlet {

    public abstract String getBasic();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service");


        String path = req.getRequestURI().substring(getBasic().length());//write, read ...알기 위해
        String way = req.getMethod(); //get, post 알아옴

        System.out.println(path + ": " + way);

        String methodName = path + way; // writeGET, listGET 등등등등

        Class clz = this.getClass();
        try {
            System.out.println("methodname:"+methodName);
            Method method = clz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);//모든 클래스를 통해서 클래스의 메소드를 찾음

            System.out.println("method:" + method);

            Object result = method.invoke(this, req, resp);//invoke 이용 호출. invoke는 메소드를 실행하는 매소드
            String returnURL = (String)result;
            System.out.println("return :" + returnURL);

            if(returnURL.startsWith("redirect")){
                resp.sendRedirect(returnURL.substring(9));
            }
            req.getRequestDispatcher("/WEB-INF/"+returnURL+".jsp").forward(req,resp); //리 다이랙트는 리다이랙트하게 보내고 아니면 디스팻처하게

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
