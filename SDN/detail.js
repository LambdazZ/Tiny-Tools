function addRow() 
{
  var table = document.getElementById("data-table");
  var tbody = table.getElementsByTagName("tbody")[0];
  var newRow = tbody.insertRow();
  var cell1 = newRow.insertCell();
  cell1.innerHTML = "新行，列1";
  var cell2 = newRow.insertCell();
  cell2.innerHTML = "新行，列2";
  var cell3 = newRow.insertCell();
  cell3.innerHTML = "新行，列3";
}

function clean()
{
  console.log(1111)
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