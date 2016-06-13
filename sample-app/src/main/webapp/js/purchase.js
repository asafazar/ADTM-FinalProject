/**
 * Created by OrlyGB on 10/06/2016.
 */
/**
 * Created by OrlyGB on 28/05/2016.
 */
angular.module('MyApp')
    .controller('dataCtrl', ['$scope', '$http', '$interval', function($scope, $http, $interval){
        $scope.data={};
        $scope.gridData ={};
        $scope.gridColumns = [
            { field: "BidAmount1C", title: "P&L" },
            { field: "Bid1C", title: "P&L" },
            { field: "AskAmount1C", title: "Premium" },
            { field: "Ask1C", title: "Price" },
            { field: "DailyChangeC", title: "CALL" },
            { field: "CurrentPriceC", title: "Strike" },
            { field: "BasePriceC", title: "PUT" },
            { field: "StrikePriceP", title: "Price" },
            { field: "CurrentPriceP", title: "Premium" }
        ];

        $scope.strikes = $interval(function(){
            $http({
                method : 'GET',
                url : 'getStrikes'
            }).success(function(data, status, headers, config) {
                $scope.data;
                console.log($scope.gridData);
            }).error(function(data, status, headers, config) {
            });
        }, 30000);
    }]);