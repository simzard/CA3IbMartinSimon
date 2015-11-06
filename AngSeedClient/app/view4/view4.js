'use strict';

var converterApp = angular.module('myApp.view4', ['ngRoute']);

converterApp.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view4', {
            templateUrl: 'view4/view4.html',
            controller: 'ConverterCon'
        });
    }]);
 
converterApp.controller('ConverterCon', ["$scope","$http", "CurrencyFactory", function ($scope, $http, CurrencyFactory) {

        $scope.currencyList = CurrencyFactory.getValutaData();
        
        $scope.result = 0;
        
        $scope.output = {};
        
       
        
        $scope.valutaCodes = [{
                    value: 'DKR',
                    label: 'DKR'
                }, {
                    value: 'BGN',
                    label: 'BGN'
                }, {
                    value: 'MYR',
                    label: 'MYR'
                  
                }, {
                    value: 'MXN',
                    label: 'MXN'
                  
                }, {
                    value: 'GBP',
                    label: 'GBP'
                  
                }, {
                    value: 'PHP',
                    label: 'PHP'
                  
                }, {
                    value: 'IDR',
                    label: 'IDR'
                }, {
                    value: 'HRK',
                    label: 'HRK'
                }, {
                    value: 'ISK',
                    label: 'ISK'
                }, {
                    value: 'JPY',
                    label: 'JPY'
                }, {
                    value: 'BRL',
                    label: 'BRL'
                }, {
                    value: 'CHF',
                    label: 'CHF'
                }, {
                    value: 'NZD',
                    label: 'NZD'
                }, {
                    value: 'USD',
                    label: 'USD'
                }, {
                    value: 'TRY',
                    label: 'TRY'
                }, {
                    value: 'ILS',
                    label: 'ILS'
                }, {
                    value: 'INR',
                    label: 'INR'
                }, {
                    value: 'HUF',
                    label: 'HUF'
                }, {
                    value: 'ZAR',
                    label: 'ZAR'
                }, {
                    value: 'AUD',
                    label: 'AUD'
                }, {
                    value: 'XDR',
                    label: 'XDR'
                }, {
                    value: 'RUB',
                    label: 'RUB'
                }, {
                    value: 'NOK',
                    label: 'NOK'
                }, {
                    value: 'CZK',
                    label: 'CZK'
                }, {
                    value: 'SEK',
                    label: 'SEK'
                }, {
                    value: 'CAD',
                    label: 'CAD'
                }, {
                    value: 'RON',
                    label: 'RON'
                }, {
                    value: 'EUR',
                    label: 'EUR'
                }, {
                    value: 'CNY',
                    label: 'CNY'
                }, {
                    value: 'SGD',
                    label: 'SGD'
                }, {
                    value: 'PLN',
                    label: 'PLN'
                }, {
                    value: 'KRW',
                    label: 'KRW'
                }, {
                    value: 'HKD',
                    label: 'HKD'
                }, {
                    value: 'THB',
                    label: 'THB'
                }  
            
        ];
            
        
        $scope.test = function(){
            $scope.result = "13";
            
        };


//        $http.post('api/Currency/calculator/'+ output.amount + "/" + output.code + "/" , dataObj)
//                .success(function () {
//                    $scope.data = data;
//                })
//                .error(function () {
//
//                });

    }]);