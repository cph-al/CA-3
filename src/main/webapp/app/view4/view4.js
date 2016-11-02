'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'app/view4/view4.html',
                    controller: 'View4Ctrl'
                });
            }])

        .controller('View4Ctrl', function ($http, $scope, $location) {
            $http.get('api/user')
                    .success(function (data, status, headers, config) {
                        $scope.data = data;
                    })
                    .error(function (data, status, headers, config) {
                        $location.path('/view1')
                    });
        });