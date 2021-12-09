
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="heal.dto.Patients,java.util.ArrayList,java.io.IOException,java.io.InputStream,java.io.ByteArrayOutputStream,java.util.Base64"  %>
<%
    String userid=(String)session.getAttribute("id");
    ArrayList<Patients> pArr=(ArrayList<Patients>)request.getAttribute("patients");
    StringBuffer displayBlock=new StringBuffer();
    displayBlock.append("<h1>Pateints</h1><table id=\"didpatientss\" class=\"patients mx-5 table table-striped table-bordered table-sm\" cellspacing=\"0\" width=\"100%\">\n" +
            "  <thead>\n" +
            "    <tr>\n" +
            "      <th class=\"th-sm\">Name\n" +
            "\n" +
            "      </th>\n" +
            "      <th class=\"th-sm\">Phone\n" +
            "\n" +
            "      </th>\n" +
            "      <th class=\"th-sm\">Blood Group\n" +
            "\n" +
            "      </th>\n" +
            "      <th class=\"th-sm\">Gender\n" +
            "\n" +
            "      </th>\n" +
            "      <th class=\"th-sm\">DOB\n" +
            "\n" +
            "      </th>\n" +
            "      <th class=\"th-sm\">Address\n" +
            "\n" +
            "      </th>\n" +
            "      <th class=\"th-sm\">Image\n" +
            "\n" +
            "      </th>\n" +
            "    </tr>\n" +
            "  </thead>\n" +
            "  <tbody>\n");
    for(Patients p:pArr)
    {
        InputStream inputStream = p.getpImage().getBinaryStream();
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
        displayBlock.append("\n"+
                     "    <tr>\n" +
                     "      <td>"+p.getpName()+"</td>\n" +
                     "      <td>"+p.getpPhone()+"</td>\n" +
                     "      <td>"+p.getpBg()+"</td>\n" +
                     "      <td>"+p.getpGender()+"</td>\n" +
                     "      <td>"+p.getpDob()+"</td>\n" +
                     "      <td>"+p.getpAddress()+"</td>\n" +
                     "      <td><a href=\""+base64Image+"\" download>\n"+
                     "      Download Image\n"+
                     "      </a></td>\n" +      
                     "    </tr>\n");
           
    }
    displayBlock.append(""+
            "  </tbody>\n"+
            "  </table>");
    out.println(displayBlock);   
    
%>
