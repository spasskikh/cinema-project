var moviesApp = angular.module('moviesApp', []);

moviesApp.controller('moviesCtrl', function ($scope, $http) {
    $scope.getAllMovies = function () {
        $http.get('/cinema/rest/movies/')
            .success(function (data) {
                $scope.movies = data;
            });
    };

    $scope.getMovie = function (movieId) {
        $http.get('/cinema/rest/movies/'+movieId)
            .success(function (data) {
                $scope.movie = data;
            });
    };
});