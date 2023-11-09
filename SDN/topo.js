var hostPlaceholder = document.getElementById('host-placeholder');
var switchPlaceholder = document.getElementById('switch-placeholder');
var linkPlaceholder = document.getElementById('link-placeholder');
var topoChart = echarts.init(document.getElementById('gauge'));

topoOption = {
    series: [
      {
        type: 'gauge',
        startAngle: 180,
        endAngle: 0,
        min: 0,
        max: 1000,
        splitNumber: 10,
        itemStyle: {
          color: '#58D9F9',
          shadowColor: 'rgba(0,138,255,0.45)',
          shadowBlur: 10,
          shadowOffsetX: 2,
          shadowOffsetY: 2
        },
        progress: {
          show: true,
          roundCap: true,
          width: fontSizeRem(8)
        },
        pointer: {
          icon: 'path://M2090.36389,615.30999 L2090.36389,615.30999 C2091.48372,615.30999 2092.40383,616.194028 2092.44859,617.312956 L2096.90698,728.755929 C2097.05155,732.369577 2094.2393,735.416212 2090.62566,735.56078 C2090.53845,735.564269 2090.45117,735.566014 2090.36389,735.566014 L2090.36389,735.566014 C2086.74736,735.566014 2083.81557,732.63423 2083.81557,729.017692 C2083.81557,728.930412 2083.81732,728.84314 2083.82081,728.755929 L2088.2792,617.312956 C2088.32396,616.194028 2089.24407,615.30999 2090.36389,615.30999 Z',
          length: '75%',
          width: fontSizeRem(5),
          offsetCenter: [0, '5%']
        },
        axisLine: {
          roundCap: true,
          lineStyle: {
            width: fontSizeRem(8)
          }
        },
        axisTick: {
          splitNumber: 2,
          lineStyle: {
            width: 2,
            color: '#999'
          }
        },
        splitLine: {
          length: fontSizeRem(10),
          lineStyle: {
            width: 3,
            color: '#999'
          }
        },
        axisLabel: {
          distance: fontSizeRem(20),
          color: '#999',
          fontSize: fontSizeRem(8)
        },
        title: {
          show: false
        },
        detail: {
          backgroundColor: '#fff',
          borderColor: '#999',
          borderWidth: 2,
          width: '60%',
          lineHeight: fontSizeRem(25),
          height: fontSizeRem(20),
          borderRadius: 8,
          offsetCenter: [0, '35%'],
          valueAnimation: true,
          formatter: function (value) {
            return '{value|' + value.toFixed(0) + '}{unit|MB/s}';
          },
          rich: {
            value: {
              fontSize: fontSizeRem(20),
              fontWeight: 'bolder',
              color: '#777'
            },
            unit: {
              fontSize: fontSizeRem(16),
              color: '#999',
              padding: [0, 0, -10, 5]
            }
          }
        },
        data: [
          {
            value: 100
          }
        ]
      }
    ]
  };


topoChart.setOption(topoOption);  

window.addEventListener('resize', function() 
{
  topoChart.resize();
});

function fontSizeRem(size)
{
  const clientWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
  if (!clientWidth) return;
  let fontSize = clientWidth / 1920;
  return size* fontSize;
}

function bandwidthValueInit()
{
  topoOption.series[0].data[0].value = Math.random() * 1000;
  topoChart.setOption(topoOption);
}

function hostValueInit()
{
  hostPlaceholder.textContent = Math.floor(Math.random() * 10);
}

function switchValueInit()
{
  switchPlaceholder.textContent = Math.floor(Math.random() * 10);
}

function linkValueInit()
{
  linkPlaceholder.textContent = Math.floor(Math.random() * 10);
}

function init()
{
  hostValueInit();
  switchValueInit();
  linkValueInit();
  bandwidthValueInit();
}

function updateBandwidthValue()
{
  topoOption.series[0].data[0].value = Math.random() * 1000;
  topoChart.setOption(topoOption);
}

function updateHostValue()
{
  hostPlaceholder.textContent = Math.floor(Math.random() * 10);
}

function updateSwitchValue()
{
  switchPlaceholder.textContent = Math.floor(Math.random() * 10);
}

function updateLinkValue()
{
  linkPlaceholder.textContent = Math.floor(Math.random() * 10);
}

function update()
{
  updateHostValue();
  updateSwitchValue();
  updateLinkValue();
  updateBandwidthValue();
}

init();
setInterval(update, 5000);