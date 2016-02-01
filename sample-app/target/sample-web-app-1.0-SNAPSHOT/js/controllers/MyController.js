/**
 * Created by OrlyGB on 30/01/2016.
 */
app.controller('MyController', ['$scope','$http',function($scope, $http) {
    $scope.getDataFromServer = function() {
    $http({
        method : 'GET',
        url : 'javaAngularJS'
    }).success(function(data, status, headers, config) {
        $scope.person = data;
    }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });

};
}]);