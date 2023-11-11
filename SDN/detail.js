document.addEventListener("DOMContentLoaded", function() {
  var tableContainer = document.getElementById("table-container");
  var table = document.getElementById("data-table");

  function addRow() 
  {
    var tbody = table.getElementsByTagName("tbody")[0];
    for(var i = 0; i < 20; ++i)
    {
      var newRow = tbody.insertRow();
      var cell1 = newRow.insertCell();
      cell1.innerHTML = i + "行，列1";
      var cell2 = newRow.insertCell();
      cell2.innerHTML = i + "行，列2";
      var cell3 = newRow.insertCell();
      cell3.innerHTML = i + "行，列3";
      var cell4 = newRow.insertCell();
      cell4.innerHTML = i + "行，列4";
      var cell5 = newRow.insertCell();
      cell5.innerHTML = i + "行，列5";
      var cell6 = newRow.insertCell();
      cell6.innerHTML = Math.random() * 2023;
    }
  }

  function clean()
  {
    var table = document.getElementById("data-table");
    var oldTbody = table.getElementsByTagName("tbody")[0];
    var newTbody = document.createElement("tbody");
    table.replaceChild(newTbody, oldTbody);
    
  }

  function update()
  {
    clean();
    addRow();
  }

  setInterval(update, 5000);
});