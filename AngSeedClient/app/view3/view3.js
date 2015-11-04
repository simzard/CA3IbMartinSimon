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
                    value: 'search',
                    label: 'normal search'
                }, {
                    value: 'vat',
                    label: 'vat'
                }, {
                    value: 'name',
                    label: 'name'
                }, {
                    value: 'produ',
                    label: 'production unit'
                }, {
                    value: 'phone',
                    label: 'phone number'
                }];



            $scope.find = function () {
                var dataObj = {
                    search: $scope.search,
                    option: $scope.optionList.label,
                    country: $scope.countryList.label
                }
                
                
                $http.post('api/company', dataObj)
                        .success(function (data, status, headers, config) {
                            $scope.data = data;
                        })
                        .error(function (data, status, headers, config) {

                        });
            }

        });