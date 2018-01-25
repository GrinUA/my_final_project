package ua.nure.uvarov.web.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/images/*")
public class ImageController extends HttpServlet {

    private String pathToImages;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");

        String fileName = req.getPathInfo();
        File file = new File(pathToImages, fileName);
        BufferedImage bufferedImage = ImageIO.read(file);

        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

        OutputStream outputStream = resp.getOutputStream();
        ImageIO.write(bufferedImage, formatName, outputStream);
        outputStream.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/images/" + req.getPathInfo());
    }

    @Override
    public void init() throws ServletException {
        pathToImages = getServletContext().getInitParameter("images_path");
    }
}
