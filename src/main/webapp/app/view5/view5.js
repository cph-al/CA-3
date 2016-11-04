'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'app/view5/view5.html',
                    controller: 'View5Ctrl'
                });
            }])

        .controller('View5Ctrl', function ($http, $scope, $location) {
            $http.get('api/admin')
                    .success(function (data, status, headers, config) {
                        $scope.data = data;
                    })
                    .error(function (data, status, headers, config) {
                        $location.path('/view1')
                    });

        })
                .controller('userCtrl', function($http, $scope) {
                    $http.get('api/admin/users')
                    .success(function (data, status, headers, config) {
                        $scope.data = data;
                    
                    });
                });