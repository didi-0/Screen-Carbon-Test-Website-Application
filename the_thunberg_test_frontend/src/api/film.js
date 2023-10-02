import service from "@/utils/requestApi";
import serviceServer from "@/utils/requestServer";

export function NowPlaying(language, page) {
  return service({
    url: "/3/movie/now_playing?language=" + language + "&page=" + page,
    method: "get",
  });
}

export function Popular(language, page) {
  return service({
    url: "/3/movie/popular?language=" + language + "&page=" + page,
    method: "get",
  });
}

export function TopRated(language, page) {
  return service({
    url: "/3/movie/top_rated?language=" + language + "&page=" + page,
    method: "get",
  });
}

export function Details(movieId, language) {
  return service({
    url: "/3/movie/" + movieId + "?language=" + language,
    method: "get",
  });
}

export function DetailsTV(seriesId) {
  return service({
    url: "/3/tv/" + seriesId + "?language=en-US",
    method: "get",
  });
}

export function Credits(movieId, language) {
  return service({
    url: "/3/movie/" + movieId + "/credits?language=" + language,
    method: "get",
  });
}

export function CreditsTV(seriesId, language) {
  return service({
    url: "/3/tv/" + seriesId + "/credits?language=" + language,
    method: "get",
  });
}

export function CombinedCredits(personId, language) {
  return service({
    url: "/3/person/" + personId + "/combined_credits?language=" + language,
    method: "get",
  });
}

export function DetailsPeople(personId, language) {
  return service({
    url: "/3/person/" + personId + "?language=" + language,
    method: "get",
  });
}

export function MovieCertifications() {
  return service({
    url: "/3/certification/movie/list",
    method: "get",
  });
}

export function MovieGenresList(language) {
  return service({
    url: "/3/genre/movie/list?language=" + language,
    method: "get",
  });
}

export function MovieDiscovery(certification, page, sortBy, genres, keywords) {
  return service({
    url:
      "/3/discover/movie?certification=" +
      certification +
      "&certification_country=GB&include_adult=false&include_video=false&language=en-US&page=" +
      page +
      "&sort_by=" +
      sortBy +
      "&with_genres=" +
      genres +
      "&with_keywords=" +
      keywords,
    method: "get",
  });
}

export function TVCertifications() {
  return service({
    url: "/3/certification/tv/list",
    method: "get",
  });
}

export function TVGenresList(language) {
  return service({
    url: "/3/genre/tv/list?language=" + language,
    method: "get",
  });
}

export function TVDiscovery(certification, page, sortBy, genres) {
  return service({
    url:
      "/3/discover/tv?certification=" +
      certification +
      "&certification_country=GB&include_adult=false&include_video=false&language=en-US&page=" +
      page +
      "&sort_by=" +
      sortBy +
      "&with_genres=" +
      genres,
    method: "get",
  });
}

export function PopularPeople() {
  return service({
    url: "/3/person/popular?language=en-US&page=1",
    method: "get",
  });
}

export function Trending() {
  return service({
    url: "/3/trending/all/week?language=en-US",
    method: "get",
  });
}

export function selectBulletinBoard() {
  return serviceServer({
    url: "/bulletinBoard/selectBulletinBoard",
    method: "get",
  });
}

export function serverMovieDiscovery(
  certification,
  page,
  sortBy,
  genres,
  keywords
) {
  return serviceServer({
    url:
      "/search/selectMovie?certification=" +
      certification +
      "&certification_country=GB&include_adult=false&include_video=false&language=en-US&page=" +
      page +
      "&sort_by=" +
      sortBy +
      "&with_genres=" +
      genres +
      "&with_keywords=" +
      keywords,
    method: "get",
  });
}

export function serverTVDiscovery(
  certification,
  page,
  sortBy,
  genres
) {
  return serviceServer({
    url:
      "/search/selectTv?certification=" +
      certification +
      "&certification_country=GB&include_adult=false&include_video=false&language=en-US&page=" +
      page +
      "&sort_by=" +
      sortBy +
      "&with_genres=" +
      genres,
    method: "get",
  });
}

export function serverQueryQuestion(data) {
  return serviceServer({
    url: "/queryRate/select",
    method: "post",
    data: data,
  });
}

export function serverUserRate(data) {
  return serviceServer({
    url: "/userRate/insert",
    method: "post",
    data: data,
  });
}

export function serverQueryFilmRate(filmId, filmType) {
  return serviceServer({
    url: "/filmRate/select?filmId=" + filmId + "&filmType=" + filmType,
    method: "get",
  });
}

export function serverQueryCelebRate(actorIds) {
  return serviceServer({
    url: "/celebRate/selectBatch",
    method: "post",
    data: actorIds,
  });
}

export function serverUserLogin(data) {
  return serviceServer({
    url: "/user/login",
    method: "post",
    data: data,
  });
}

export function serverQueryUserInfo(token) {
  return serviceServer({
    url: "/user/info?token=" + token,
    method: "get",
  });
}

export function serverUserRegister(data) {
  return serviceServer({
    url: "/user/register",
    method: "post",
    data: data,
  });
}