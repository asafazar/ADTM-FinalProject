angular.module('MyApp')
    .factory('Account', function($http) {
    return {
      getProfile: function() {
        return $http.get('/rest/api/me');
      },
      updateProfile: function(profileData) {
        return $http.put('/rest/api/me', profileData);
      }
    };
  });