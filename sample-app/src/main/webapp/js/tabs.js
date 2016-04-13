/**
 * Created by OrlyGB on 10/02/2016.
 */

var app = angular.module("MyApp", ['ngMaterial', 'ngMessages', 'ui.bootstrap', 'ngTouch', 'ui.grid']);

/*
    angular
        .module('MyApp',['ngMaterial', 'ngMessages', 'ui.bootstrap','ngTouch', 'ui.grid'])
        */
/*
        .controller('MainCtrl', ['$scope', '$http', '$interval', 'uiGridConstants', function ($scope, $http, $interval, uiGridConstants) {

            $scope.liveData = [];
            $interval(function(){
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
                    $scope.gridApi = gridApi;
                },
                columnDefs: [
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
                ]
            };

            $http.get('/webapp/tabs/market/market.json')
                .success(function(data) {
                    $scope.gridOptions.data = data;

                });

            $scope.toggleFiltering = function(){
                $scope.gridOptions.enableFiltering = !$scope.gridOptions.enableFiltering;
                $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
            };
        }])
        */

/*
        .controller('market', ['$scope', function ($scope) {

        $scope.myData = [
            {
                "P כמות ביקוש":"16",
                "P מחיר ביקוש": "80",
                "P מחיר תיאורטי": "82",
                "P מחיר היצע": "84",
                "P כמות היצע": "2",
                "מחיר מימוש": "001 WK2",
                "C כמות היצע":"1",
                "C מחיר היצע": "5600",
                "C מחיר תיאורטי": "5301",
                "C מחיר ביקוש": "4390",
                "C כמות ביקוש": "3"
            },
            {
                "P כמות ביקוש":"16",
                "P מחיר ביקוש": "80",
                "P מחיר תיאורטי": "82",
                "P מחיר היצע": "84",
                "P כמות היצע": "2",
                "מחיר מימוש": "001 WK2",
                "C כמות היצע":"1",
                "C מחיר היצע": "5600",
                "C מחיר תיאורטי": "5301",
                "C מחיר ביקוש": "4390",
                "C כמות ביקוש": "3"
            },
            {
                "P כמות ביקוש":"16",
                "P מחיר ביקוש": "80",
                "P מחיר תיאורטי": "82",
                "P מחיר היצע": "84",
                "P כמות היצע": "2",
                "מחיר מימוש": "001 WK2",
                "C כמות היצע":"1",
                "C מחיר היצע": "5600",
                "C מחיר תיאורטי": "5301",
                "C מחיר ביקוש": "4390",
                "C כמות ביקוש": "3"
            }
        ];
    }])*/

/*
        .controller('AppCtrl', AppCtrl).controller('AppCtrl', function($scope) {
        $scope.myDate = new Date();
        $scope.minDate = new Date(
            $scope.myDate.getFullYear(),
            $scope.myDate.getMonth() - 2,
            $scope.myDate.getDate());
        $scope.maxDate = new Date(
            $scope.myDate.getFullYear(),
            $scope.myDate.getMonth() + 2,
            $scope.myDate.getDate());
        $scope.onlyWeekendsPredicate = function(date) {
            var day = date.getDay();
            return day === 0 || day === 6;
        }
    })*/
        /*
    .controller('AccordionDemoCtrl', function ($scope) {
        $scope.oneAtATime = true;

       $scope.groups = [
            {
                title: 'Dynamic Group Header - 1',
                content: 'Dynamic Group Body - 1'
            },
            {
                title: 'Dynamic Group Header - 2',
                content: 'Dynamic Group Body - 2'
            }
         ];

        $scope.items = ['Item 1', 'Item 2', 'Item 3'];

        $scope.addItem = function() {
            var newItemNo = $scope.items.length + 1;
            $scope.items.push('Item ' + newItemNo);
        };

        $scope.status = {
            isFirstOpen: true,
            isFirstDisabled: false
        };
    })
        */
        /*
    .controller('stockController', ['$scope','$http',function($scope, $http) {
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
/*
    function AppCtrl ( $scope ) {
        $scope.data = {
            selectedIndex: 0,
            secondLocked:  true,
            secondLabel:   "Item Two",
            bottom:        false
        };
        $scope.next = function() {
            $scope.data.selectedIndex = Math.min($scope.data.selectedIndex + 1, 2) ;
        };
        $scope.previous = function() {
            $scope.data.selectedIndex = Math.max($scope.data.selectedIndex - 1, 0);
        };
    }
})();*/
