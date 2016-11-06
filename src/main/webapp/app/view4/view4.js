'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'app/view4/view4.html',
                    controller: 'View4Ctrl'
                });
            }])

        .controller('View4Ctrl', function ($http, $scope, $location) {
            var self = this;
            $http.get('api/user')
                    .success(function (data, status, headers, config) {
                        $scope.data = data;
                    })
                    .error(function (data, status, headers, config) {
                        $location.path('/view1')
                    });
            self.exchangeArray = [{desc: "DKK", rate: 1}, {desc: "EUR", rate: 7.44}];
            self.amount;
            self.from;
            self.to;
            self.calculate = function () {
                self.result = self.from / self.to * self.amount;
                self.result = self.result.toFixed(2);
            };
        });