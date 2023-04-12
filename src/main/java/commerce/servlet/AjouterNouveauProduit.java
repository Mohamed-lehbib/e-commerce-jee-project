package commerce.servlet;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
//
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)

public class AjouterNouveauProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



//@WebServlet("/FileUploadServlet")



    /*
     create images folder at, C:\Users\Dharmesh Mourya\Documents\NetBeansProjects\ImageProject\build\web\images
     */

    public static final String UPLOAD_DIR = "image-produit";
    public String dbFileName = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String nom = request.getParameter("nom");
        String categorie = request.getParameter("categorie");
        double prix = Double.parseDouble(request.getParameter("prix"));
        Part part = request.getPart("image");//
        String fileName = extractFileName(part);//file name

        /**
         * *** Get The Absolute Path Of The Web Application ****
         */
        String applicationPath = getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
        System.out.println("applicationPath:" + applicationPath);
        File fileUploadDirectory = new File(uploadPath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }
        String savePath = uploadPath + File.separator + fileName;
        System.out.println("savePath: " + savePath);
        String sRootPath = new File(savePath).getAbsolutePath();
        System.out.println("sRootPath: " + sRootPath);
        part.write(savePath + File.separator);
        File fileSaveDir1 = new File(savePath);
        /*if you may have more than one files with same name then you can calculate some random characters
         and append that characters in fileName so that it will  make your each image name identical.*/
        dbFileName = fileName;
        part.write(savePath + File.separator);
        //out.println(id+" "+firstName+" "+lastName+" "+fileName+" "+savePath);
        /*
         You need this loop if you submitted more than one file
         for (Part part : request.getParts()) {
         String fileName = extractFileName(part);
         part.write(savePath + File.separator + fileName);
         }*/
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_cart", "root", "");
            PreparedStatement pst = con.prepareStatement("insert into products(name, category, price, image) values(?,?,?,?)");
            pst.setString(1, nom);
            pst.setString(2, categorie);
            pst.setDouble(3, prix);
            pst.setString(4, dbFileName);
            pst.executeUpdate();
            response.sendRedirect("index.jsp");
           // out.println("<center><h1>Image inserted Succesfully......</h1></center>");
        } catch (Exception e) {
            out.println(e);
        }

    }
    // file name of the upload file is included in content-disposition header like this:
    //form-data; name="dataFile"; filename="PHOTO.JPG"

    private String extractFileName(Part part) {//This method will print the file name.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}