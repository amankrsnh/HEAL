let username,email,mobile,password,cpassword;

function registerUser()
{
    username=$("#name").val();
    email=$("#email").val();
    mobile=$("#mobile").val();
    password=$("#password").val();
    cpassword=$("#cpassword").val();
    if(password!==cpassword)
    {
        swal("Error", "Password does not match", "error");
        return;
    }
    else
    {
        let data={
            username:username,
            email:email,
            mobile:mobile,
            password:password
        };
        let xhr=$.post("RegistrationControllerServlet",data,processResponse);
        xhr.fail(handleError);
    }
}

function processResponse(responseText)
{
    let str=responseText.trim();
    if(str==="success")
    {
        swal("Success","Registration Done Successfully.","success")
        setTimeout(redirectUser,3000);
    }
    else if (str === "uap")
        swal("Error", "Sorry! the userid already present", "error");
    else
        swal("Error", "Registration failed", "error");
}

function handleError(xhr)
{
    swal("Error","Problem in Server Communication :"+xhr.statusText,"error");
}

function redirectUser()
{
    window.location="index.html";
}

function login()
{
    email=$("#loginemail").val();
    password=$("#loginpassword").val();
    let data={
        email:email,
        password:password
    };
    let xhr=$.post("LoginControllerServlet",data,successLogin);
    xhr.fail(handleError);
}

function successLogin(responseText)
{
    console.log(responseText);
    if(responseText.trim()==='error')
    {
        swal("Access Denied","Invalid Userid/Password","error"); 
    }
    else if(responseText.trim().indexOf("jsessionid")!==-1)
    {
        swal("Success","Login Successful","success").then((value)=>{
                window.location=responseText.trim();
        });    
    }
    else
    {
        swal("Access Denied","Some problem occurred:"+responseText,"error");
    }
}