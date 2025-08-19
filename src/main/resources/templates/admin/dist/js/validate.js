/**VALIDATE DATE**/
$('.input-dob').each(function() {
    $(this).bind("propertychange change click keyup input paste", function(event) {
  
      this.value=this.value.replace(/^(\d\d)(\d)$/g,'$1/$2').replace(/^(\d\d\/\d\d)(\d+)$/g,'$1/$2').replace(/[^\d\/]/g,'');
  
      if ($(this).val().length == 10) {
        $(this).removeClass('validation-failed');
        var dates = $(this).val().split("/");
        var d = new Date();
        var userday = dates[0];
        var usermonth = dates[1];
        var useryear = dates[2];
        var curday = d.getDate();
        var curmonth = d.getMonth() + 1;
        var curyear = d.getFullYear();
        var age = curyear - useryear;
  
        // Compare with current date
        if ((curmonth < usermonth) || ((curmonth == usermonth) && curday < userday)) {
          age--;
        }
  
        if (age >= 18) {
          // User is 18 or older
          $(this).css('border', '1px solid green');
        } else {
          // User is under 18
          $(this).css('border', '1px solid red');
        }
      }
    });
  });  

  /**VALIDATE EMAIL **/
$('#emailInput').on('input', function() {
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
if (emailRegex.test($(this).val())) {
  $('#emailFeedback').text(''); // Email is valid
} else {
  $('#emailFeedback').text('Please enter a valid email address.');
}
});

/**PASS LENGTH VALIDATE**/
var minLength = 3;
var maxLength = 8;
$("input#password").on("keydown keyup change", function(){
  var value = $(this).val();
  if (value.length < minLength)
      $("#passFeedback").text("Text is short");
  else if (value.length > maxLength)
      $("#passFeedback").text("Text is long");
  else
      $("#passFeedback").text("");


});