/**
 * Created by OrlyGB on 10/02/2016.
 */
(function () {
    'use strict';

    angular
        .module('MyApp',['ngMaterial', 'ngMessages', 'ui.bootstrap'])
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
    })
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
})();
