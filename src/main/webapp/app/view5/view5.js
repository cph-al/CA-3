'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'app/view5/view5.html',
                    //controller: 'View5Ctrl'
                });
            }])

        .controller('View5Ctrl', function ($http, $scope) {
            var self = this;
            self.data = {};
            $http.get('api/admin/users')
                    .success(function (data, status, headers, config) {
                        self.data = data;
                        console.log("YES" + data)
                    })
                    .error(function (data, status, headers, config) {
                        console.log("nope");
                    })
            self.user = {userName: ""}
            self.delete = function ($http) {
                $http.delete('/api/admin/user/')
                        .success(function (data, status, headers, config) {
                            self.data = data
                            console.log("WORKS")
                        })
                        .error(function (data, status, headers, config) {
                            console.log("nope")
                        });
            }
        });

