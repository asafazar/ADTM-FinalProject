/**
 * Created by OrlyGB on 10/06/2016.
 */
/**
 * Created by OrlyGB on 28/05/2016.
 */
angular.module('MyApp')
    .controller('dataCtrl', ['$scope', '$http', '$interval', function($scope, $http, $interval){
        $scope.dataGridOptions = {
            dataSource: {
                type: "odata",
                transport:{
                    read: {
                        url:"/js/data/liveData.json",
                        dataType: "json"
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
                { field: "BidAmount1C", title: "Bid Amount 1 C" },
                { field: "Bid1C", title: "Bid 1 C" },
                { field: "AskAmount1C", title: "Ask Amount 1 C" },
                { field: "Ask1C", title: "Ask 1 C" },
                { field: "DailyChangeC", title: "Daily Change C" },
                { field: "CurrentPriceC", title: "Current Price C" },
                { field: "BasePriceC", title: "Base Price C" },
                { field: "StrikePriceP", title: "Strike Price C" },
                { field: "CurrentPriceP", title: "Current Price P" },
                { field: "DailyChangeP", title: "Daily Change P" },
                { field: "Ask1P", title: "Ask 1 P" },
                { field: "AskAmount1P", title: "Ask Amount 1 P" },
                { field: "Bid1P", title: "Bid 1 P" },
                { field: "BidAmount1P", title: "Bid Amount 1 P" }
            ]
        };
    }]);