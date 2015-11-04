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
                var dataObj = {
				username : $scope.user.name,
				password : $scope.user.password,				
		};	
		
                $http.post("api/demouser", dataObj).success(function (data, status) {
                    $scope.result = data.message;
                }).error(function (data, status) {
                    $scope.result = "Some error occured!";
                })
            }

        });