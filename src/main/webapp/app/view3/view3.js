'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])
        
        .controller('View3Ctrl', function ($http, $scope) {
            $http({
                method: 'GET',
                url: 'api/user'
            }).then(function successCallback(res) {
                $scope.data = res.data.message;
            }, function errorCallback(res) {
                $scope.error = res.status + ": " + res.data.statusText;
                $location.path('/view1');
            });

        });


//.controller('View3Ctrl', function ($http, $scope, $location) {
//    $http.get('/api/user')
//            .success(function (data, status, headers, config) {
//                $scope.data = data;
//            })
//            .error(function (data, status, headers, config) {
//            $scope.error = res.status + ": " + res.data.statusText;
//                $location.path('/view1')
//            });
//
//});