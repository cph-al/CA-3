'use strict';
angular.module('myApp.view3', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])
        
        .controller('View3Ctrl',['$http','$scope', function ($http, $scope, $location) {
            $http({
                method: 'GET',
                url: 'api/user'
            }).then(function successCallback(res) {
                $scope.data = res.data.message;
            }, function errorCallback(res) {
                $scope.error = res.status + ": " + res.data.statusText;
                $location.path('/view1');
            });

        }]);
        
