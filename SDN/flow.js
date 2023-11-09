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
        radius: [10, 100],
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
    var size = flowOption.series[0].data.length;
    for(var i = 0; i < size; ++i)
        flowOption.series[0].data[i].value = Math.random() * 2023;
    flowChart.setOption(flowOption);
}

function init()
{
    flowValueInit();
}

function updateFlowValue()
{
    var size = flowOption.series[0].data.length;
    for(var i = 0; i < size; ++i)
        flowOption.series[0].data[i].value = Math.random() * 2023;
    flowChart.setOption(flowOption);
}

function update()
{
  updateFlowValue();
}

init();
setInterval(update, 5000);