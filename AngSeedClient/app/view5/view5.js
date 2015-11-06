'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'view5/view5.html',
                    controller: 'View5Ctrl'
                });
            }])

        .controller('View5Ctrl', function ($http, $scope) {
            $http.get('api/admin/users')
                    .success(function (data, status, headers, config) {
                        $scope.data = data;
                    })
                    .error(function (data, status, headers, config) {

                    });

        })
        .filter('rolesToString', function () {
            return function(rolesArray) {
                // input is roles array
                var rolesString = "";
                for (var i in rolesArray) {
                    rolesString += rolesArray[i] + ",";
                }
                if (rolesArray.length >= 1) {
                    rolesString = rolesString.substring(0,rolesString.length-1);
                }
                return rolesString;
            };
        })