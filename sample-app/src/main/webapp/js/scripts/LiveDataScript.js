/**
 * Created by OrlyGB on 10/06/2016.
 */

function liveData() {

    var liveData = $interval(function(){
        $http({
            method : 'GET',
            url : 'getLiveData'
        }).success(function(data, status, headers, config) {
            return data;
        }).error(function(data, status, headers, config) {
        });
    }, 30000);
}

