$(document).ready(function()
{
    var re = new RegExp("^[A-Za-z]+$");

    $('#fname').keyup(function()
    {
          if(this.value)
          {
                if(re.test(this.value))
                {
                    $('#fname').css('background-color', '#C3FDB8')
                    $('#fname').css('color', 'green')
                }
                else
                {
                    $('#fname').css('background-color', '#FFC0CB')
                    $('#fname').css('color', 'red')
                }
          }
    });

    $('#lname').keyup(function()
        {
              if(this.value)
              {
                    if(re.test(this.value))
                    {
                        $('#lname').css('background-color', '#C3FDB8')
                        $('#lname').css('color', 'green')
                    }
                    else
                    {
                        $('#lname').css('background-color', '#FFC0CB')
                        $('#lname').css('color', 'red')
                    }
              }
        });

$('#repassword').keyup(function()
    {
          if(this.value)
          {
                if($('#password').val() === $('#repassword').val())
                {
                    $('#repassword').css('background-color', '#C3FDB8')
                    $('#repassword').css('color', 'green')
                }
                else
                {
                    $('#repassword').css('background-color', '#FFC0CB')
                    $('#repassword').css('color', 'red')
                }
          }
    });

$('#email').keyup(function()
{
    var re=RegExp("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$");
    if(this.value)
    {
        if(re.test(this.value))
        {
            $('#email').css('background-color','#C3FDB8')
            $('email').css('color','green')
        }
        else
        {
            $('#email').css('background-color', '#FFC0CB')
            $('#email').css('color', 'red')
        }

    }
});
$("#reason").change(function () {
    if (this.value) {
      $("#btnSubmit").prop("disabled", false);
      $("#reason").css("background-color", "#C3FDB8");
      $("#reason").css("color", "green");
    } else {
      $("#reason").css("background-color", "#FFC0CB");
      $("#reason").css("color", "red");
      $("#btnSubmit").prop("disabled", true);
    }
  });

$("#stuclass").change(function () {
    if (this.value) {
      $("#btnSubmit").prop("disabled", false);
      $("#stuclass").css("background-color", "#C3FDB8");
      $("#stuclass").css("color", "green");
    } else {
      $("#stuclass").css("background-color", "#FFC0CB");
      $("#stuclass").css("color", "red");
      $("#btnSubmit").prop("disabled", true);
    }
  });

$("#file").change(function () {
    if (this.value) {
      $("#btnSubmit").prop("disabled", false);
      $("#file").css("background-color", "#C3FDB8");
      $("#file").css("color", "green");
    } else {
      $("#file").css("background-color", "#FFC0CB");
      $("#file").css("color", "red");
      $("#btnSubmit").prop("disabled", true);
    }
  });
});
