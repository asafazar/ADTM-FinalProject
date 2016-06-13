//angular.module('MyApp')
    // Directive for generic chart, pass in chart options
    //.directive('hcChart', function () {
    /*
     return {
     restrict: 'E',
     template: '<div></div>',
     scope: {
     options: '='
     },
     link: function (scope, element) {
     Highcharts.chart(element[0], {
     chart: {
     type: 'line'
     },
     title: {
     text: 'TA25.TA Result'
     },
     plotOptions:{
     tooltip: {
     shared: true,
     formatter: function() {
     var result = '<b>' +  this.x + '</b>';
     $.each(this.points, function(i, datum) {
     result += '<br />' + datum.y ;
     result += '<br />' + Highcharts.dateFormat('%A, %b %e, %Y',datum.point.date);
     });
     return result;
     }
     }
     },
     series: [
     {
     'name': 'Pattern1',
     data: [
     {
     y: 480.85998499999994,
     date: 'Date.UTC(2000,04,24)',
     x: 1
     },
     {
     y: 480.85998499999994,
     date: 'Date.UTC(2000,04,25)',
     x: 2
     },
     {
     y: 480.85998499999994,
     date: 'Date.UTC(2000,04,26)',
     x: 3
     },
     {
     y: 481.75,
     date: 'Date.UTC(2000,05,03)',
     x: 4
     }
     ]
     },
     {
     'name': 'Pattern2',
     data:
     [
     {
     y: 540.469971,
     date: 'Date.UTC(2000,08,09)',
     x: 1
     },
     {
     y: 540.469971,
     date: 'Date.UTC(2000,08,10)',
     x: 2
     },
     {
     y: 540.469971,
     date: 'Date.UTC(2000,08,11)',
     x: 3
     },
     {
     y: 552.6400150000001,
     date: 'Date.UTC(2000,08,18)',
     x: 4}
     ]
     },
     {
     'name': 'Pattern3',
     data:
     [
     {
     y: 566.650024,
     date: 'Date.UTC(2000,09,27)',
     x: 1
     },
     {
     y: 559.8099980000001,
     date: 'Date.UTC(2000,09,28)',
     x: 2
     },
     {
     y: 559.8099980000001,
     date: 'Date.UTC(2000,09,29)',
     x: 3
     },
     {
     y: 551.0,
     date: 'Date.UTC(2000,10,06)',
     x: 4
     }
     ]
     },
     {
     'name': 'Pattern4',
     data:
     [
     {
     y: 493.05999800000006,
     date: 'Date.UTC(2000,11,15)',
     x: 1
     },
     {
     y: 492.55999800000006,
     date: 'Date.UTC(2000,11,16)',
     x: 2
     },
     {
     y: 492.55999800000006,
     date: 'Date.UTC(2000,11,17)',
     x: 3
     },
     {
     y: 476.10000599999995,
     date: 'Date.UTC(2000,11,24)',
     x: 4
     }
     ]
     },
     {
     'name': 'Pattern5',
     data:
     [
     {
     y: 477.200012,
     date: 'Date.UTC(2001,01,11)',
     x: 1
     },
     {
     y: 477.200012,
     date: 'Date.UTC(2001,01,12)',
     x: 2
     },
     {
     y: 482.55999800000006,
     date: 'Date.UTC(2001,01,15)',
     x: 3
     },
     {
     y: 468.130005,
     date: 'Date.UTC(2001,01,22)',
     x: 4
     }
     ]
     }
     ]
     });
     }
     };
     })
     // Directive for pie charts, pass in title and data only
     .controller('TimeChartCtrl',['$scope', '$http', function ($scope, $http) {

     $scope.jsonData = function(){
     $http({
     method : 'GET',
     url : '/js/data/T25.TAResults (3).json'
     }).success(function(data, status, headers, config) {
     return data;
     }).error(function(data, status, headers, config) {
     });
     };

     // Sample options for first chart
     $scope.chartOptions = {
     tooltip:{
     shared: true,
     formatter: function() {
     var result = '<b>' +  this.x + '</b>';
     $.each(this.points, function(i, datum) {
     result += '<br />' + datum.y ;
     result += '<br />' + Highcharts.dateFormat('%A, %b %e, %Y',datum.point.date);
     });
     return result;
     }
     },
     title: {
     text: 'TA25.TA Result'
     }
     };

     }]);

}*/

