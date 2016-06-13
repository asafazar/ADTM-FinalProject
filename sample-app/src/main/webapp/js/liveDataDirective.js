/**
 * Created by OrlyGB on 11/06/2016.
 */
angular.module('MyApp')
    .controller('liveDataDirective',['$scope', '$http', '$interval', function($scope, $http, $interval) {
    $scope.gridData ={};
    $scope.gridColumns = [
        { field: "bidAmount1", title: "Bid Amount 1" },
        { field: "bid1", title: "Bid 1" },
        { field: "askAmount1", title: "Ask Amount 1" },
        { field: "ask1", title: "Ask 1" },
        { field: "dailyChange", title: "Daily Change" },
        { field: "currentPrice", title: "Current Price" },
        { field: "strikePrice", title: "Strike Price" }
    ];

    $scope.liveData = $interval(function(){
        $http({
            method : 'GET',
            url : 'getLiveData'
        }).success(function(data, status, headers, config) {
            $scope.gridData=data;
            console.log($scope.gridData);
        }).error(function(data, status, headers, config) {
        });
    }, 10000);

}]);