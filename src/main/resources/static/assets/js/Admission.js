$(document).ready(function () {
  var re = new RegExp("^[A-Za-z]+$");

  $("#fname").keyup(function () {
    if (this.value) {
      if (re.test(this.value)) {
        $("#btnSubmit").prop("disabled", false);
        $("#fname").css("background-color", "#C3FDB8");
        $("#fname").css("color", "green");
      } else {
        $("#fname").css("background-color", "#FFC0CB");
        $("#fname").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });

  $("#lname").keyup(function () {
    if (this.value) {
      if (re.test(this.value)) {
        $("#btnSubmit").prop("disabled", false);
        $("#lname").css("background-color", "#C3FDB8");
        $("#lname").css("color", "green");
      } else {
        $("#lname").css("background-color", "#FFC0CB");
        $("#lname").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });
  $("#dob").change(function () {
    var dateOfBirth = document.getElementById("dob").value;
    var toStringData = dateOfBirth.toString();
    var year = toStringData.split("-");
    var dobYear = year[0];
    var now = new Date();
    var currentYear = now.getFullYear();
    var age = currentYear - dobYear;
    $("#age").val(age);
    if (this.value) {
      if (re.test(this.value)) {
        $("#btnSubmit").prop("disabled", false);
        $("#lname").css("background-color", "#C3FDB8");
        $("#lname").css("color", "green");
      } else {
        $("#lname").css("background-color", "#FFC0CB");
        $("#lname").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });

  $("#mname").keyup(function () {
    if (this.value) {
      if (re.test(this.value)) {
        $("#btnSubmit").prop("disabled", false);
        $("#mname").css("background-color", "#C3FDB8");
        $("#mname").css("color", "green");
      } else {
        $("#mname").css("background-color", "#FFC0CB");
        $("#mname").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });
  $("#nameMother").keyup(function () {
    if (this.value) {
      if (re.test(this.value)) {
        $("#btnSubmit").prop("disabled", false);
        $("#nameMother").css("background-color", "#C3FDB8");
        $("#nameMother").css("color", "green");
      } else {
        $("#nameMother").css("background-color", "#FFC0CB");
        $("#nameMother").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });
  $("#motherName").keyup(function () {
    if (this.value) {
      if (re.test(this.value)) {
        $("#btnSubmit").prop("disabled", false);
        $("#motherName").css("background-color", "#C3FDB8");
        $("#motherName").css("color", "green");
      } else {
        $("#motherName").css("background-color", "#FFC0CB");
        $("#motherName").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });
  $("#lastschoolName").keyup(function () {
    if (this.value) {
      if (re.test(this.value)) {
        $("#btnSubmit").prop("disabled", false);
        $("#lastschoolName").css("background-color", "#C3FDB8");
        $("#lastschoolName").css("color", "green");
      } else {
        $("#lastschoolName").css("background-color", "#FFC0CB");
        $("#lastschoolName").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });
  $("#fathername").keyup(function () {
    if (this.value) {
      if (re.test(this.value)) {
        $("#btnSubmit").prop("disabled", false);
        $("#fathername").css("background-color", "#C3FDB8");
        $("#fathername").css("color", "green");
      } else {
        $("#fathername").css("background-color", "#FFC0CB");
        $("#fathername").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });
  $("#motherNamedata").keyup(function () {
    if (this.value) {
      if (re.test(this.value)) {
        $("#btnSubmit").prop("disabled", false);
        $("#motherNamedata").css("background-color", "#C3FDB8");
        $("#motherNamedata").css("color", "green");
      } else {
        $("#motherNamedata").css("background-color", "#FFC0CB");
        $("#motherNamedata").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });

  $("#nameFather").keyup(function () {
    if (this.value) {
      if (re.test(this.value)) {
        $("#btnSubmit").prop("disabled", false);
        $("#nameFather").css("background-color", "#C3FDB8");
        $("#nameFather").css("color", "green");
      } else {
        $("#nameFather").css("background-color", "#FFC0CB");
        $("#nameFather").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });

  $("#skills").keyup(function () {
    var skillsRe = RegExp("^[A-Za-zsw,'-]+$");
    if (this.value) {
      if (skillsRe.test(this.value)) {
        $("#btnSubmit").prop("disabled", false);
        $("#skills").css("background-color", "#C3FDB8");
        $("#skills").css("color", "green");
      } else {
        $("#skills").css("background-color", "#FFC0CB");
        $("#skills").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });

  $("#mobileno").keyup(function () {
    var input = document.getElementById("mobileno").value;
    var stringConvert = input.toString();
    var inputLen = stringConvert.length;
    var mobileRe = RegExp("[0-9]{10}");
    if (this.value) {
      if (mobileRe.test(this.value) && inputLen < 11) {
        $("#btnSubmit").prop("disabled", false);
        $("#mobileno").css("background-color", "#C3FDB8");
        $("#mobileno").css("color", "green");
      } else {
        $("#mobileno").css("background-color", "#FFC0CB");
        $("#mobileno").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });

  $("#pnumber").keyup(function () {
    var input = document.getElementById("pnumber").value;
    var stringConvert = input.toString();
    var inputLen = stringConvert.length;
    var mobileRe = RegExp("[0-9]{10}");

    if (this.value) {
      if (mobileRe.test(this.value) && inputLen < 11) {
        $("#btnSubmit").prop("disabled", false);
        $("#btnSubmit").prop("disabled", false);
        $("#pnumber").css("background-color", "#C3FDB8");
        $("#pnumber").css("color", "green");
      } else {
        $("#pnumber").css("background-color", "#FFC0CB");
        $("#pnumber").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });

  $("#emailid").keyup(function () {
    var emailRe = RegExp("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$");
    if (this.value) {
      if (emailRe.test(this.value)) {
        $("#btnSubmit").prop("disabled", false);
        $("#emailid").css("background-color", "#C3FDB8");
        $("#emailid").css("color", "green");
      } else {
        $("#emailid").css("background-color", "#FFC0CB");
        $("#emailid").css("color", "red");
      }
    }
  });

  $("#percentage").keyup(function () {
    var percentageRe = RegExp("[0-9]{2,2}");
    var input = document.getElementById("percentage").value;
    var stringConvert = input.toString();
    var inputLen = stringConvert.length;

    if (this.value) {
      if (percentageRe.test(this.value) && inputLen < 4) {
        $("#btnSubmit").prop("disabled", false);
        $("#percentage").css("background-color", "#C3FDB8");
        $("#percentage").css("color", "green");
      } else {
        $("#percentage").css("background-color", "#FFC0CB");
        $("#percentage").css("color", "red");
        $("#btnSubmit").prop("disabled", true);
      }
    }
  });

  $("#country").change(function () {
    if (this.value) {
      $("#btnSubmit").prop("disabled", false);
      $("#country").css("background-color", "#C3FDB8");
      $("#country").css("color", "green");
    } else {
      $("#emailid").css("background-color", "#FFC0CB");
      $("#emailid").css("color", "red");
      $("#btnSubmit").prop("disabled", true);
    }
  });

  $("#state").change(function () {
    if (this.value) {
      $("#btnSubmit").prop("disabled", false);
      $("#state").css("background-color", "#C3FDB8");
      $("#state").css("color", "green");
    } else {
      $("#state").css("background-color", "#FFC0CB");
      $("#state").css("color", "red");
      $("#btnSubmit").prop("disabled", true);
    }
  });

  $("#district").change(function () {
    if (this.value) {
      $("#btnSubmit").prop("disabled", false);
      $("#district").css("background-color", "#C3FDB8");
      $("#district").css("color", "green");
    } else {
      $("#district").css("background-color", "#FFC0CB");
      $("#district").css("color", "red");
      $("#btnSubmit").prop("disabled", true);
    }
  });

  $("#fqualification").change(function () {
    if (this.value) {
      $("#btnSubmit").prop("disabled", false);
      $("#fqualification").css("background-color", "#C3FDB8");
      $("#fqualification").css("color", "green");
    } else {
      $("#fqualification").css("background-color", "#FFC0CB");
      $("#fqualification").css("color", "red");
      $("#btnSubmit").prop("disabled", true);
    }
  });

  $("#mqualification").change(function () {
    if (this.value) {
      $("#btnSubmit").prop("disabled", false);
      $("#mqualification").css("background-color", "#C3FDB8");
      $("#mqualification").css("color", "green");
    } else {
      $("#mqualification").css("background-color", "#FFC0CB");
      $("#mqualification").css("color", "red");
      $("#btnSubmit").prop("disabled", true);
    }
  });
  $("#cast").change(function () {
    if (this.value) {
      $("#btnSubmit").prop("disabled", false);
      $("#cast").css("background-color", "#C3FDB8");
      $("#cast").css("color", "green");
    } else {
      $("#cast").css("background-color", "#FFC0CB");
      $("#cast").css("color", "red");
      $("#btnSubmit").prop("disabled", true);
    }
  });
});
