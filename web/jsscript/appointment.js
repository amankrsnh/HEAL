function getDoctors()
{
    let xhr=$.post("ShowDoctorsControllerServlet",processResponse);
    xhr.fail(handleError);
}

function processResponse(responseText)
{
    if(responseText!==null)
    {
        $(".doctorslist").empty().append(responseText);
    }
    else
    {
        swal("Error", "Error while getting Doctors List.", "error");
    }
}

function handleError(xhr)
{
    swal("Error","Problem in Server Communication :"+xhr.statusText,"error");
}

function checkLogin(id)
{
    if(id!=null)
        window.location="appointment.jsp";
    else
        window.location="index.html";
}