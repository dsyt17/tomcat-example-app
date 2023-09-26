package by.igoncharov;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/env")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String testMode = System.getenv("TEST_MODE");
        String systemVariableMessage = System.getenv("MESSAGE");

        resp.setContentType("text/html");
        var writer = resp.getWriter();
        writer.write("<html><body>");
        if (testMode != null && !testMode.isEmpty()) {
            writer.write("<h1 style='color: red'> Приложение работает в тестовом режиме! </h1>");
        } else {
            writer.write("<h1 style='color: green'> Приложение работает обчном режиме... </h1>");
        }

        writer.write("<hr/>");
        if (systemVariableMessage != null && !systemVariableMessage.isEmpty()) {
            writer.write(("<h3>Сообщение из переменной MESSAGE: <span style='color: green'> %s </span></h3>").formatted(systemVariableMessage));
        } else {
            writer.write(("<h3>Переменная MESSAGE не задана...").formatted(systemVariableMessage));
        }
        writer.write("</body></html>");
    }
}
