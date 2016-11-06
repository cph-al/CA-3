'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute',
    'ngAnimate',
    'angular-jwt',
    'ui.bootstrap',
    'myApp.security',
    'myApp.view1',
    'myApp.view2',
    'myApp.view3',
    'myApp.view4',
    'myApp.view5',
    'myApp.view6',
    'myApp.filters',
    'myApp.directives',
    'myApp.factories',
    'myApp.services'
]).
        config(['$routeProvider', function ($routeProvider) {
                $routeProvider.otherwise({redirectTo: '/view1'});
            }]).
        config(function ($httpProvider) {
            $httpProvider.interceptors.push('AuthInterceptor');
        }).
        controller('myCtrl', ['$http', function ($http) {
                var self = this;
                self.user = {userName: "", passwordHash: ""}
                self.create = function () {
                    console.log(self.user.userName +" "+ self.user.passwordHash);
                    $http.post('api/demoall/user', self.user)
                            .success(function (data, status, headers, config) {
                                console.log("Works");
                            })
                            .error(function (data, status, headers, config) {
                                console.log("Doesn't work");
                            });
                };
                self.exchangeArray = [{desc: "DKK", rate: 1}, {desc: "EUR", rate: 7.44}];
                self.amount = 10;
                self.from;
                self.to;
                self.calc = function () {
                    self.result = self.from / self.to * self.amount;
                    self.result = self.result.toFixed(2);
                };
            }]);


