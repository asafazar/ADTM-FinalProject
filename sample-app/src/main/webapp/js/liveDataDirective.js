/**
 * Created by OrlyGB on 11/06/2016.
 */
angular.module('MyApp')
    .controller('liveDataDirective',['$scope', '$http', '$interval', function($scope, $http, $interval) {
    $scope.gridData = new kendo.data.ObservableArray([
        { ContractIdC: "1234", TheoreticalValueC: "5" },
        { ContractIdC: "5678", TheoreticalValueC: "3" }
    ]);
    $scope.gridColumns = [
        { field: "ContractId C", title: "Contract Id C" },
        { field: "TheoreticalValueC", title: "Theoretical Value C" }
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
    }, 30000);

    $scope.update = function() {
        $scope.gridData=[
            { ContractIdC: "1", TheoreticalValueC: "8" },
            { ContractIdC: "2", TheoreticalValueC: "0" }];
        console.log($scope.gridData);
    };
}]);