angular.module('MyApp')
    // Directive for generic chart, pass in chart options
    .directive('hcChart', function () {
        return {
            restrict: 'E',
            template: '<div></div>',
            scope: {
                options: '='
            },
            link: function (scope, element) {
                Highcharts.chart(element[0], scope.options);
            }
        };
    })
    // Directive for pie charts, pass in title and data only
    .controller('TimeChartCtrl', function ($scope) {

        $scope.getJsonData = function(){
            $http({
                method : 'GET',
                url : '/js/data/T25.TAResults (3).json'
            }).success(function(data, status, headers, config) {
                $scope.jsonData=data;
            }).error(function(data, status, headers, config) {
            });
        };

        // Sample options for first chart
        $scope.chartPCOptions = {
            tooltip:{
                shared: true,
                formatter: function() {
                    var result = '<b>' + 'day ' +  this.x + '</b>';
                        $.each(this.points, function(i, datum) {
                            result += '<br />' + this.series.name;
                            if (this.series.name != 'Estimated Percent Change'){
                                result += ' day: ' +
                                Highcharts.dateFormat('%A, %b %e, %Y', datum.point.date);
                                result += ', PC: ';
                            }
                            result += datum.point.y +'%';
                            result += '<br />';
                    });
                    return result;
                }
            },
            title: {
                text: 'Pattern Recognition - Percent Change'
            },
            xAxis:{
                title: {
                    text: 'Patterns'
                }
            },
            yAxis: {
                title: {
                    text: 'Percent Change (%)'
                }
            },
            subtitle: {
                text: 'Based on 5 days'
            },
            series:  [
                {
                    name: 'Pattern 1',
                    data: [{x: 1, y: 1.449, date: Date.UTC(2004,08,17)},
                        {x: 2, y: 0.0788, date: Date.UTC(2004,08,18)},
                        {x: 3, y: 1.167, date: Date.UTC(2004,08,19)},
                        {x: 4, y: 1.167, date: Date.UTC(2004,08,20)},
                        {x: 5, y: 3.002, date: Date.UTC(2004,08,23)},
                        {x: 6, y: 1.097, date: Date.UTC(2004,08,30)}]
                },
                {
                    name: 'Pattern 2',
                    data:
                        [{x: 1, y: 1.174, date: Date.UTC(2005,05,26)},
                         {x: 2, y: 1.174, date: Date.UTC(2005,05,27)},
                         {x: 3, y: 1.340, date: Date.UTC(2005,05,30)},
                         {x: 4, y: 1.088, date: Date.UTC(2005,05,31)},
                         {x: 5, y: 1.012, date: Date.UTC(2005,06,01)},
                         {x: 6, y: 1.567, date: Date.UTC(2005,06,08)}]
                },
                {
                    name: 'Pattern 3',
                    data:
                        [{x: 1, y: 0.812, date: Date.UTC(2006,12,07)},
                         {x: 2, y: 0.812, date: Date.UTC(2006,12,08)},
                         {x: 3, y: 1.329, date: Date.UTC(2006,12,11)},
                         {x: 4, y: 1.124, date: Date.UTC(2006,12,12)},
                         {x: 5, y: 1.306, date: Date.UTC(2006,12,13)},
                         {x: 6, y: 1.068, date: Date.UTC(2006,12,20)}]
                },
                {
                    'name': 'Pattern 4',
                    data:
                        [{x: 1, y: 0.777, date: Date.UTC(2007,03,27)},
                         {x: 2, y: 0.111, date: Date.UTC(2007,03,28)},
                         {x: 3, y: 1.633, date: Date.UTC(2007,03,29)},
                         {x: 4, y: 1.633, date: Date.UTC(2007,03,30)},
                         {x: 5, y: 1.633, date: Date.UTC(2007,04,02)},
                         {x: 6, y: 3.168, date: Date.UTC(2007,04,09)}]
                },
                {
                    'name': 'Pattern 5',
                    data:
                    [{x: 1, y: 0.837, date: Date.UTC(2016,05,25)},
                     {x: 2, y: 0.067, date: Date.UTC(2016,05,26)},
                     {x: 3, y: 1.420, date: Date.UTC(2016,05,30)},
                     {x: 4, y: 1.153, date: Date.UTC(2016,05,31)},
                     {x: 5, y: 1.633, date: Date.UTC(2007,04,02)},
                     {x: 6, y: -0.797, date: Date.UTC(2016,06,09)}]
                },
                {
                    'name': 'Pattern 6',
                    data:
                        [{x: 1, y: 0.837, date: Date.UTC(2016,05,25)},
                         {x: 2, y: 0.067, date: Date.UTC(2016,05,26)},
                         {x: 3, y: 1.420, date: Date.UTC(2016,05,30)},
                         {x: 4, y: 1.153, date: Date.UTC(2016,05,31)},
                         {x: 5, y: 0.920, date: Date.UTC(2016,06,01)},
                         {x: 6, y: -0.797, date: Date.UTC(2016,06,09)}]
                },
                {
                    'name': 'Estimated Percent Change',
                    data:
                        [{x: 6, y: -0.392, date:Date.UTC(2016,06,09)}]
                }
            ]

        };

        $scope.chartPriceOptions = {
            tooltip:{
                shared: true,
                formatter: function() {
                    var result = '<b>' + 'day ' +  this.x + '</b>';
                    $.each(this.points, function(i, datum) {
                        result += '<br />' + this.series.name+': ';
                        if (this.series.name != 'Estimated Price'){
                            result += ' day: ' +
                                Highcharts.dateFormat('%A, %b %e, %Y', datum.point.date);
                            result += ', Price: ';
                        }
                        result += datum.point.y +'NIS';
                        result += '<br />';
                    });
                    return result;
                }
            },
            title: {
                text: 'Pattern Recognition - Price'
            },
            subtitle: {
                text: 'Based on 5 days'
            },
            xAxis:{
                title: {
                    text: 'Patterns'
                }
            },
            yAxis: {
                title: {
                    text: 'Price (NIS)'
                }
            },
            series:  [
                {
                    name: 'Pattern 1',
                    data: [
                        {date: Date.UTC(2004,08,17), x: 1, y: 514},
                        {date: Date.UTC(2004,08,18), x: 2, y: 507},
                        {date: Date.UTC(2004,08,19), x: 3, y: 512},
                        {date: Date.UTC(2004,08,20), x: 4, y: 512},
                        {date: Date.UTC(2004,08,23), x: 5, y: 522},
                        {date: Date.UTC(2004,08,30), x: 6, y: 527}]
                },
                {
                    name: 'Pattern 2',
                    data:
                        [
                            {date: Date.UTC(2005,05,26), x: 1, y: 680},
                            {date: Date.UTC(2005,05,27), x: 2, y: 680},
                            {date: Date.UTC(2005,05,30), x: 3, y: 681},
                            {date: Date.UTC(2005,05,31), x: 4, y: 679},
                            {date: Date.UTC(2005,06,01), x: 5, y: 679},
                            {date: Date.UTC(2005,06,08), x: 6, y: 690}]
                },
                {
                    name: 'Pattern 3',
                    data:
                        [
                            {date: Date.UTC(2006,12,07), x: 1, y: 934},
                            {date: Date.UTC(2006,12,08), x: 2, y: 934},
                            {date: Date.UTC(2006,12,11), x: 3, y: 939},
                            {date: Date.UTC(2006,12,12), x: 4, y: 937},
                            {date: Date.UTC(2006,12,13), x: 5, y: 938},
                            {date: Date.UTC(2006,12,20), x: 6, y: 948}]
                },
                {
                    'name': 'Pattern 4',
                    data:
                        [
                            {date: Date.UTC(2007,03,27), x: 1, y: 988},
                            {date: Date.UTC(2007,03,28), x: 2, y: 982},
                            {date: Date.UTC(2007,03,29), x: 3, y: 997},
                            {date: Date.UTC(2007,03,30), x: 4, y: 997},
                            {date: Date.UTC(2007,04,02), x: 5, y: 997},
                            {date: Date.UTC(2007,04,09), x: 6, y: 1028}]
                },
                {
                    'name': 'Pattern 5',
                    data:
                        [
                            {x: 1, date: Date.UTC(2016,05,25), y: 1439},
                            {x: 2, date: Date.UTC(2016,05,26), y: 1428},
                            {x: 3, date: Date.UTC(2016,05,30), y: 1447},
                            {x: 4, date: Date.UTC(2016,05,31), y: 1443},
                            {x: 5, date: Date.UTC(2016,05,31), y: 1430},
                            {date: Date.UTC(2016,06,09), x: 6, y: 1428}]
                },
                {
                    'name': 'Pattern 6',
                    data:
                        [
                            {x: 1, date: Date.UTC(2016,05,25), y: 1439},
                            {x: 2, date: Date.UTC(2016,05,26), y: 1428},
                            {x: 3, date: Date.UTC(2016,05,30), y: 1447},
                            {x: 4, date: Date.UTC(2016,05,31), y: 1443},
                            {x: 5, date: Date.UTC(2016,06,01), y: 1440},
                            {date: Date.UTC(2016,06,09), x: 6, y: 1428}]
                },
                {
                    'name': 'Estimated Price',
                    data:
                        [{x: 6, y: 1415, date:Date.UTC(2016,06,09)}]
                }
            ]

        };
    });


