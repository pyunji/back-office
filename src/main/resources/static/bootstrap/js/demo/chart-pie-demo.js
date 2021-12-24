// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
var ctx = document.getElementById("myPieChart");
var bnameTest0 = $(bname0).val();
var price0 = $(totalprice0).val();
var bnameTest1 = $(bname1).val();
var price1 = $(totalprice1).val();
var bnameTest2 = $(bname2).val();
var price2 = $(totalprice2).val();
var bnameTest3 = $(bname3).val();
var price3 = $(totalprice3).val();

var bnameTest4 = $(bname4).val();
var price4 = $(totalprice4).val();
var bnameTest5 = $(bname5).val();
var price5 = $(totalprice5).val();
var bnameTest6 = $(bname6).val();
var price6 = $(totalprice6).val();
var bnameTest7 = $(bname7).val();
var price7 = $(totalprice7).val();

var bnameTest8 = $(bname8).val();
var price8 = $(totalprice8).val();
var bnameTest9 = $(bname9).val();
var price9 = $(totalprice9).val();
var bnameTest10 = $(bname10).val();
var price10 = $(totalprice10).val();
var bnameTest11 = $(bname11).val();
var price11 = $(totalprice11).val();

var bnameTest12 = $(bname12).val();
var price12 = $(totalprice12).val();
var bnameTest13 = $(bname13).val();
var price13 = $(totalprice13).val();
var bnameTest14 = $(bname14).val();
var price14 = $(totalprice14).val();
var bnameTest15 = $(bname15).val();
var price15 = $(totalprice15).val();

var bnameTest16 = $(bname16).val();
var price16 = $(totalprice16).val();
var bnameTest17 = $(bname17).val();
var price17 = $(totalprice17).val();
//var bnameTest18 = $(bname18).val();
//var price18 = $(totalprice18).val();
//var bnameTest19 = $(bname19).val();
//var price19 = $(totalprice19).val();

console.log(bnameTest0);
console.log(bnameTest1);
console.log(bnameTest2);
console.log(bnameTest3);
console.log(bnameTest4);
console.log(bnameTest5);
console.log(bnameTest6);
console.log(bnameTest7);
console.log(bnameTest8);
console.log(bnameTest9);

console.log(bnameTest10);
console.log(bnameTest11);
console.log(bnameTest12);
console.log(bnameTest13);
console.log(bnameTest14);
console.log(bnameTest15);
console.log(bnameTest16);
console.log(bnameTest17);



var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: [bnameTest0, bnameTest1, bnameTest2,bnameTest3,bnameTest4,bnameTest5,
    bnameTest6,bnameTest7,bnameTest8,bnameTest9,bnameTest10,bnameTest11,bnameTest12,
    bnameTest13,bnameTest14,bnameTest15,bnameTest16,bnameTest17],
    datasets: [{
      data: [price0, price1, price2,price3,price4,price5,price6,price7,price8,price9,
      price10, price11, price12,price13,price14,price15,price16,price17],
      backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc', '#ABDD93','#4e73df', '#1cc88a', '#36b9cc', '#ABDD93'
      ,'#4e73df', '#1cc88a', '#36b9cc', '#ABDD93','#4e73df', '#1cc88a', '#36b9cc', '#ABDD93','#4e73df', '#1cc88a', '#36b9cc', '#ABDD93' ],
      hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf','#2e59d9'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 40,
  },
});
