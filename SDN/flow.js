document.addEventListener("DOMContentLoaded", function() {
  var tableContainer = document.getElementById("table-container");
  var table = document.getElementById("data-table");
  var flowChart = echarts.init(document.getElementById('nightingale'));

  flowOption = {
      legend: {
        top: 'bottom'
      },
      toolbox: {
        show: true,
        feature: {
          mark: { show: true },
          dataView: { show: true, readOnly: true },
          restore: { show: false },
          saveAsImage: { show: false }
        }
      },
      series: [
        {
          name: '流量统计数据',
          type: 'pie',
          radius: [10, 80],
          center: ['50%', '50%'],
          roseType: 'area',
          itemStyle: {
            borderRadius: 6
          },
          data: [
            { value: 40, name: '1' },
            { value: 38, name: '2' },
            { value: 32, name: '3' },
            { value: 30, name: '4' },
            { value: 28, name: '5' },
            { value: 26, name: '6' },
            { value: 22, name: '7' },
            { value: 18, name: '8' }
          ]
        }
      ]
    };
  
  
  flowChart.setOption(flowOption);  
  
  window.addEventListener('resize', function() 
  {
      flowChart.resize();
  });
  
  function flowValueInit()
  {
      updateFlow();
  }
  
  function init()
  {
      flowValueInit();
  }
  
  function updateFlow()
  {
    var visibleRows = getVisibleRows(tableContainer);
    var _data = [];
    for (var i = 0; i < visibleRows.length; i++) {
      var columns = Array.from(visibleRows[i].querySelectorAll("td"));
      var sValue = columns[5] ? columns[5].textContent : 0;
      var nameValue = "源IP: " + (columns[0] ? columns[0].textContent : "unknown");
      var sObject = { value: sValue, name: nameValue };
      
      _data.push(sObject);
    }
    flowOption.series[0].data = _data;
    console.log(flowOption.series[0].data);
    flowChart.setOption(flowOption);
  }

  init();

  function getVisibleRows(container) {
    var table = container.querySelector("table");
    var tbody = table.querySelector("tbody");
    var rows = Array.from(tbody.querySelectorAll("tr"));
  
    var visibleRows = rows.filter(function (row) {
      var rowRect = row.getBoundingClientRect();
      var containerRect = container.getBoundingClientRect();
      return (
        rowRect.top >= containerRect.top &&
        rowRect.bottom <= containerRect.bottom
      );
    });
  
    return visibleRows;
  }
  
  tableContainer.addEventListener("scroll", function () 
  {
    updateFlow();
  });

  const observer = new MutationObserver(function (mutationsList, observer) {
    for (let mutation of mutationsList) {
      if (mutation.type === 'childList' && mutation.target === table) {
        updateFlow();
      }
    }
  });
  
  const observerConfig = { childList: true, subtree: true };
  observer.observe(table, observerConfig);
});