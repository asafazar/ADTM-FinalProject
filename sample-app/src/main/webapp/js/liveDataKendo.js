/**
 * Created by OrlyGB on 28/05/2016.
 */
angular.module('MyApp')
    .controller('liveDataKendo', ['$scope', '$http', '$interval', function($scope, $http, $interval){
    $scope.liveDataGridOptions = {
        dataSource: {
            type: "odata",
            transport: {
                read: {
                    url: "/getLiveData",
                    type: "GET",
                    dataType: "application/json"
                }
            },
            pageSize: 10,
            schema: {
                data: function (data) {
                    return data;
                },
                total: function (data) {
                    return data['odata.count'];
                }
            },
            serverPaging: true,
            serverSorting: true
        },
        batch: true,
        height: 550,
        filterable: true,
        groupable: true,
        sortable: true,
        pageable: true,
        columnMenu: true,
        columns: [
            { field: "bidAmount1", title: "Bid Amount 1" },
            { field: "bid1", title: "Bid 1 C" },
            { field: "askAmount1", title: "Ask Amount 1" },
            { field: "ask1", title: "Ask 1 C" },
            { field: "dailyChange", title: "Daily Change" },
            { field: "currentPrice", title: "Current Price" },
            { field: "basePrice", title: "Base Price" }
        ]
    };
/*
        $scope.liveData = $interval(function(){
            $http({
                method : 'GET',
                url : 'getLiveData'
            }).success(function(data, status, headers, config) {
                $scope.data.set(data);
            }).error(function(data, status, headers, config) {
            });
        }, 5000);

         $scope.liveData = $interval(function(){
                 $http({
                     method : 'GET',
                     url : 'getLiveData'
                 }).success(function(data, status, headers, config) {
                     saveToPc(data, "liveData");
             }).error(function(data, status, headers, config) {
                 });
             }, 30000);


        $scope.saveToPc = function (data, filename) {

            if (!data) {
                console.error('No data');
                return;
            }

            if (!filename) {
                filename = 'download.json';
            }

            if (typeof data === 'object') {
                data = JSON.stringify(data, undefined, 2);
            }

            var blob = new Blob([data], {type: 'text/json'}),
                e = document.createEvent('MouseEvents'),
                a = document.createElement('a');

            a.download = filename;
            a.href = window.URL.createObjectURL(blob);
            a.dataset.downloadurl = ['text/json', a.download, a.href].join(':');
            e.initEvent('click', true, false, window,
                0, 0, 0, 0, 0, false, false, false, false, 0, null);
            a.dispatchEvent(e);
        };
        */
}]);