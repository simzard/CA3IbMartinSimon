'use strict';

/* Place your global Factory-service in this file */

angular.module('myApp.factories', []).
        factory('CurrencyFactory', function ($http) {

            var valutaData;

            $http.get('api/currency/dailyrate')
                    .success(function (data) {
                        valutaData = data;
                    })
                    .error(function () {
                    });

            var service = {};

            service.getValutaData = function () {
                return valutaData;
            }

            return service;
        });