'use strict';
angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider
                        .when('/view5', {
                            templateUrl: 'view5/view5.html',
                            controller: 'View5Ctrl'
                        })
                        .when('/view5d/:username', {
                            template: '<p>{{result}}</p>',
                            controller: 'View5dCtrl'
                        });

            }])

        .controller('View5dCtrl', function ($scope, $http, $routeParams) {
            

            $http.delete('api/admin/user/' + $routeParams.username)
                    .success(function (data, status, headers, config) {
                        $scope.result = data.message;
                        
                      
                    })
                    .error(function (data, status, headers, config) {

                    });


        })

        .controller('View5Ctrl', function ($http, $scope) {

            $http.get('api/admin/users')
                    .success(function (data, status, headers, config) {
                        $scope.data = data;
                    })
                    .error(function (data, status, headers, config) {

                    });

            


        })

//        .factory('View5Factory', function () {
//            var users = {};
//            var service = {};
//            
//            service.getUsersHttp = function() {
//                 $http.get('api/admin/users')
//                    .success(function (data) {
//                        users = data;
//                    })
//            }
//            service.getUsers = function() {
//                return users;
//            }
//            
//    
//            return service;
//
//        })

        .filter('rolesToString', function () {
            return function (rolesArray) {
                // input is roles array
                var rolesString = "";
                for (var i in rolesArray) {
                    rolesString += rolesArray[i] + ", ";
                }
                if (rolesArray.length >= 1) {
                    rolesString = rolesString.substring(0, rolesString.length - 2);
                }
                return rolesString;
            };
        })