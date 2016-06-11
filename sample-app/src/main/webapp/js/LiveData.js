/**
 * Created by OrlyGB on 12/04/2016.
 */
angular.module('MyApp')
    .controller('LiveData', ['$scope', '$http', '$interval', 'uiGridConstants', function ($scope, $http, $interval, uiGridConstants) {

    $scope.highlightFilteredHeader = function( row, rowRenderIndex, col, colRenderIndex ) {
        if( col.filters[0].term ){
            return 'header-filtered';
        } else {
            return '';
        }
    };

    $scope.gridOptions = {
        enableFiltering: true,
        onRegisterApi: function(gridApi){
            $scope.gridApi = gridApi;
        },
        columnDefs: [
            // default
            { field: 'bidAmount1', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'bid1', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'askAmount1', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'ask1', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'dailyChange', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'predicted', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'basePrice', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'strikePrice', headerCellClass: $scope.highlightFilteredHeader }
        ]
    };

    $http.get('/js/data/liveData.json')
        .success(function(data) {
            $scope.gridOptions.data = data;
        });


    $scope.gridOptions.data = $interval(function(){
        $http({
            method : 'GET',
            url : 'getLiveData'
        }).success(function(data, status, headers, config) {
                $scope.gridOptions.data = data;
        }).error(function(data, status, headers, config) {
        });
    }, 10000);

    $scope.toggleFiltering = function(){
        $scope.gridOptions.enableFiltering = !$scope.gridOptions.enableFiltering;
        $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
    };
}]);