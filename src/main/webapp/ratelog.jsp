<%--
  Created by IntelliJ IDEA.
  User: yuandengfeng
  Date: 2017/2/9
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/static/scripts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/scripts/jquery-1.11.0.js"></script>
    <script src="//cdn.bootcss.com/jquery-datetimepicker/2.5.4/build/jquery.datetimepicker.full.min.js"></script>

<%--<script src="${pageContext.request.contextPath}/static/scripts/jquery.datetimepicker.min.js"></script>--%>
    <link href="${pageContext.request.contextPath}/static/scripts/jquery.datetimepicker.min.css" rel="stylesheet">
    <style>
        .control {
            margin:0 5px;
        }
        .control.active {
            background-color: #f90;
        }
    </style>
</head>
<body>
<div style="margin-bottom: 20px;">
    <label>起始时间：</label><input type="datetime" class="start">
    <label>结束时间：</label><input type="datetime" class="end">
    <label>MAC:</label><input type="text" class="mac">
    <button type="button" class="search">查询</button>
</div>
<div>
    <button type="button" data-type="max-out" class="control con-max-out active">max_out</button>
    <button type="button" data-type="max-in" class="control con-max-in">max_in</button>
    <button type="button" data-type="memtotal" class="control memtotal">memtotal</button>
    <button type="button" data-type="memfree" class="control memfree">memfree</button>
</div>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 800px;height:400px;"></div>
<script type="text/javascript">

    Date.prototype.Format = function(fmt){
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "h+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }
    jQuery.KTDate={
        getToday:function(pattern) { //根据pattern格式获取系统时间今天日期, 例子: 输入：pattern:"yyyy-MM-dd" 返回:2014-07-16
            return (new Date()).Format(pattern);
        },
        getBeforeDate:function(n,pattern) { //根据pattern格式获取系统时间前n天的日期,n=0表示今天,n=1表示昨天,n=-1表示明天
            var today = new Date();
            var beforeday = new Date(today.getTime() - (n * 24 * 60 * 60 * 1000));
            return beforeday.Format(pattern);
        },
        getFirstDate : function(pattern) { // 获取当前月的第一天
            var Nowdate = new Date();
            var MonthFirstDay = new Date(Nowdate.getFullYear(),Nowdate.getMonth(),1);
            return MonthFirstDay.Format(pattern);
        },
        getBeforeFirstDate : function(pattern) { // 获取上个月的第一天
            var Nowdate = new Date();
            var MonthFirstDay = new Date(Nowdate.getFullYear(),Nowdate.getMonth()-1,1);
            return MonthFirstDay.Format(pattern);
        },
        getBeforeLastDate : function(pattern) { // 获取上个月的最后一天
            var Nowdate = new Date();
            var MonthLastDay = new Date(Nowdate.getFullYear(),Nowdate.getMonth(),0);
            return MonthLastDay.Format(pattern);
        }
    };

    var defaultDate = $.KTDate.getBeforeDate(1,"yyyy-MM-dd");  //1表示前一天，后面是时间格式

    $(".start").val(defaultDate + " 00:00:00");
    $(".end").val(defaultDate + " 23:59:59");

    $.datetimepicker.setLocale('zh');

    $('.start').datetimepicker({
        todayButton: false,
//        timepicker: false,
        format: 'Y-m-d H:i:s'
//        minDate: $.KTDate.getBeforeDate(90,'yyyy-MM-dd')
//        defaultDate: defaultDate + "00:00:00"
    });

    $('.end').datetimepicker({
        todayButton: false,
//        timepicker: false,
        format: 'Y-m-d H:i:s'
//        minDate: $.KTDate.getBeforeDate(90,'yyyy-MM-dd'),
//        defaultDate: defaultDate + "23:59:59"
    });


    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    var result ;

    function getData() {
        var start = $(".start").val();
        var end = $(".end").val();
        var mac = $(".mac").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/es/ratelog",
            data:{startTime: start, endTime: end, mac: mac},   //请求参数
            type:"POST",
            dataType:"json",   //数组中数据类型
            success: function(res){   //发送请求获取的数据，数组类型

// 数据样例 {"@version":"1","@timestamp":"2017-02-09T09:48:25.725Z","rip":"112.252.112.182","state":"404","uripath":"/wifidog/rate","refer":"\"-\"","ua":"\"curl/7.40.0\"","eventType":"rate","dev":"D4EE0732F834","max_in":"23","min_in":"0","max_out":"21","min_out":"0","avg_in":"13.3","avg_out":"19.5","usr":"0","sys":"18","nic":"0","idle":"81","io":"0","irq":"0","sirq":"0","memtotal":"126500","memfree":"91180","channelpath":"1_75C","wanproto":"dhcp","wanip":"192.168.199.133"}

                var date = [], maxout = [], maxin = [], memtotal = [],memfree = [];
                var len = res.length;
                for(var i = 0; i < len; i++) {
                    // 2017-02-09T07:29:23.232Z
                    var time = res[i]["@timestamp"];
//                var YMD = time.split("T")[0];
                    var HMS = time.split("T")[1].split(".")[0];
                    date.push(HMS);
                    maxout.push(res[i]["max_out"]);
                    maxin.push(res[i]["max_in"]);
                    memtotal.push(res[i]["memtotal"]);
                    memfree.push(res[i]["memfree"]);
                }
                result = {
                    "date":date,
                    "maxin":maxin,
                    "maxout":maxout,
                    "memtotal":memtotal,
                    "memfree":memfree
                }
                $(".con-max-out").trigger("click");
                console.log(result);
            }
        });
    }


    function renderChart(date, data) {
//        $("#main").html("");
        var option = {
            tooltip: {
                trigger: 'axis',
                position: function (pt) {
                    return [pt[0], '10%'];
                }
            },
            title: {
                left: 'center',
                text: '路由数据量面积图',
            },
            toolbox: {
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: date
            },
            yAxis: {
                type: 'value',
                boundaryGap: [0, '100%']
            },
            dataZoom: [{
                type: 'inside',
                start: 0,
                end: 10
            }, {
                start: 0,
                end: 10,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }],
            series: [
                {
                    name:'当前数值',
                    type:'line',
                    smooth:true,
                    symbol: 'none',
                    sampling: 'average',
                    itemStyle: {
                        normal: {
                            color: 'rgb(255, 70, 131)'
                        }
                    },
                    areaStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: 'rgb(255, 158, 68)'
                            }, {
                                offset: 1,
                                color: 'rgb(255, 70, 131)'
                            }])
                        }
                    },
                    data: data
                }
            ]
        };
        myChart.setOption(option);
    }

    // 切换显示数据类型
    $(".control").click(function(){
        var type = $(this).attr("data-type");
        $(".control").removeClass("active");
        $(this).addClass("active");

        if(type == "max-in") {
            renderChart(result.date, result.maxin);
        } else if (type == "max-out") {
            renderChart(result.date, result.maxout);
        } else if (type == "memtotal") {
            renderChart(result.date, result.memtotal);
        } else if (type == "memfree") {
            renderChart(result.date, result.memfree);
        } else {
            // ...
        }
    });


//    页面初始化运行
    $(".search").click(function(){
        getData();
    })



</script>
</body>
</html>