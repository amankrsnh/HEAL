
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="heal.dto.Doctors,java.util.ArrayList,java.io.IOException,java.io.InputStream,java.io.ByteArrayOutputStream,java.util.Base64"  %>
<%
    String userid=(String)session.getAttribute("id");
    ArrayList<Doctors> dArr=(ArrayList<Doctors>)request.getAttribute("doctors");
    StringBuffer displayBlock=new StringBuffer();
    for(Doctors d:dArr)
    {
        InputStream inputStream = d.getdImage().getBinaryStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);                  
        }
        byte[] imageBytes = outputStream.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        inputStream.close();
        outputStream.close();
        
        String img="data:image/jpg;base64,"+base64Image;
        displayBlock.append("<div class='col-md-6 col-lg-3 ftco-animate fadeInUp ftco-animated'><div class='staff'><div class='img-wrap d-flex align-items-stretch'><div class='img align-self-stretch' style='background-image: url("+img+");'></div></div><div class='text pt-3 text-center'><h3 class='mb-2'>"+d.getdName()+"</h3><span class='position mb-2'>"+d.getdSpecialisation()+"</span><div class='faded'><p>"+d.getdHospital()+"</p><ul class='ftco-social text-center'><li class='ftco-animate'><a href='#'><span class='icon-twitter'></span></a></li><li class='ftco-animate'><a href='#'><span class='icon-facebook'></span></a></li><li class='ftco-animate'><a href='#'><span class='icon-google-plus'></span></a></li><li class='ftco-animate'><a href='#'><span class='icon-instagram'></span></a></li></ul><p><a href='javascript:checkLogin(`"+userid+"`)' class='btn btn-primary'>Book now</a></p></div></div></div></div>");
    }
    out.println(displayBlock);   
    
%>