// Load the fonts
Highcharts.createElement('link', {
    href: 'https://fonts.googleapis.com/css?family=Unica+One',
    rel: 'stylesheet',
    type: 'text/css'
}, null, document.getElementsByTagName('head')[0]);

Highcharts.theme = {
    colors: ["#2b908f", "#90ee7e", "#f45b5b", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee",
        "#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
    chart: {
        backgroundColor: {
            linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
            stops: [
                [0, '#2a2a2b'],
                [1, '#3e3e40']
            ]
        },
        style: {
            fontFamily: "'Unica One', sans-serif"
        },
        plotBorderColor: '#606063'
    },
    title: {
        style: {
            color: '#E0E0E3',
            textTransform: 'uppercase',
            fontSize: '20px'
        }
    },
    subtitle: {
        style: {
            color: '#E0E0E3',
            textTransform: 'uppercase'
        }
    },
    xAxis: {
        gridLineColor: '#707073',
        labels: {
            style: {
                color: '#E0E0E3'
            }
        },
        lineColor: '#707073',
        minorGridLineColor: '#505053',
        tickColor: '#707073',
        title: {
            style: {
                color: '#A0A0A3'

            }
        }
    },
    yAxis: {
        gridLineColor: '#707073',
        labels: {
            style: {
                color: '#E0E0E3'
            }
        },
        lineColor: '#707073',
        minorGridLineColor: '#505053',
        tickColor: '#707073',
        tickWidth: 1,
        title: {
            style: {
                color: '#A0A0A3'
            }
        }
    },
    tooltip: {
        backgroundColor: 'rgba(0, 0, 0, 0.85)',
        style: {
            color: '#F0F0F0'
        }
    },
    plotOptions: {
        series: {
            dataLabels: {
                color: '#B0B0B3'
            },
            marker: {
                lineColor: '#333'
            }
        },
        boxplot: {
            fillColor: '#505053'
        },
        candlestick: {
            lineColor: 'white'
        },
        errorbar: {
            color: 'white'
        }
    },
    legend: {
        itemStyle: {
            color: '#E0E0E3'
        },
        itemHoverStyle: {
            color: '#FFF'
        },
        itemHiddenStyle: {
            color: '#606063'
        }
    },
    credits: {
        style: {
            color: '#666'
        }
    },
    labels: {
        style: {
            color: '#707073'
        }
    },

    drilldown: {
        activeAxisLabelStyle: {
            color: '#F0F0F3'
        },
        activeDataLabelStyle: {
            color: '#F0F0F3'
        }
    },

    navigation: {
        buttonOptions: {
            symbolStroke: '#DDDDDD',
            theme: {
                fill: '#505053'
            }
        }
    },

    // scroll charts
    rangeSelector: {
        buttonTheme: {
            fill: '#505053',
            stroke: '#000000',
            style: {
                color: '#CCC'
            },
            states: {
                hover: {
                    fill: '#707073',
                    stroke: '#000000',
                    style: {
                        color: 'white'
                    }
                },
                select: {
                    fill: '#000003',
                    stroke: '#000000',
                    style: {
                        color: 'white'
                    }
                }
            }
        },
        inputBoxBorderColor: '#505053',
        inputStyle: {
            backgroundColor: '#333',
            color: 'silver'
        },
        labelStyle: {
            color: 'silver'
        }
    },

    navigator: {
        handles: {
            backgroundColor: '#666',
            borderColor: '#AAA'
        },
        outlineColor: '#CCC',
        maskFill: 'rgba(255,255,255,0.1)',
        series: {
            color: '#7798BF',
            lineColor: '#A6C7ED'
        },
        xAxis: {
            gridLineColor: '#505053'
        }
    },

    scrollbar: {
        barBackgroundColor: '#808083',
        barBorderColor: '#808083',
        buttonArrowColor: '#CCC',
        buttonBackgroundColor: '#606063',
        buttonBorderColor: '#606063',
        rifleColor: '#FFF',
        trackBackgroundColor: '#404043',
        trackBorderColor: '#404043'
    },

    // special colors for some of the
    legendBackgroundColor: 'rgba(0, 0, 0, 0.5)',
    background2: '#505053',
    dataLabelsColor: '#B0B0B3',
    textColor: '#C0C0C0',
    contrastTextColor: '#F0F0F3',
    maskColor: 'rgba(255,255,255,0.3)'
};

// Apply the theme
Highcharts.setOptions(Highcharts.theme);