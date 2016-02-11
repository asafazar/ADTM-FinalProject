/**
 * Created by OrlyGB on 01/02/2016.

MyApp.controller('stockController', ['$scope','$http',function($scope, $http) {
    $scope.getStockDataFromServer = function() {
        $http({
            method : 'GET',
            url : 'getStocks'
        }).success(function(data, status, headers, config) {
            $scope.stocks = data;
        }).error(function(data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };
}]);
 */