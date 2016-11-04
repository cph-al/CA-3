'use strict';
angular.module('myApp.view3', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])
        .controller('View3Ctrl', function ($http, $scope, $location) {
            $http.get('api/demoall')
                    .success(function (data, status, headers, config)
                    {
                        $scope.data = data;
                    })
                    .error(function (data, status, headers, config)
                    {
                        $location.path('/view1');
                    });
        });
        