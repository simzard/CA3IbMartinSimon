'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', function ($http, $scope) {

//  $http.get('api/demoadmin')
//            .success(function (data, status, headers, config) {
//              $scope.data = data;
//            })
//            .error(function (data, status, headers, config) {
//              
//             });

            $scope.countries = [{
                    value: 'dk',
                    label: 'dk'
                }, {
                    value: 'nor',
                    label: 'nor'
                }];
            
            $scope.options = [{
                    value: 'vat',
                    label: 'vat'
                }, {
                    value: 'name',
                    label: 'name'
                }, {
                    value: 'produ',
                    label: 'produ'
                }, {
                    value: 'phone',
                    label: 'phone'
                }];



            $scope.find = function () {
                document.domain = "localhost";
                
                $scope.optionsString = "?";
                $scope.optionsString += $scope.optionList.label + "=" + $scope.search;
                $scope.optionsString += "&country=" + $scope.countryList.label;
                
                $http.get('http://cvrapi.dk/api' + $scope.optionsString)
                        .success(function (data, status, headers, config) {
                            $scope.data = data;
                        })
                        .error(function (data, status, headers, config) {

                        });
            }

        });