/**
 * Created by OrlyGB on 12/04/2016.
 */
app.controller('LiveData', ['$scope', '$http', '$interval', 'uiGridConstants', function ($scope, $http, $interval, uiGridConstants) {

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
            { field: 'Bid Amount 1 C', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'Bid 1 C', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'Ask Amount 1 C', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'Ask 1 C', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'Daily Change C', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'Current Price C', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'Base Price C', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'Strike Price C', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'Current Price P', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'Daily Change P', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'Ask 1 P', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'Ask Amount 1 P', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'Bid 1 P', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'Bid Amount 1 P', headerCellClass: $scope.highlightFilteredHeader }
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
    }, 30000);

    $scope.toggleFiltering = function(){
        $scope.gridOptions.enableFiltering = !$scope.gridOptions.enableFiltering;
        $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
    };
}]);