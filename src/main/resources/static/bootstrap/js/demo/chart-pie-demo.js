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
var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: [bnameTest0, bnameTest1, bnameTest2],
    datasets: [{
      data: [price0, price1, price2],
      backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc', '#4e73df' ],
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
    cutoutPercentage: 80,
  },
});
