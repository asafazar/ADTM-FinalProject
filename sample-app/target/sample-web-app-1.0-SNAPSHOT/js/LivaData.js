/**
 * Created by OrlyGB on 12/04/2016.
 */
app.controller('LiveData', ['$scope', '$http', '$interval', 'uiGridConstants', function ($scope, $http, $interval, uiGridConstants) {

    $scope.liveData = $interval(function(){
        $http({
            method : 'GET',
            url : 'getLiveData'
        }).success(function(data, status, headers, config) {
            $scope.liveData = data;
        }).error(function(data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    }, 3000);

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
            $scope.gridApi = $scope.liveData;
        },
        columnDefs: [
            // default
            { field: 'שם נייר', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'כמות היצע ל1', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'היצע ל1', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'כמות ביקוש ל1', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'ביקוש ל1', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'מספר נייר', headerCellClass: $scope.highlightFilteredHeader }
        ]
        /*columnDefs: [
            // default
            { field: 'P כמות ביקוש', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'P מחיר ביקוש', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'P מחיר תיאורטי', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'P מחיר היצע', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'P כמות היצע', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'מחיר מימוש', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'C כמות היצע', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'C מחיר היצע', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'C מחיר תיאורטי', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'C מחיר ביקוש', headerCellClass: $scope.highlightFilteredHeader },
            { field: 'C כמות ביקוש', headerCellClass: $scope.highlightFilteredHeader }
        ]*/
    };

    $scope.toggleFiltering = function(){
        $scope.gridOptions.enableFiltering = !$scope.gridOptions.enableFiltering;
        $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
    };
}]);