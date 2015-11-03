'use strict';

angular.module('myApp.view6', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view6', {
                    templateUrl: 'view6/view6.html',
                    controller: 'View6Ctrl'
                });
            }])

        .controller('View6Ctrl', function ($http, $scope) {
//  $http.get('api/demoadmin')
//            .success(function (data, status, headers, config) {
//              $scope.data = data;
//            })
//            .error(function (data, status, headers, config) {
//              
//             });
//
            $scope.addUser = function () {
                var data = JSON.stringify($scope.user);

                $http.post("api/demouser", data).success(function (data, status) {
                    $scope.hello = data;
                })
            }

        });