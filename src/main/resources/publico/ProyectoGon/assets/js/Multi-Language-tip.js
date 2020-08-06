$(document).ready(function() {
  if(navigator.language != 'es'){
    $(".en").show();
    $(".es").hide();
  }
  $("#es").click(function () {
      $(".es").show();
      $(".en").hide();
  });  
   $("#en").click(function () {
      $(".en").show();
      $(".es").hide();
  });
  
});